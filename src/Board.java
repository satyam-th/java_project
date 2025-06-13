//checking at being able to import this file
import java.util.Map;
import java.util.HashMap;
import java.util.Collection; // this is for Collections.unmodifiableMap
public class Board {
    final static int BOARD_SIZE = 100; // Size of the board
    private final Map<Integer, Integer> snakes; // Map of snake heads to tails
    private final Map<Integer, Integer> ladders; // Map of ladder bottoms to tops

    public Board() {
       this.initializeSnakesAndLadders();
    }
    public void initializeSnakesAndLadders() {

        // i will update this later with more snakes and ladders
        this.snakes.put(16, 6);
        this.snakes.put(47, 26);
        this.snakes.put(49, 11);
        this.snakes.put(56, 53);
        this.snakes.put(62, 19);
        this.ladders.put(1, 38);
        this.ladders.put(4, 14);
        this.ladders.put(9, 31);
    }
    public int getDestination(int roll) {
        if(this.snakes.containsKey(roll)) {
            return this.snakes.get(roll); // If the roll lands on a snake, return the tail
        } else if (this.ladders.containsKey(roll)) {
            return this.ladders.get(roll); // If the roll lands on a ladder, return the top
        } else {
            return roll; // Otherwise, return the same position
        }
    }
    public Map<Integer, Integer> getSnakes() {
        return Collections.unmodifiableMap(snakes); // Return an unmodifiable view of the snakes map
    }
    public Map<Integer, Integer> getLadders() {
        return Collections.unmodifiableMap(ladders); // Return an unmodifiable view of the ladders map
    }
    public Boolean isWinnnerPosition(int roll) {
        return roll == 100; // Check if the roll is the winning position
    }
    public boolean isSankeHead(int roll) {
        return this.snakes.containsKey(roll); // Check if the roll is a snake head
    }
    public boolean isLadderBottom(int roll) {
        return this.ladders.containsKey(roll); // Check if the roll is a ladder bottom
    }



}
