package circle;

import core.Particle;
import core.ParticleManager;
import gui.PSOViewer;
import gui.PSOViewerPanel;

/**
 * User: sigurd
 * Date: 23/11/13
 * Time: 02:28
 */
public abstract class CircleParticleManager extends ParticleManager {
    protected CircleParticleManager(int swarmSize) {
        super(swarmSize, 100.0);
    }

    @Override
    public double[] getNewPosition(Particle particle) {
        double[] position = particle.getPosition();
        double[] newPosition = new double[position.length];

        for (int i = 0; i < newPosition.length; i++) {
            newPosition[i] = position[i] + particle.getVelocity()[i];
        }

        return newPosition;
    }
}
