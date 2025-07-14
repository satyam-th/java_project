import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameLogManager {
    

    public void savegamelog(GameInstance game){
        if(!game.isgameover()){
            return ; //  notthing to wirte 

        }

    else{

        String gametime = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date());
        String filename = "Your_Game_Record_" + gametime + ".txt";



        // create a new file with the game time
        // if the file already exists, it will be overwritten
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))){
            // writing into text file
            writer.println("Game date: " + gametime);

            writer.println("Your filename: " + filename);
            
            writer.println("Winner: " + game.getwinner().getName());
            writer.println("------Players-----");

            for (Player player : game.getPlayers()) {
                writer.println("Player: " + player.getName() + " Position: " + player.getCurrentPosition());
                writer.println("Dice roll: " + game.getpalyerdiceroll().get(player.getName()));
            
            }

            writer.println("full log");

                for(String log : game.getGamelog()){
                    writer.println(log); // writing log
                }
            writer.println("------End of Game Log-----");

            



        }
        catch (Exception e) {
            e.printStackTrace(); // handle exception
        }



        
    
    }
    }



    public List<String> searchname(String name){
        List<String> results = new ArrayList<>();
        
        File folder = new File(".");
       File[] files = folder.listFiles(new FilenameFilter() {
    @Override
    public boolean accept(File dir, String name) {
        return name.startsWith("gamelog") && name.endsWith(".txt");
    }
});

if(files != null) {
    for(File file : files) {
        // search the file for the player's name
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder filecontent  = new StringBuilder();
            boolean found = false;
            while((line = reader.readLine()) != null) {
                filecontent.append(line).append("\n");
                if (line.contains(name)) {
                    found = true; // name found in this file
                }
            }

            if (found) {
                results.add(file.getName() + ": " + filecontent.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

return results;

}
  

}
