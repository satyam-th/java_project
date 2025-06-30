    import java.awt.Color;
import java.awt.color.*;
    
    public class palyerColor {
    private static final Color[] colors ={
        Color.red.darker(), Color.BLUE.darker(), Color.GREEN.darker() , Color.YELLOW.darker()
    };
    
    public static Color getnextColor(int playeri){
        return colors[playeri];
    }
}
