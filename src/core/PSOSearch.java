package core;

import utilities.Util;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * User: sigurd
 * Date: 12/11/13
 * Time: 13:20
 */
public abstract class PSOSearch {
    public static final double INERTIA_WEIGHT_START = 1.0;
    public static final double INERTIA_WEIGHT_END = 0.4;

    protected ParticleManager particleManager;
    protected ArrayList<Particle> swarm;
    protected double[] globalBestPosition;
    protected double globalBestFitness;
    protected int maxIterations;

    public PSOSearch(ParticleManager particleManager) {
        this.particleManager = particleManager;
    }

    public void search(int maxIterations, long delay, boolean useInertiaWeight, boolean useThreeNeighborPosition, boolean visualize) {
        this.swarm = particleManager.getInitialSwarm();
        this.globalBestPosition = null;
        this.globalBestFitness = getInitialGlobalBestFitness();
        this.maxIterations = maxIterations;

        if (useThreeNeighborPosition) {
            updateThreeNeighborBestPosition(swarm);
        } else {
            updateGlobalBestPosition();
        }

        double inertiaWeight = useInertiaWeight ? INERTIA_WEIGHT_START : 1.0;

        for (int i = 0; i < maxIterations; i++) {
            int j = 0;
            for (Particle particle : swarm) {
                double particleFitness = particle.getFitness();

                if (useThreeNeighborPosition) {
                    System.out.println("threeBest(" + i + ", " + (j++) + "): fitness: " + particleManager.fitness(particle.getThreeNeighborBestPosition()) + " - " + particleManager.getPositionString(particle.getThreeNeighborBestPosition()));
                    particle.setVelocity(getNewVelocity(particle, particle.getThreeNeighborBestPosition(), inertiaWeight));
                } else {
                    particle.setVelocity(getNewVelocity(particle, globalBestPosition, inertiaWeight));
                }
                double[] newPosition = particleManager.getNewPosition(particle);
                particle.setPosition(newPosition);

                double newParticleFitness = particleManager.fitness(particle.getPosition());

                if (newParticleFitness != -1) {
                    particle.setFitness(newParticleFitness);

                    if (doUpdateParticleBestPosition(newParticleFitness, particleFitness)) {
                        particle.setBestPosition(newPosition);
                    }

                    if (exceededFitnessLimit(newParticleFitness)) {
                        updateGlobalBestPosition();
                        System.out.println("exceededFitnessLimit(" + i + "): fitness: " + globalBestFitness + " - " + particleManager.getPositionString(globalBestPosition));
                        return;
                    }
                }

                if (visualize) {
                    particleManager.printSwarm(swarm, globalBestFitness);
                }
            }

            if (useInertiaWeight) {
                System.out.println("inertiaWeight(" + i + "): " + inertiaWeight);
                inertiaWeight = getInertiaWeight(inertiaWeight);
            }

            if (useThreeNeighborPosition) {
                updateThreeNeighborBestPosition(swarm);
            } else {
                updateGlobalBestPosition();
                System.out.println("globalBest(" + i + "): fitness: " + globalBestFitness + " - " + particleManager.getPositionString(globalBestPosition));
            }

            Util.sleepThread(delay);
        }

        updateGlobalBestPosition();
        System.out.println("exceededMaxIterations(" + maxIterations + "): fitness: " + globalBestFitness + " - " + particleManager.getPositionString(globalBestPosition));
    }

    protected double[] getNewVelocity(Particle particle, double[] globalBestPosition, double inertiaWeight) {
        double[] velocity = particle.getVelocity();
        double[] newVelocity = new double[velocity.length];

        for (int i = 0; i < newVelocity.length; i++) {
            double v = getInertia(i, inertiaWeight, velocity) + getParticleSelfMemory(i, particle.getBestPosition(), particle.getPosition()) + getGlobalInfluence(i, globalBestPosition, particle.getPosition());
            newVelocity[i] = getClampedVelocity(v);
        }

        return newVelocity;
    }

    protected double getInertia(int i, double weight, double[] velocity) {
        return weight * velocity[i];
    }

    protected double getParticleSelfMemory(int i, double[] bestPosition, double[] position) {
        return getC1() * Util.getRandomDouble(0.0, 1.0) * (bestPosition[i] - position[i]);
    }

    protected double getGlobalInfluence(int i, double[] globalBestPosition, double[] position) {
        return getC2() * Util.getRandomDouble(0.0, 1.0) * (globalBestPosition[i] - position[i]);
    }

    protected double getClampedVelocity(double velocity) {
        if (velocity > getVelocityClampMax()) {
            velocity = getVelocityClampMax();
        } else if (velocity < getVelocityClampMin()) {
            velocity = getVelocityClampMin();
        }

        return velocity;
    }

    protected void updateGlobalBestPosition() {
        for (Particle particle : swarm) {
            double particleFitness = particle.getFitness();

            if (doUpdateGlobalBestPosition(particleFitness)) {
                this.globalBestFitness = particleFitness;
                this.globalBestPosition = particle.getPosition();
            }
        }
    }

    protected void updateThreeNeighborBestPosition(ArrayList<Particle> swarm) {
        for (int i = 0; i < swarm.size(); i++) {
            TreeMap<Double, Particle> distances = new TreeMap<Double, Particle>();
            Particle particle = swarm.get(i);

            for (int j = 0; j < swarm.size(); j++) {
                if (i != j) {
                    Particle swarmParticle = swarm.get(j);
                    Double distance = Math.abs(swarmParticle.getFitness() - particle.getFitness());
                    distances.put(distance, swarmParticle);
                }
            }

            double bestFitness = particle.getFitness();
            double[] bestPosition = particle.getPosition();

            int j = 0;
            for (Map.Entry<Double, Particle> entry : distances.entrySet()) {
                Particle swarmParticle = entry.getValue();

                if (swarmParticle.getFitness() < bestFitness) {
                    bestFitness = swarmParticle.getFitness();
                    bestPosition = swarmParticle.getPosition();
                }

                if (++j == 3) {
                    break;
                }
            }

            particle.setThreeNeighborBestPosition(bestPosition);
        }
    }

    protected double getInertiaWeight(double prevWeight) {
        double diff = INERTIA_WEIGHT_START - INERTIA_WEIGHT_END;
        double step = diff / (double) maxIterations;
        return prevWeight - step;
    }

    protected abstract double getInitialGlobalBestFitness();

    protected abstract double getC1();

    protected abstract double getC2();

    protected abstract boolean exceededFitnessLimit(double fitness);

    protected abstract double getVelocityClampMax();

    protected abstract double getVelocityClampMin();

    protected abstract boolean doUpdateGlobalBestPosition(double fitness);

    protected abstract boolean doUpdateParticleBestPosition(double newParticleFitness, double particleFitness);
}
