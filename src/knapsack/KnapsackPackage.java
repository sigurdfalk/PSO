package knapsack;

/**
 * User: sigurd
 * Date: 16/11/13
 * Time: 18:24
 */
public class KnapsackPackage {
    private double weight;
    private double value;

    public KnapsackPackage(double weight, double value) {
        this.weight = weight;
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }
}
