import java.util.Random;
/**
 *
 * @author satyam
 */
public class Dice {
private final Random random;

    public Dice() {
        this.random = new Random();
    }

    /**
     * Simulates rolling a dice and returns a random number between 1 and 6.
     *
     * @return a random integer between 1 and 6
     */
    public int roll() {
        return random.nextInt(6) + 1;
        
    }    
   

}
