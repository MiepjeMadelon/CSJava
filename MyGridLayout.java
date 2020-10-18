
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
import javax.swing.SwingUtilities;
import java.lang.*;
public class MyGridLayout extends JLayeredPane implements ActionListener {
    int columns; //we werken met een gridlayout, hiervoor zijn kolommen en rijen nodig
    int rows;//we werken met een gridlayout, hiervoor zijn kolommen en rijen nodig
    int numButtons; //het totaal aantal cellen (dus het totaal aantal buttons) in de grid
    JPanel top; //van het LayeredPane de twee na hoogste laag, namelijk de knoppen.
    JPanel bottom; //van het LayeredPane de laagste laag, namelijk de waardes
    Map<String, Spot> buttons; //de map waarin alle vakjes worden opgeslagen op basis van hun ID
    int numBombs; //om te tellen hoeveel bommen er totaal in het spel zijn
    int numClickToWin; //om te tellen hoevaak iemand op een leeg vakje moet klikken zodat er alleen nog bommen over zjin
    int numClicks; //om te tellen hoevaak een persoon geklikt heeft
    MyGridLayout(int height, int width) {
        columns = width;
        rows = height;
        numButtons = width*height;
        int sizeHeight = 30*rows;
        int sizeWidth = 30*columns;
        int i = 0; // definieërt de i voor de loop van de buttons
        int j = 0; // definieërt de j voor de loop van de waardes
        top = new JPanel();
        top.setLayout(new GridLayout(rows, columns)); //zorgt ervoor dat de knoppen op vaste plekken komen.
        top.setOpaque(false); //zorgt ervoor dat wanneer een knopje wordt ingedrukt de lagen eronder zichtbaar zijn.
        top.setSize(sizeWidth,sizeHeight);
        bottom = new JPanel();
        bottom.setLayout(new GridLayout(rows, columns));
        bottom.setSize(sizeWidth,sizeHeight);
        /* -- Loop 1: de knoppen --
        * In deze loop maken we voor elke cel een nieuwe instantie van spot aan.
        *We geven deze ook een nieuwe waarde, met een kans van een op 10 dat deze een bom is.
        *We slaan elk nieuw object op in een nieuwe map, waardoor we elk object op basis van hun ID
        *terug kunnen vinden, en zodat we deze kunnen koppelen aan een JLabel.
        */
        buttons = new HashMap<>();//de hashmap waarin de knoppen worden opgeslagen.
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
          /*
          hier krijgt elke knop een mouselistener om de vlaggen bij te houden
          */
          btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) { // if right click
                  if(btn.getHasFlag()){
                    btn.setText("");
                    btn.changeHasFlag();
                  } else {
                    btn.setText("F");
                    btn.changeHasFlag();
                  }
                }
            }
          });
          btn.addActionListener( this );
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
                checkID[1] = j-columns;
                checkID[2] = j-columns+1;
                checkID[3] = j-1;
                checkID[4] = j+1;
                checkID[5] = j+columns-1;
                checkID[6] = j+columns;
                checkID[7] = j+columns+1;
                int k;
                int newValue = 0;
                    for (k = 0; k < 8; k++) { //loopt door de acht mogelijke andere vakjes heen
                        if (!(checkID[k] < 0) && !(checkID[k] > (numButtons-1))){ //checkt of het vakje bestaat
                            Spot control;
                            control = buttons.get(Integer.toString(checkID[k])); //pakt het vakje dat om het current vakje heen ligt
                            if (control.getValue() == "B") { //controleert of het een bom is
                                newValue = newValue + 1; //verhoogt de waarde met een.
                            }
                          }
                        }
                current.changeValue(Integer.toString(newValue)); //verandert de waarde van de spot.
                if (newValue == 0) {
                  current.changeValue("");
                }
              } else {
                numBombs = numBombs + 1;
              }
              //Maakt de JLabel met de value als tekst, stopt hem in de map en stopt hem in de JPanel
              JLabel ButtonValue = new JLabel();
              ButtonValue.setText(current.getValue());
              ButtonValues.put(Integer.toString(j), ButtonValue);
              bottom.add(ButtonValue);
              ButtonValue.setHorizontalAlignment(JLabel.CENTER);
          }
          numClickToWin = numButtons - numBombs;
          numClicks = 0;
          add(top, Integer.valueOf(3)); //voegt de laag met de knoppen toe aan de JLayeredPane
          add(bottom, Integer.valueOf(2)); //voegt de laag met de waardes toe aan de JLayeredPane
          setSize(sizeWidth,sizeHeight);
          setVisible(true);


    }

    public void actionPerformed( ActionEvent e ) {
      //for loop die door alles loopt om te kijken welke knop is ingedrukt
      int l;
      Spot ebtn;
      for (l = 0; l < numButtons; l++) {
        //controleren of de huidige cell de juiste knop bevat
        if(e.getSource() == buttons.get(Integer.toString(l))) {
          ebtn = buttons.get(Integer.toString(l));
          if(!(ebtn.getHasFlag())){
            ebtn.setVisible(false); //wanneer er op de knop gedrukt wordt is deze niet meer zichtbaar
            numClicks = numClicks + 1;
            if (ebtn.getValue() == "B") {
              ebtn.setVisible(false);
              top.setVisible(false);
              JOptionPane.showInputDialog(null, "Results", "You've lost :(");

            } else if(numClicks == numClickToWin) {
              JOptionPane.showInputDialog(null, "Results", "You've won :D");
              ebtn.setVisible(false);
              top.setVisible(false);
            }

            //code to make stuff dissapear if it is a ""
            if (ebtn.getValue() == "") {
              int[] checkID = new int[8];
              checkID[0] = l-columns-1;
              checkID[1] = l-columns;
              checkID[2] = l-columns+1;
              checkID[3] = l-1;
              checkID[4] = l+1;
              checkID[5] = l+columns-1;
              checkID[6] = l+columns;
              checkID[7] = l+columns+1;
              int k;
              for (k = 0; k < 8; k++) { //loopt door de acht mogelijke andere vakjes heen
                  if (!(checkID[k] < 0) && !(checkID[k] > (numButtons-1))){ //checkt of het vakje bestaat
                      Spot control;
                      control = buttons.get(Integer.toString(checkID[k])); //pakt het vakje dat om het current vakje heen ligt
                      control.setVisible(false);
                      while (control.getValue() == "") {
                        try
                        {
                          int m = Integer.parseInt(control.getID());
                          int[] checkID2 = new int[8];
                          checkID2[0] = m-columns-1;
                          checkID2[1] = m-columns;
                          checkID2[2] = m-columns+1;
                          checkID2[3] = m-1;
                          checkID2[4] = m+1;
                          checkID2[5] = m+columns-1;
                          checkID2[6] = m+columns;
                          checkID2[7] = m+columns+1;
                          int n;
                          for (n = 0; n < 8; n++) {
                            control = buttons.get(Integer.toString(checkID2[n])); //pakt het vakje dat om het current vakje heen ligt
                            control.setVisible(false);
                          }
                        }
                        catch (NumberFormatException nfe)
                        {
                          System.out.println("NumberFormatException: " + nfe.getMessage());
                          break;
                        }

                      }
                    }
                  }
            }
          }

        }
      }
    }
}
