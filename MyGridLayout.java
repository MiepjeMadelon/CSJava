
/**
 * Deze class maakt en runt het scherm, alle componenten worden in deze class op het scherm gezet
 *
 * @Madelon
 * @1.1
 */
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;  
import java.util.HashMap; 
import java.util.Map;
import java.util.ArrayList;
public class MyGridLayout extends JLayeredPane implements ActionListener{  
    int columns;
    int rows;
    int numButtons;
    int newValue;
    Spot control;
    JPanel top;
    MyGridLayout(int height, int width){    
        columns = width;
        rows = height;
        numButtons = width*height;
         
        String ButtonName = "b";
        int i = 0;
        int j = 0;
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(rows, columns)); 
        top.setSize(300,300);
        //setting grid layout of 3 rows and 3 columns 
        Map<String, Spot> buttons = new HashMap<>();
        for (i = 0; i < numButtons; i++) {
          Spot btn = new Spot(i);
              btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    btn.setVisible(false);
                    
                }
            });
          buttons.put(btn.getID(), btn);
          top.add(btn);
        }
        Map<String, JLabel> ButtonValues = new HashMap<>();
        for (j = 0; j < numButtons; j++){
           Spot current = buttons.get(Integer.toString(j));
            if (!(current.getValue() == "B")) {
                int[] checkID = new int[8];
                checkID[0] = j-columns-1;
                checkID[1] =  j-columns;
                checkID[2] = j-columns+1;
                checkID[3] = j-1;
                checkID[4] = j+1;
                checkID[5] = j+columns-1;
                checkID[6] = j+columns;
                checkID[7] = j+columns+1;
                int k;
                    for (k = 0; k < 8; k++) {
                        if (!(checkID[k] < 0) && !(checkID[k] > (numButtons-1))){
                            Spot control = buttons.get(Integer.toString(checkID[k]));
                            if (control.getValue() == "B") {
                                newValue = newValue + 1;
                            }
                        }
                    }
                current.changeValue(Integer.toString(newValue));
            }
           
        }
        
        add(top);
      
        setSize(300,300);  
        setVisible(true);  
    }  
    public static void main(String[] args) {  
        new MyGridLayout(16,33);  
    }
    
    public void actionPerformed( ActionEvent e ) {
       
    }
}  