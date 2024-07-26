import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DiceSimulation {
    PriorityQueue<Integer> minStack;
    List<Dice> dices;

    public DiceSimulation(int numDices) {
        this.minStack = new PriorityQueue<>();
        this.dices = new ArrayList<>();

        for (int i = 0; i < numDices; i++) {
            dices.add(new Dice());
        }
    }



    public static void main(String[] args) {

    }
}
