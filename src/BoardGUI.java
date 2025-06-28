import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class BoardGUI extends JPanel {
    private static final int CELL_SIZE = 60;
    private static final int BOARD_DIM = 10;
    private static final int BOARD_PIXEL_SIZE = CELL_SIZE * BOARD_DIM;

    private GameInstance currentGame;
    private Board boardDef;
    private JLabel backgroundImage;

    public BoardGUI(Board boardDef) {
        this.boardDef = boardDef;
        setPreferredSize(new Dimension(BOARD_PIXEL_SIZE, BOARD_PIXEL_SIZE));
        setLayout(null);
    }

    public void setCurrentGame(GameInstance game) {
        this.currentGame = game;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D sndgraphics = (Graphics2D) g;
        

        // Draw grid
        boolean leftToRight;
        int count = 100;

        for (int row = 0; row < BOARD_DIM; row++) {
            leftToRight = ((BOARD_DIM - 1 - row) % 2 == 0);
            System.out.println(leftToRight);
            for (int col = 0; col < BOARD_DIM; col++) {
                int number = leftToRight ? count - (9 - col) : count - col;
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                // Draw cell border and number
                sndgraphics.setColor(Color.BLACK);
                sndgraphics.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                sndgraphics.drawString(String.valueOf(number), x + 5, y + 15);
            }
            count -= 10;
        }



        

        //background img
        ImageIcon bgIcon = new ImageIcon("C:\\Users\\satya\\Desktop\\SnakeAndLadder\\img\\snd.jpg");
        backgroundImage = new JLabel(bgIcon);
        backgroundImage.setBounds(0, 0, 600, 600);
        add(backgroundImage);


        // for the active player
        if(currentGame != null && currentGame.getPlayers() != null){
            List<Player> players = currentGame.getPlayers();
            for(int i = 0; i<players.size(); i++){
                Player player = players.get(i);

                if(player.getCurrentPosition() > 0){
                    Point playercoordinate =  getCellPixelCoordinates(player.getCurrentPosition());
                    sndgraphics.setColor(player.getColor());
                    int xcoor = (i % 2) * (CELL_SIZE / 2);
                    int ycoor = (i % 2) * (CELL_SIZE / 2);
                    sndgraphics.fillOval(playercoordinate.x + xcoor, ycoor + playercoordinate.y, 25, 25);
                    sndgraphics.drawOval(playercoordinate.x + xcoor, ycoor + playercoordinate.y, 25, 25);
                }
            }
        }
        

    }



    private Point getCellPixelCoordinates(int cellNumber) {
        int row = (cellNumber - 1) / BOARD_DIM;
        int col;
        if (row % 2 == 0) {
            col = (cellNumber - 1) % BOARD_DIM;
        } else {
            col = BOARD_DIM - 1 - ((cellNumber - 1) % BOARD_DIM);
        }
        int x = col * CELL_SIZE + 30;
        int y = (BOARD_DIM - 1 - row) * CELL_SIZE + 25;
        return new Point(x, y);
    }

  }











       
   






