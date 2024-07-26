import java.util.Random;

public class Dice {

    private int upperBound = 6;
    public Dice() {
    }

    public Dice(int upperBound) {
        this.upperBound = upperBound;
    }

    public int roll() {
        return (new Random()).nextInt(upperBound) + 1;
    }
}
