
/**
 * Deze class is voor de vierkantjes van het scherm waar je op kan klikken.
 * @Madelon
 * @2.5
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Spot extends JButton{
    String value; //de waarde van het vakje
    int spotID; //de ID van het vakje
    Boolean hasFlag;
    Spot(int id){
        spotID = id; //de ID wordt door MyGridLayout gegeven
        String value = new String(); // definieërt de string
        hasFlag = false;
    }

    public void changeValue(String ValueName) { //verandert de Value
        value = ValueName;
    }

    public String getValue() { //geeft de value
        return value;
    }
    public String getID() { //geeft de ID (als String om het in een map op te kunnen bergen)
        return  Integer.toString(spotID);
    }
    public Boolean getHasFlag() {
        return hasFlag;
    }
    public void changeHasFlag() {
        if(hasFlag) {
          hasFlag = false;
        } else {
          hasFlag = true;
        }
    }
    public void hasFlagFalse() {
        hasFlag = false;
    }
    public void hasFlagTrue() {
        hasFlag = true;
    }
}
