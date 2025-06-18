import java.util.*;

public class GameInstance {
private  Board board;
private Dice dice;
private List<Player> player; 
private int CurrentPositionIndex;
private boolean Gameover;
private Player winPlayer;
private List<String> gamelog;
private Map<String, List<Integer>> playerDiceRolls; // to store the data of 

public GameInstance(List<Player> gameplayers){
    this.board = new Board();
    this.dice = new Dice();
    this.player = new ArrayList<>();
    this.gamelog = new ArrayList<>();
    this.playerDiceRolls = new HashMap<>();
    this.CurrentPositionIndex = 0;
    this.Gameover = false;
    this.winPlayer = null;


    for (Player p : gameplayers){
        p.resetfornewGame(); // seting player at zero 
        p.setActive(true); 
        this.player.add(p); // adding the player  in the game 
        this.playerDiceRolls.put(p.getName(), new ArrayList<>()); // creating the array list for the player  name = key  left to give value

    }
    // message to log
    log("Game start with" + player.stream().map(Player::getName).collect(Collectors.joining(", ")));

}
   // taking msg to log
    private void log(String message){
        gamelog.add(message); // adding to list
        
    }


//getters 
public Player getcurrentPlayer(){
     return player.get(CurrentPositionIndex);
}



//dice
public Map<String, List<Integer>> getpalyerdiceroll(){
 return playerDiceRolls;
}


    /**
     * @return
     */
    public String playturn(){

        if(Gameover){ 
            return "Game is over.";
        
         }
        else{

            Player currentPlayer = getcurrentPlayer(); // taking palyer position 

            int diceroll = dice.roll();                 // taking random number of 1 - 6

            playerDiceRolls.get(currentPlayer.getName()).add(diceroll);  //  adding the number of dice 

            log(currentPlayer.getName() +"roll dice" + diceroll + ".");
            int CurrentPosition = currentPlayer.getCurrentPosition();
            if(CurrentPosition == 0){
                CurrentPosition = 1;

            }
            else{
                CurrentPosition = currentPlayer.getCurrentPosition();
            }
            
            int newPosition =  CurrentPosition +  diceroll;
            
            if(newPosition > Board.BOARD_SIZE){
                log(currentPlayer.getName() + "needs " + (Board.BOARD_SIZE -currentPlayer.getCurrentPosition()) + "to win game" + diceroll + ". Stays at" + currentPlayer.getCurrentPosition());
            }
            else{
                currentPlayer.setCurrentPosition(newPosition);
                log(currentPlayer.getName() +" " + CurrentPosition + " to " + newPosition);
                    // now check the palyer is in the sanke or ladder
                int finalPosition = Board.getDestination(currentPlayer.getCurrentPosition());

                //checking now if there was snake and ladder or not
                if(finalPosition != currentPlayer.getCurrentPosition()){
                    if(board.isSankeHead(currentPlayer.getCurrentPosition())){
                        //log
                        log("ohhh NOOOOOO! " +currentPlayer.getName()+ "step at sanke " + currentPlayer.getCurrentPosition() +" eaten by snake now: " +finalPosition +"."  );
                        currentPlayer.incrementSnakeHit();
                    }
                    else if(board.isLadderBottom(currentPlayer.getCurrentPosition())){
                        log("ohh yehh! I  found ladder at" + currentPlayer.getCurrentPosition() + "i reach to: " +  finalPosition );
                    }
                    currentPlayer.setCurrentPosition(finalPosition);
                }
                
            }


        }
       


        
    }


}
 