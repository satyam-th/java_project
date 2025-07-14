import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.*;

public class searchgui {
    private final GameLogManager gameLogManager = new GameLogManager();
    private final JFrame frame;

    public searchgui() {
        frame = new JFrame("Search Game Logs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Top panel for search input
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchPanel.add(new JLabel("Enter Player Name: "));
        JButton backButton = new JButton("<-Back");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(backButton);
    
        // Center panel for results
        JTextArea resultArea = new JTextArea(20, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(380, 400));

        // Layout setup
        frame.setLayout(new BorderLayout(10, 10));
        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);




        // Search button action
        searchButton.addActionListener(e -> {
            String playerName = searchField.getText().trim();
            resultArea.setText(""); // Clear previous results
            if (playerName.isEmpty()) {
                resultArea.setText("Please enter a player name.");
                return;
            }
            List<String> results = gameLogManager.searchname(playerName);
            if (results.isEmpty()) {
                resultArea.setText("No logs found for player: " + playerName);
            } else {
                results.forEach(result -> resultArea.append(result + "\n"));
            }
        });
            backButton.addActionListener(e -> {
            StartMain startMain = new StartMain();
            frame.dispose(); // Close the search GUI
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
