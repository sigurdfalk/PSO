package core;

import java.util.Random;

/**
 * User: sigurd
 * Date: 11/11/13
 * Time: 16:47
 */
public class Particle {
    private int index;
    private double[] position;
    private double[] bestPosition;
    private double[] threeNeighborBestPosition;
    private double[] velocity;
    private double fitness;

    public Particle(double[] position, double[] velocity, double fitness) {
        this.position = position;
        this.bestPosition = position;
        this.threeNeighborBestPosition = position;
        this.velocity = velocity;
        this.fitness = fitness;
    }

    public Particle(double fitness) {
        this(null, null, fitness);
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public double[] getBestPosition() {
        return bestPosition;
    }

    public void setBestPosition(double[] bestPosition) {
        this.bestPosition = bestPosition;
    }

    public double[] getThreeNeighborBestPosition() {
        return threeNeighborBestPosition;
    }

    public void setThreeNeighborBestPosition(double[] threeNeighborBestPosition) {
        this.threeNeighborBestPosition = threeNeighborBestPosition;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < position.length; i++) {
            stringBuilder.append(position[i]).append("\t");
        }

        return stringBuilder.toString();
    }
}
















