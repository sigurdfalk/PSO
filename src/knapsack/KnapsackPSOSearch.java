package knapsack;

import core.PSOSearch;
import core.Particle;
import core.ParticleManager;

/**
 * User: sigurd
 * Date: 17/11/13
 * Time: 00:44
 */
public class KnapsackPSOSearch extends PSOSearch {
    public static final double C1 = 1.5;
    public static final double C2 = 1.5;
    public static final double VELOCITY_CLAMP = 1.0;

    public KnapsackPSOSearch(ParticleManager particleManager) {
        super(particleManager);
    }

    @Override
    protected double getInitialGlobalBestFitness() {
        return Double.MIN_VALUE;
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
        return false;
    }

    @Override
    protected double getVelocityClampValue() {
        return VELOCITY_CLAMP;
    }

    @Override
    protected boolean doUpdateParticleBestPosition(double newParticleFitness, double particleFitness) {
        return newParticleFitness > particleFitness;
    }

    @Override
    protected boolean doUpdateGlobalBestPosition(double fitness) {
        return fitness > super.globalBestFitness;
    }
}
