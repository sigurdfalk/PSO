import circle.Circle1DParticleManager;
import circle.Circle2DParticleManager;
import circle.CirclePSOSearch;
import circle.CircleParticleManager;
import core.PSOSearch;
import core.ParticleManager;
import knapsack.KnapsackParticleManager;
import knapsack.KnapsackVolumeParticleManager;
import knapsack.KnapsackWeightParticleManager;
import knapsack.KnapsackPSOSearch;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;

/**
 * User: sigurd
 * Date: 14/11/13
 * Time: 11:33
 */
public class PSO {
    public static final String PROBLEM_CIRCLE = "circle";
    public static final String PROBLEM_KNAPSACK = "knapsack";
    public static final String TYPE_CIRCLE_1D = "1d";
    public static final String TYPE_CIRCLE_2D = "2d";
    public static final String TYPE_KNAPSACK_WEIGHT = "weight";
    public static final String TYPE_KNAPSACK_VOLUME = "volume";
    public static final String YES = "y";
    public static final String NO = "n";

    public static final void main(String[] args) {
        System.out.println();
        System.out.print("Enter problem (circle or knapsack): ");
        String problem = System.console().readLine();

        ParticleManager particleManager = null;
        PSOSearch psoSearch = null;

        if (problem.trim().equals(PROBLEM_CIRCLE)) {
            System.out.print("Enter problem type (1d or 2d): ");
            String type = System.console().readLine();
            System.out.print("Enter swarm size: ");
            int swarmSize = Integer.parseInt(System.console().readLine().trim());

            if (type.trim().equals(TYPE_CIRCLE_1D)) {
                particleManager = new Circle1DParticleManager(swarmSize, 0.0);
            } else if (type.trim().equals(TYPE_CIRCLE_2D)) {
                particleManager = new Circle2DParticleManager(swarmSize, 0.0, 0.0);
            }

            psoSearch = new CirclePSOSearch(particleManager);
        } else if (problem.trim().equals(PROBLEM_KNAPSACK)) {
            System.out.print("Enter problem type (weight or volume): ");
            String type = System.console().readLine();
            System.out.print("Enter swarm size: ");
            int swarmSize = Integer.parseInt(System.console().readLine().trim());

            try {
                if (type.trim().equals(TYPE_KNAPSACK_WEIGHT)) {
                    particleManager = new KnapsackWeightParticleManager(swarmSize);
                } else if (type.trim().equals(TYPE_KNAPSACK_VOLUME)) {
                    particleManager = new KnapsackVolumeParticleManager(swarmSize);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            psoSearch = new KnapsackPSOSearch(particleManager);
        }

        int maxIterations = 0;
        boolean useInertiaWeight = false;
        boolean useThreeNeighbor = false;
        boolean visualizeSearch = false;

        System.out.print("Enter number of iterations: ");
        maxIterations = Integer.parseInt(System.console().readLine().trim());

        System.out.print("Use inertia weight? (y/n): ");
        String answer = System.console().readLine();

        if (answer.trim().equals(YES)) {
            useInertiaWeight = true;
        }

        System.out.print("Use three neighbor social update? (y/n): ");
        answer = System.console().readLine();

        if (answer.trim().equals(YES)) {
            useThreeNeighbor = true;
        }

        System.out.print("Visualize search? (y/n): ");
        answer = System.console().readLine();

        if (answer.trim().equals(YES)) {
            visualizeSearch = true;
        }

        System.out.println();
        psoSearch.search(maxIterations, 0, useInertiaWeight, useThreeNeighbor, visualizeSearch);

    }

    public static void searchCircle(int type) {
        CircleParticleManager particleManager = null;

        if (type == 0) {
            particleManager = new Circle1DParticleManager(10, 0.0);
        } else if (type == 1) {
            particleManager = new Circle2DParticleManager(10, 0.0, 0.0);
        }

        CirclePSOSearch circlePSOSearch = new CirclePSOSearch(particleManager);
        circlePSOSearch.search(200, 0, false, false, true);
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
            knapsackPSOSearch.search(700, 0, false, false, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
