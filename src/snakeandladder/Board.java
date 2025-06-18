//checking at being able to import this file
import java.util.*; // this is for Collections.unmodifiableMap
public class Board {
    final static int BOARD_SIZE = 100; // Size of the board
    private final Map<Integer, Integer> snakes; // Map of snake heads to tails
    private final Map<Integer, Integer> ladders; // Map of ladder bottoms to tops

  
        public Board() {
        this.snakes = new HashMap<>(); // fix this 
        this.ladders = new HashMap<>();
    }

    public void initializeSnakesAndLadders() {

        //  ladders
    this.ladders.put(1, 38);
    this.ladders.put(4,14 );
    this.ladders.put(9, 31);
    this.ladders.put(21,42);
    this.ladders.put(28, 84);
    this.ladders.put(51, 67);
    this.ladders.put(71, 91);
    this.ladders.put(80, 99);
    //snake
    this.snakes.put(17, 7);
    this.snakes.put(62, 19);
    this.snakes.put(87, 24);
    this.snakes.put(54, 34);
    this.snakes.put(64, 60);
    this.snakes.put(98, 79);
    this.snakes.put(95, 75);
    this.snakes.put(93, 73);
    
}
    public  int getDestination(int position) {
        if(this.snakes.containsKey(position)) { // checking key for the snake it pass true or false
            return this.snakes.get(position); // If the position lands on a snake, return the tail
        } else if (this.ladders.containsKey(position)) {
            return this.ladders.get(position); // If the position lands on a ladder, return the top
        } else {
            return position; // Otherwise, return the same position
        }
    }
    public Map<Integer, Integer> getSnakes() {
        return Collections.unmodifiableMap(snakes); // Return an unmodifiable view of the snakes map
    }
    public Map<Integer, Integer> getLadders() {
        return Collections.unmodifiableMap(ladders); // Return an unmodifiable view of the ladders map
    }
    public Boolean isWinnnerPosition(int position) {
        return position == 100; // Check if the position is the winning position
    }
    public boolean isSankeHead(int position) {
        return this.snakes.containsKey(position); // Check if the position is a snake head
    }
    public boolean isLadderBottom(int position) {
        return this.ladders.containsKey(position); // Check if the position is a ladder bottom
    }


    }

