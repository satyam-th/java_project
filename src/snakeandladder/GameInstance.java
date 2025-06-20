import java.util.*;
import java.util.stream.*;

public class GameInstance {
    private Dice dice;
    private List<Player> player; 
    private int CurrentPositionIndex;
    private boolean Gameover;
    private Player winPlayer;
    private List<String> gamelog;
    private Map<String, List<Integer>> playerDiceRolls; // to store the data of 
    private  Board board;

        public GameInstance(List<Player> gameplayers){
             this.board = new Board();
             this.player = new ArrayList<>();
             this.gamelog = new ArrayList<>();
             this.playerDiceRolls = new HashMap<>();
             this.CurrentPositionIndex = 0;
             this.Gameover = false;
             this.winPlayer = null;
             this.dice = new Dice();


            for (Player p : gameplayers){
                p.resetfornewGame(); // seting player at zero 
                p.setActive(true); 
                this.player.add(p); // adding the player  in the game 
                this.playerDiceRolls.put(p.getName(), new ArrayList<>()); // creating the array list for the player  name = key  left to give value

            }   
                // message to log
             log("Game start with" + player.stream().map(Player::getName).collect(Collectors.joining(", ")));
          
        }
       



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
                    int finalPosition = Board.getDestination(currentPlayer.getCurrentPosition()); // this should not give a problem

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


            if(board.isWinnnerPosition(currentPlayer.getCurrentPosition())){

                winPlayer = currentPlayer;
                Gameover = true;
                currentPlayer.setWins();
                for(Player p :player){  // seting other palyer to loss
                    if(p != winPlayer){
                        p.setLosses();
                        p.setActive(false);
                    }
                    log(currentPlayer.getName()+ " is  winner of this game!!");
                }
                return currentPlayer.getName() + "wins the match";
            }

        
            if(!Gameover){
                CurrentPositionIndex = (CurrentPositionIndex + 1) % player.size();
            }

         return gamelog.get(gamelog.size() -1); // last msg for game log
          }
        }



        // taking msg to log
         private void log(String message){
             gamelog.add(message); // adding msg to list 
        
        }


  //getters 
  public List<String> getGamelog(){
    return new ArrayList<>(gamelog);
  }
    public Player getcurrentPlayer(){
         return player.get(CurrentPositionIndex);
    }   
    public List<Player> getPlayers(){
         return player; 
        }
    public boolean isgameover(){
        return Gameover;
    }
    public Player getwinner(){
         return winPlayer;
    }
    public Board getBoard() {
        return board;   
    }
    public Map<String, List<Integer>>  getpalyerdiceroll(){
        return playerDiceRolls;
    }
}

 