package circle;

import core.PSOSearch;
import core.Particle;
import core.ParticleManager;

/**
 * User: sigurd
 * Date: 17/11/13
 * Time: 00:43
 */
public class CirclePSOSearch extends PSOSearch {
    public static final double C1 = 1.5;
    public static final double C2 = 1.5;
    public static final double FITNESS_LIMIT = 0.001;
    public static final double VELOCITY_CLAMP = 0.1;

    public CirclePSOSearch(ParticleManager particleManager) {
        super(particleManager);
    }

    @Override
    protected double getInitialGlobalBestFitness() {
        return Double.MAX_VALUE;
    }

    @Override
    protected double getC1() {
        return C1;
    }

    @Override
    protected double getC2() {
        return C2;
    }

    @Override
    protected boolean exceededFitnessLimit(double fitness) {
        return fitness < FITNESS_LIMIT;
    }

    @Override
    protected double getVelocityClampValue() {
        return VELOCITY_CLAMP;
    }

    @Override
    protected boolean doUpdateParticleBestPosition(double newParticleFitness, double particleFitness) {
        return newParticleFitness < particleFitness;
    }

    @Override
    protected boolean doUpdateGlobalBestPosition(double fitness) {
        return fitness < super.globalBestFitness;
    }
}
