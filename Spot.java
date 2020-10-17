
/**
 * Deze class is voor de vierkantjes van het scherm waar je op kan klikken. Hier wordt vastgesteld welke vakjes allemaal bommen zijn.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Spot extends JButton implements ActionListener{
    private String value;
    int spotID;
    Spot(int id){
        spotID = id;
        String value = new String();
        addActionListener( this );

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
