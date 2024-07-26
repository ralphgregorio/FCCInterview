import java.util.*;

public class DiceSimulation {

    private int numDices;
    private List<Dice> dices;
    private Map<Integer, Integer> stats;
    private int iterations = 0;

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
        this.iterations = iterations;
        for (int i = 0; i < iterations; i++) {
            resetDices();
            int score = simulate();
            stats.put(score, stats.getOrDefault(score, 1) + 1);
        }
    }

    public void printStats() {
        System.out.printf("Number of simulations was %d using %d dice\n", iterations, numDices);
        Set<Integer> keys = stats.keySet();
        int test = 0;

        for (Integer key : keys) {
            System.out.printf(
                "Total %d occurs %f occured %d times.\n",
                key,
                (float) stats.get(key)/iterations,
                stats.get(key)
            );
            test += stats.get(key);
        }
        System.out.println(test);

    }

    public static void main(String[] args) {
        DiceSimulation simulation = new DiceSimulation(5);
        simulation.simulate(10);
        simulation.printStats();
    }
}
