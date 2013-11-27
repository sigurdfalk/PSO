package circle;

import core.Particle;
import core.ParticleManager;
import utilities.Util;

import java.util.ArrayList;
import java.util.Random;

/**
 * User: sigurd
 * Date: 14/11/13
 * Time: 00:19
 */
public class Circle1DParticleManager extends CircleParticleManager {
    private Double u;

    public Circle1DParticleManager(Integer swarmSize, Double u) {
        super(swarmSize);
        this.u = u;
    }

    @Override
    public String getPositionString(double[] position) {
        return "x: " + position[0];
    }

    @Override
    public double fitness(double[] position) {
        Double goal = u * u;
        Double current = position[0] * position[0];

        return Math.abs(goal - current);
    }

    @Override
    public ArrayList<Particle> getInitialSwarm() {
        ArrayList<Particle> swarm = new ArrayList<Particle>();

        for (int i = 0; i < swarmSize; i++) {
            double[] position = new double[1];
            position[0] = Util.getRandomDouble(-10.0, 10.0);

            double[] velocity = new double[1];
            velocity[0] = Util.getRandomDouble(-0.1, 0.1);

            double fitness = fitness(position);

            Particle particle = new Particle(position, velocity, fitness);
            swarm.add(particle);
        }

        return swarm;
    }
}
