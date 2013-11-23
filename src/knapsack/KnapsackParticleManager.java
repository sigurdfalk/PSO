package knapsack;

import core.Particle;
import core.ParticleManager;
import gui.PSOViewer;
import gui.PSOViewerPanel;
import utilities.Util;

import java.io.IOException;
import java.util.ArrayList;

/**
 * User: sigurd
 * Date: 22/11/13
 * Time: 21:38
 */
public abstract class KnapsackParticleManager extends ParticleManager {
    protected ArrayList<KnapsackPackage> packages;

    protected KnapsackParticleManager(int swarmSize, double viewScale) throws IOException {
        super(swarmSize, viewScale);
        setPackages();
    }

    @Override
    public ArrayList<Particle> getInitialSwarm() {
        ArrayList<Particle> swarm = new ArrayList<Particle>();

        for (int i = 0; i < swarmSize; i++) {
            double[] position = new double[packages.size()];
            double[] velocity = new double[packages.size()];

            for (int j = 0; j < velocity.length; j++) {
                velocity[j] = Util.getRandomDouble(0.001, 0.002);
            }

            Particle particle = new Particle(position, velocity, 0.0);
            particle.setPosition(getNewPosition(particle));
            double fitness = fitness(particle.getPosition());
            particle.setFitness(fitness);
            swarm.add(particle);
        }

        return swarm;
    }

    @Override
    public double[] getNewPosition(Particle particle) {
        double[] position = particle.getPosition();
        double[] newPosition = new double[position.length];

        for (int i = 0; i < newPosition.length; i++) {
            double rand = Util.getRandomDouble(0.0, 1.0);
            double velocity = particle.getVelocity()[i];

            newPosition[i] = (rand < velocity) ? 1.0 : 0.0;
        }

        return newPosition;
    }

    protected abstract void setPackages() throws IOException;
}
