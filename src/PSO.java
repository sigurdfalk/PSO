import circle.Circle1DParticleManager;
import circle.Circle2DParticleManager;
import circle.CirclePSOSearch;
import circle.CircleParticleManager;
import knapsack.KnapsackParticleManager;
import knapsack.KnapsackVolumeParticleManager;
import knapsack.KnapsackWeightParticleManager;
import knapsack.KnapsackPSOSearch;

import java.io.IOException;

/**
 * User: sigurd
 * Date: 14/11/13
 * Time: 11:33
 */
public class PSO {
    public static final void main(String[] args) {
        searchCircle(1);
        //searchKnapsack(0);
    }

    public static void searchCircle(int type) {
        CircleParticleManager particleManager = null;

        if (type == 0) {
            particleManager = new Circle1DParticleManager(100, 0.0);
        } else if (type == 1) {
            particleManager = new Circle2DParticleManager(100, 0.0, 0.0);
        }

        CirclePSOSearch circlePSOSearch = new CirclePSOSearch(particleManager);
        circlePSOSearch.search(1000, 200, true, true);
    }

    public static void searchKnapsack(int type) {
        try {
            KnapsackParticleManager particleManager = null;

            if (type == 0) {
                particleManager = new KnapsackWeightParticleManager(4000);
            } else if (type == 1) {
                particleManager = new KnapsackVolumeParticleManager(4000);
            }

            KnapsackPSOSearch knapsackPSOSearch = new KnapsackPSOSearch(particleManager);
            knapsackPSOSearch.search(1000, 0, false, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
