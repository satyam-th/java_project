import java.awt.Color;

 /*
 * @author satyam
 */
public class Player {
  String Name;
  Color color;
  int CurrentPosition;
  int SnakeHit;
  int LadderUP;
  int Wins;
  int Losses;
  boolean isActive;

public Player(String name, Color color) {
  this.Name = name;
  this.color = color;
  this.CurrentPosition = 0;
  this.SnakeHit = 0;
  this.LadderUP = 0;
  this.Wins = 0;
  this.Losses = 0;
  this.isActive = false;
  //calling methods
  resetfornewGame();

}
public void resetfornewGame(){
  this.CurrentPosition =0;
}
//getters
  public String getName() {
  return Name;
}
public Color getColor() {
  return color;
} 
public int getCurrentPosition() {
  return this.CurrentPosition;
}
public int getwins() {
  return this.Wins;
}
public int getLosses() {
  return this.Losses;
}
public boolean isActive() {
  return isActive; // Assuming you want to check if the player is active this is flase by default
}
public int getSnakeHit() {
  return this.SnakeHit;
}
public int getLadderUP() {
  return this.LadderUP;
}
//setters
public void setName(String name) {
  this.Name = name;
}
public void setCurrentPosition(int position) {
  this.CurrentPosition = position;
}
public void setColor(Color color) {
  this.color = color;
}
public void setWins() {
  this.Wins++;
}
public void setLosses() {
  this.Losses++;
}
public void incrementSnakeHit() {
  this.SnakeHit++;
}
public void incrementLadderup() {
  this.LadderUP++;
}
public void setActive(boolean isActive) {
  this.isActive = isActive; // Set the player's active status
}


// for log and the game save file
@Override
public String toString() {
  return "Player{" +
          "Name='" + Name + '\'' +
          ", color=" + color +
          ", CurrentPosition=" + CurrentPosition +
          ", SnakeHit=" + SnakeHit +
          ", LadderUP=" + LadderUP +
          ", Wins=" + Wins +
          ", Losses=" + Losses +
          ", isActive=" + isActive +
          '}';

}
}


