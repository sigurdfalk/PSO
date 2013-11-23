package knapsack;

/**
 * User: sigurd
 * Date: 22/11/13
 * Time: 21:29
 */
public class KnapsackVolumePackage extends KnapsackPackage {
    private double volume;

    public KnapsackVolumePackage(double weight, double value, double volume) {
        super(weight, value);
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }
}
