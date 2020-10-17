
/**
 *alle componenten worden in deze class aangemaakt en op de juiste plek gezet
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
import java.util.SplittableRandom;
public class MyGridLayout extends JLayeredPane implements ActionListener{
    int columns; //we werken met een gridlayout, hiervoor zijn kolommen en rijen nodig
    int rows;//we werken met een gridlayout, hiervoor zijn kolommen en rijen nodig
    int numButtons; //het totaal aantal cellen (dus het totaal aantal buttons) in de grid
    int newValue; //op een gegeven moment moeten de vakjes onder de knoppen een waarde krijgen ("", B, of 1-8)
    Spot control; //de plek waarvan we controleren of het huidige vakje in de loop zijn waarde moet worden aangepast
    JPanel top; //van het LayeredPane de twee na hoogste laag, namelijk de knoppen.
    JPanel bottom; //van het LayeredPane de laagste laag, namelijk de waardes
    MyGridLayout(int height, int width){
        columns = width;
        rows = height;
        numButtons = width*height;

        int i = 0; // definieërt de i voor de loop van de buttons
        int j = 0; // definieërt de j voor de loop van de waardes
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(rows, columns)); //zorgt ervoor dat de knoppen op vaste plekken komen.
        top.setOpaque(false); //zorgt ervoor dat wanneer een knopje wordt ingedrukt de lagen eronder zichtbaar zijn.
        top.setSize(300,300);
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(rows, columns));
        bottom.setSize(300,300);
        /* -- Loop 1: de knoppen --
        * In deze loop maken we voor elke cel een nieuwe instantie van spot aan.
        *We geven deze ook een nieuwe waarde, met een kans van een op 10 dat deze een bom is.
        *We slaan elk nieuw object op in een nieuwe map, waardoor we elk object op basis van hun ID
        *terug kunnen vinden, en zodat we deze kunnen koppelen aan een JLabel.
        */
        Map<String, Spot> buttons = new HashMap<>();//de hashmap waarin de knoppen worden opgeslagen.
        for (i = 0; i < numButtons; i++) { //de loop
          //de waarde eventueel naar bom veranderen door een random int te trekken.
          SplittableRandom random = new SplittableRandom();
          int chance = random.nextInt(20);
          String r;
          Spot btn = new Spot(i);
          r = " ";
          if (chance < 2) {
            r = "B";
            btn.changeValue("B");
          } else {
            r = "0";
            btn.changeValue("0");
          }
          //de knop een actionlistener geven zodat deze verdwijnt wanneer erop geklikt wordt.
          btn.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ae) {
                  btn.setVisible(false);
              }
          });
          //de knop in de hashmap zetten
          buttons.put(btn.getID(), btn);
          //de knop in het frame zetten.
          top.add(btn);
        }
        /* -- Loop 2: de waardes --
        * In deze loop zorgen we ervoor dat elke spot de juiste waarde krijgt,
        *door te controleren of een van de omliggende vakjes een bom is.
        *Hierna slaan we alle waarden van de vakjes op in een nieuwe hashmap, met
        *dezelfde ID als de bijbehorende ID van de spot.
        *daarna zetten we alle waarden in een nieuwe laag, identiek aan de vorige,
        *zodat onder elke knop de juiste waarde ligt.
        */
        Map<String, JLabel> ButtonValues = new HashMap<>(); //de hashmap waar de waarden in worden opgeslagen
        for (j = 0; j < numButtons; j++){//de loop
           Spot current = buttons.get(Integer.toString(j)); //haalt uit de hashmap met de spots telkens een object met dezelfde ID als j
            if (!(current.getValue() == "B")) { //alleen als het geen bom is moet de waarde verandert worden.
              //wat rekensommen om ongeacht de grootte van de grid de omliggende acht waardes te vinden.
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
                newValue = 0;
                    for (k = 0; k < 8; k++) { //loopt door de acht mogelijke andere vakjes heen
                        if (!(checkID[k] < 0) && !(checkID[k] > (numButtons-1))){ //checkt of het vakje bestaat
                            Spot control = buttons.get(Integer.toString(checkID[k])); //pakt het vakje dat om het current vakje heen ligt
                            if (control.getValue() == "B") { //controleert of het een bom is
                                newValue = newValue + 1; //verhoogt de waarde met een.
                            }
                          }
                        }
                current.changeValue(Integer.toString(newValue)); //verandert de waarde van de spot.
              }
              //Maakt de JLabel met de value als tekst, stopt hem in de map en stopt hem in de JPanel
              JLabel ButtonValue = new JLabel();
              ButtonValue.setText(current.getValue());
              ButtonValues.put(Integer.toString(j), ButtonValue);
              bottom.add(ButtonValue);
          }

          add(top, Integer.valueOf(2)); //voegt de laag met de knoppen toe aan de JLayeredPane
          add(bottom, Integer.valueOf(1)); //voegt de laag met de waardes toe aan de JLayeredPane
          setSize(300,300);
          setVisible(true);
    }

    public void actionPerformed( ActionEvent e ) {

    }
}
