import java.util.Random;

public class Dice {

    private int num;

    public Dice() {
        this.num = 1;
    }

    public void roll() {
        this.num = (new Random()).nextInt(6) + 1;
    }

    public int getRolled() {
        return num;
    }
}
