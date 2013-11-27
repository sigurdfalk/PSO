package knapsack;

import core.Particle;
import core.ParticleManager;
import utilities.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: sigurd
 * Date: 22/11/13
 * Time: 21:22
 */
public class KnapsackVolumeParticleManager extends KnapsackParticleManager {
    public static final int MAX_WEIGHT = 1000;
    public static final int MAX_VOLUME = 250;

    public KnapsackVolumeParticleManager(int swarmSize) throws IOException {
        super(swarmSize, 2.0);
    }

    @Override
    protected void setPackages() throws IOException {
        this.packages = getKnapsackPackages(2001);
    }

    @Override
    public String getPositionString(double[] position) {
        double totalWeight = 0.0;
        double totalValue = 0.0;
        double totalVolume = 0.0;

        for (int i = 0; i < position.length; i++) {
            if (position[i] == 1.0) {
                KnapsackVolumePackage knapsackVolumePackage = (KnapsackVolumePackage) packages.get(i);
                totalWeight += knapsackVolumePackage.getWeight();
                totalValue += knapsackVolumePackage.getValue();
                totalVolume += knapsackVolumePackage.getVolume();
            }
        }

        return "totalWeight: " + totalWeight + " - totalVolume: " + totalVolume;
    }

    @Override
    public double fitness(double[] position) {
        double totalWeight = 0.0;
        double totalValue = 0.0;
        double totalVolume = 0.0;

        for (int i = 0; i < position.length; i++) {
            if (position[i] == 1.0) {
                KnapsackVolumePackage knapsackVolumePackage = (KnapsackVolumePackage) packages.get(i);
                totalWeight += knapsackVolumePackage.getWeight();
                totalValue += knapsackVolumePackage.getValue();
                totalVolume += knapsackVolumePackage.getVolume();
            }
        }

        return (totalWeight < MAX_WEIGHT && totalVolume < MAX_VOLUME) ? totalValue : -1;
    }

    private ArrayList<KnapsackPackage> getKnapsackPackages(int count) throws IOException {
        ArrayList<KnapsackPackage> packages = new ArrayList<KnapsackPackage>();

        try {
            File file = new File("pso-packages.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()));

            String line = null;

            for (int i = 0; i < count; i++) {
                line = bufferedReader.readLine();
                String[] val = line.split(",");

                double value = Double.parseDouble(val[0]);
                double weight = Double.parseDouble(val[1]);
                double volume = Util.getRandomDouble(1.0, 100.0);

                KnapsackVolumePackage p = new KnapsackVolumePackage(weight, value, volume);
                packages.add(p);
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Count too high");
        }

        return packages;
    }
}
