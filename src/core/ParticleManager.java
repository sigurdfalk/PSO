package core;

import gui.PSOViewer;
import gui.PSOViewerPanel;

import java.util.ArrayList;

/**
 * User: sigurd
 * Date: 12/11/13
 * Time: 13:19
 */
public abstract class ParticleManager {
    protected int swarmSize;
    protected PSOViewer psoViewer;

    protected ParticleManager(int swarmSize, double viewScale) {
        this.swarmSize = swarmSize;
        psoViewer = new PSOViewer(new PSOViewerPanel(viewScale));
    }

    public void printSwarm(ArrayList<Particle> particles, double globalBestFitness) {
        psoViewer.setVisible(true);
        psoViewer.drawUI(particles, globalBestFitness);
    }

    public abstract String getPositionString(double[] position);

    public abstract double fitness(double[] position);

    public abstract ArrayList<Particle> getInitialSwarm();

    public abstract double[] getNewPosition(Particle particle);
}
