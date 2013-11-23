package circle;

import core.Particle;
import core.ParticleManager;
import utilities.Util;

import java.util.ArrayList;
import java.util.Random;

/**
 * User: sigurd
 * Date: 16/11/13
 * Time: 14:17
 */
public class Circle2DParticleManager extends CircleParticleManager {
    private double u;
    private double v;

    public Circle2DParticleManager(int swarmSize, double u, double v) {
        super(swarmSize);
        this.u = u;
        this.v = v;
    }

    @Override
    public String getPositionString(double[] position) {
        return "x: " + position[0] + " - y: " + position[1];
    }

    @Override
    public double fitness(double[] position) {
        double goal = (u * u) + (v * v);
        double current = (position[0] * position[0]) + (position[1] * position[1]);

        return Math.abs(goal - current);
    }

    @Override
    public ArrayList<Particle> getInitialSwarm() {
        ArrayList<Particle> swarm = new ArrayList<Particle>();

        for (int i = 0; i < swarmSize; i++) {
            double[] position = new double[2];
            position[0] = Util.getRandomDouble(-5.0, 5.0);
            position[1] = Util.getRandomDouble(-5.0, 5.0);

            double[] velocity = new double[2];
            velocity[0] = Util.getRandomDouble(-0.1, 0.1);
            velocity[1] = Util.getRandomDouble(-0.1, 0.1);

            double fitness = fitness(position);

            Particle particle = new Particle(position, velocity, fitness);
            swarm.add(particle);
        }

        return swarm;
    }
}
