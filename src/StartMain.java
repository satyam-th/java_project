import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartMain extends JFrame {

    private JButton playButton;
    private JButton searchButton;
    private JButton exitButton;
    private JLabel backgroundLabel;

    public StartMain() {
        setTitle("Snake and Ladder");
        setSize(400, 435);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // using absolute layout as in original

        playButton = new JButton("PLAY GAME");
        playButton.setBounds(210, 230, 159, 30);
        playButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
        // playButton.addActionListener(e -> playGame()); //lambda
        add(playButton);
      // non lambda 
        playButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        playGame();
             }
          });
        searchButton = new JButton("SEARCH");
        searchButton.setBounds(210, 290, 159, 30);
        searchButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
        searchButton.addActionListener(e -> searchButton());
        add(searchButton);

        exitButton = new JButton("EXIT");
        exitButton.setBounds(210, 350, 159, 30);
        exitButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
        exitButton.addActionListener(e -> System.exit(0));
  

        add(exitButton);

        // Set a img
        ImageIcon bgIcon = new ImageIcon("C:\\Users\\satya\\Desktop\\SnakeAndLadder\\img\\firstpage.jpg");
        backgroundLabel = new JLabel(bgIcon);
        backgroundLabel.setBounds(0, 0, 390, 400);
        add(backgroundLabel);

        setLocationRelativeTo(null);  // focing to frame come in center
        setVisible(true);
    }

    private void playGame() {
     
      SnakeAndLaddergui gotostart = new SnakeAndLaddergui();

        gotostart.run(); 
         gotostart.startnewgame();  
        setVisible(true);             // show it
         dispose();
              
    }
    
       private void searchButton() {
        
        System.out.println("search  on terminal");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartMain::new);
    }
}