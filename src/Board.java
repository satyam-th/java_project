//checking at being able to import this file

import java.util.*; // this is for Collections.unmodifiableMap
public class Board {
    final static int BOARD_SIZE = 100; // Size of the board
    private final Map<Integer, Integer> snakes; // Map of snake heads to tails
    private final Map<Integer, Integer> ladders; // Map of ladder bottoms to tops

    public int getBoardSize(){
        return Board.BOARD_SIZE;
    }
  
    public Board() {
        snakes = new HashMap<>(); // fix this 
        ladders = new HashMap<>();
        initializeSnakesAndLadders(); // this is not initialize so the  value of map is not pass
    }

    public void initializeSnakesAndLadders() {
        // this is not working 

        //  ladders
    ladders.put(1, 38);
    ladders.put(4,14 );
    ladders.put(9, 31);
    ladders.put(21,42);
    ladders.put(28, 84);
    ladders.put(51, 67);
    ladders.put(71, 91);
    ladders.put(80, 99);
    //snake
    snakes.put(62, 19);
    snakes.put(17, 7);
    snakes.put(87, 24);
    snakes.put(54, 34);
    snakes.put(64, 60);
    snakes.put(98, 79);
    snakes.put(95, 75);
    snakes.put(93, 73);
    
    }
    public int getDestination(int position) {
        //uppper part is not working copy past here
    //         ladders.put(1, 38);
    // ladders.put(4,14 );
    // ladders.put(9, 31);
    // ladders.put(21,42);
    // ladders.put(28, 84);
    // ladders.put(51, 67);
    // ladders.put(71, 91);
    // ladders.put(80, 99);
    // //snake
    // snakes.put(62, 19);
    // snakes.put(17, 7);
    // snakes.put(87, 24);
    // snakes.put(54, 34);
    // snakes.put(64, 60);
    // snakes.put(98, 79);
    // snakes.put(95, 75);
    // snakes.put(93, 73);



        System.out.println(ladders.get(9));
        System.out.println("number was in here"+ position);
        System.out.println(snakes.containsKey(position)+" snake now ladder " + ladders.containsKey(position));
        

        if(snakes.containsKey(position)) { // checking key for the snake it pass true or false
            System.out.println("if condition work"+ position);
            return snakes.get(position); // If the position lands on a snake, return the tail
        } else if (ladders.containsKey(position)) {
            System.out.println("else if condition work"+ position);
            return ladders.get(position); // If the position lands on a ladder, return the top
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

