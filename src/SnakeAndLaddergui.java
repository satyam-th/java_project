import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
public class SnakeAndLaddergui {
private JFrame frame;
private List<Player> Currentplayerset = new ArrayList<>();
private GameInstance currentgame;
private BoardGUI boardPanel;


 public void run() {
                frame = new JFrame("Snake and Ladder");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Board boardDefinition = new Board();
                BoardGUI boardGUI = new BoardGUI(boardDefinition);

                frame.add(boardGUI);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
            }
    public void startnewgame(){
        // if the last game was stored then remove
        Currentplayerset.clear();
        List<String> names = new ArrayList<>(); // this should be more than 2 players

        Integer[] options = {2, 3, 4};
        int selectedOption = JOptionPane.showOptionDialog(
            frame,
            "Number of players you want to play",
            "Players",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        // If user closes dialog or presses cancel, exit method
        if (selectedOption < 0) {
            return;
        }

        int numberofplayer = options[selectedOption];

        // taking input of player name and setting into names
        for (int i = 0; i < numberofplayer; i++) {
            String name = null;
            do {
                name = JOptionPane.showInputDialog(frame, "Enter " + (i + 1) + " player name:", "Player " + (i + 1));
                if (name == null) return; // user cancelled
                name = name.trim();
            } while (name.isEmpty());
            names.add(name);
        }

        // setting player name for collected for object
        for (int i = 0; i < numberofplayer; i++) {
            Player pget = new Player(names.get(i), palyerColor.getnextColor(i)); // assuming PlayerColor is a class with getNextColor
            Currentplayerset.add(pget);
        }

        currentgame = new GameInstance(Currentplayerset);

        if (boardPanel != null) {
            boardPanel.setCurrentGame(currentgame);
        } else {
            // If boardPanel is not initialized, create it and add to frame
            Board boardDefinition = new Board();
            boardPanel = new BoardGUI(boardDefinition);
            boardPanel.setCurrentGame(currentgame);
            frame.add(boardPanel);
            frame.pack();
        }
    }
}
