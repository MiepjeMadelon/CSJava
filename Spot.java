
/**
 * Deze class is voor de vierkantjes van het scherm waar je op kan klikken. Hier wordt vastgesteld welke vakjes allemaal bommen zijn.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;  
import java.awt.event.*;
import javax.swing.*;  
import java.util.SplittableRandom;
  
public class Spot extends JButton implements ActionListener{  
    private String value = "_";
    int spotID;
    Spot(int id){
        spotID = id;
        SplittableRandom random = new SplittableRandom();
        int chance = random.nextInt(20);
        String value = new String();
        value = "_";
        addActionListener( this );
        if (chance < 4) {
            value="B";
        }
    }
    
    public void changeValue(String ValueName) {
        value = ValueName;
    }
    
    public String getValue() {
        return value;
    }
    public String getID() {
        return  Integer.toString(spotID);
    }
    public void actionPerformed( ActionEvent e ) {
        
    }
}  