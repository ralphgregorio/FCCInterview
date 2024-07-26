import java.util.*;

public class DiceSimulation {

    private final int numDices;
    private List<Dice> dices;
    private Map<Integer, Integer> stats;
    private int iterations = 0;
    private double elapsedTime = 0;

    public DiceSimulation(int numDices) {
        this.dices = new ArrayList<>();
        this.numDices = numDices;
        this.stats = new HashMap<>();

        resetDices();
    }

    private void resetDices() {
        this.dices = new ArrayList<>();
        for (int i = 0; i < numDices; i++) {
            dices.add(new Dice());
        }
    }
    public int simulate() {
        int score = 0;
        PriorityQueue<Integer> minStack = new PriorityQueue<>();

        while (dices.size() > 1) {
            Iterator<Dice> iterator = dices.iterator();

            while (iterator.hasNext()) {
                Dice currentDice = iterator.next();
                int roll = currentDice.roll();

                if (roll == 3) {
                    iterator.remove();
                    continue;
                }

                minStack.add(roll);
            }

            Integer min = minStack.poll();

            if (min != null) {
                score += min;
            }

            if (dices.size() > 1) {
                dices.remove(0);
            }
        }

        return score;
    }

    public void simulate(int iterations) {
        stats = new HashMap<>();
        long startTime = System.currentTimeMillis();

        this.iterations = iterations;
        for (int i = 0; i < iterations; i++) {
            resetDices();
            int score = simulate();
            stats.put(score, stats.getOrDefault(score, 0) + 1);
        }

        long endTime = System.currentTimeMillis();
        elapsedTime = (endTime - startTime) / 1000.0;
    }

    public void printStats() {
        System.out.printf("\nNumber of simulations was %d using %d dice. \n", iterations, numDices);
        Set<Integer> keys = stats.keySet();

        for (Integer key : keys) {
            System.out.printf(
                "Total %d occurs %f occured %d times.\n",
                key,
                (float) stats.get(key)/iterations,
                stats.get(key)
            );
        }
        System.out.printf("Total simulation took %f seconds. \n", elapsedTime);
    }

    public static void main(String[] args) {
        DiceSimulation simulation1 = new DiceSimulation(2);
        simulation1.simulate(100);
        simulation1.printStats();

        DiceSimulation simulation2 = new DiceSimulation(6);
        simulation2.simulate(100);
        simulation2.printStats();
    }
}
