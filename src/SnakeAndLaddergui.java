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

private Board boardDefinition;
private GameLogManager gameLogManager = new GameLogManager();

private JLabel currentpalyershow;
private JLabel diceimg;
private JButton rolldice;
private JButton resetgame;
private JTextArea gamelogArea;



public SnakeAndLaddergui(){
    boardDefinition = new Board();
    createguiinterface();
    
}

private void createguiinterface(){

                frame = new JFrame("Snake and Ladder");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //for the gap each other
                frame.setLayout(new BorderLayout(10,10));


                        // in the frame
                boardPanel = new BoardGUI(boardDefinition);

                frame.add(boardPanel,BorderLayout.CENTER);
                //back button
                JButton backButton = new JButton("<-Back");
                backButton.setBounds(10, 10, 80, 30);

               
    // control panel setup
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
    controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    controlPanel.add(backButton);
     //action for back
          backButton.addActionListener(e -> {
            StartMain startMain = new StartMain();
            frame.dispose(); // Close the search GUI
        });



    currentpalyershow = new JLabel("starting a game");
    controlPanel.add(currentpalyershow);
     

    // now for dice
        rolldice = new JButton("click to roll dice");
        rolldice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                actionfordice();
            }
        }
           

            
         );
        controlPanel.add(rolldice);

        diceimg = new JLabel();



        controlPanel.add(Box.createHorizontalStrut(10));
          resetgame = new JButton("RESET GAME");
          
          resetgame.addActionListener(e -> startnewgame());
          controlPanel.add(resetgame);



          //log area
          JLabel logLabel = new JLabel("Game Log:");
          controlPanel.add(logLabel);
            gamelogArea = new JTextArea(20, 30);
            gamelogArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(gamelogArea);
            controlPanel.add(scrollPane);




    frame.add(controlPanel, BorderLayout.EAST);



                
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);

                  


}


      

        //action for dice
        private void actionfordice(){
            if(currentgame != null && !currentgame.isgameover()){  // isgameover should be false to run

                String trunmsg =currentgame.playturn();
                //for log
                msgshowlog(trunmsg);
                //
                updategameplay();
                
                //reprint the board 
                boardPanel.repaint();

                if(currentgame.isgameover()){
                    rolldice.setEnabled(false);
                    Player winner = currentgame.getwinner();
                    msgshowlog(trunmsg + "\n" + winner.getName() + " is winner of this game!!");
                    JOptionPane.showMessageDialog(frame, winner.getName() + " is winner of this game!!", "gameover", JOptionPane.INFORMATION_MESSAGE);
                    gameLogManager.savegamelog(currentgame);
                    currentgame = null; // reset current game
                }

            }




        }


       
        

        //for the palyer name should be update
        private void updategameplay(){
             if(currentgame != null && !currentgame.isgameover()){  
                    Player currnetplayer = currentgame.getcurrentPlayer();

                    //showing current player
                    currentpalyershow.setText("Roll dice: " + currnetplayer.getName());
                    currnetplayer.setColor(currnetplayer.getColor());


             }

             else if(currentgame !=null && currentgame.isgameover()){ //for the winner
                currentpalyershow.setText("Game is over. winnner of this game :" + currentgame.getwinner().getName());

             }
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

        //rule for the game with msg
        String rule = "<html>1.palyer has to roll dice first to show the palyer.<br> 2.snake and ladder work snake eat you and ladder help you to go up.<br> 3.comming soon another palyer can also kill you and kill palyer will go to 0 palyer has to roll dice to  show their dice";
        JOptionPane.showMessageDialog(frame, rule,
         "Rule", JOptionPane.INFORMATION_MESSAGE);


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
        rolldice.setEnabled(true);
        gamelogArea.setEnabled(true);
        gamelogArea.setText(""); // Clear previous logs
        msgshowlog("Game started with " + Currentplayerset.size() + " players.");
    }




        public void msgshowlog(String message) {
           gamelogArea.append(message + "\n");
           gamelogArea.setCaretPosition(gamelogArea.getDocument().getLength());
        }



}



