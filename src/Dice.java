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

    public int roll() {
        return random.nextInt(6) + 1; // Generates a random number between 1 and 6 +1 because nextInt(6) returns 0-5
        
    }    
   

}
