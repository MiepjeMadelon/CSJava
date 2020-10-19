/**
 *Het scherm waar de applicatie op draait
 *
 * @author Madelon
 * @version 2.0
 */
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;

 class GUI extends JFrame implements ActionListener{
   //JFrame f;
   JPanel info; //het paneel met alle invoervakjes
   JButton start; //het knopje dat het huidige scherm verwijdert en minesweeper opstart
   JTextField rijen; //het vakje met invoer van de rijen
   JTextField kolommen; //het vakje met invoer van de kolommen
   public GUI() {
     info = new JPanel();
     info.setSize(300,200);
     JLabel Rijennum   = new JLabel( "Voer het aantal rijen in" );
     rijen   = new JTextField( 10 );
     JLabel Kolommennum   = new JLabel( "Voer het aantal kolommen in" );
     kolommen    = new JTextField( 10 );
     start = new JButton("Start Minesweeper");

     start.addActionListener(this); //actionlistener om alle waardes te controleren
     add(info); //het paneel is toegevoegd aan het JFrame
     //alle elementen aan het paneel toevoegen
     info.add(Rijennum);
     info.add(rijen);
     info.add(Kolommennum);
     info.add(kolommen);
     info.add(start);
     //de grootte van het JFrame vaststellen.
     setSize(350,175);
   }

   public static void main( String args[] ) {
     //stelt het JFrame in
     JFrame frame = new GUI();
     frame.setLayout(null);
     frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
     frame.setTitle( "Minesweeper" );
     frame.setVisible( true );
   }
   public void actionPerformed( ActionEvent e ) {
     //eerst controleren we of de knop daadwerkelijk is ingedrukt
     if(e.getSource() == start) {
       //vervolgens definieeren we de tekst die is ingevuld in de vlakken als String
       String Trijen = rijen.getText();
       String TKolommen = kolommen.getText();
       //we zetten ook de standaardwaardes vast voor de rijen en kolommen
       int rij = 16;
       int kolom = 33;
       //---RIJ----
       //eerst wordt de input omgezet in een int
       try {
         rij = Integer.parseInt(Trijen);
         if (rij > 20) {
           rij = 16; //zet terug naar standaard
           throw new TeGrootException(); //te groot
         } else if (rij < 5) {
           rij = 16; //zet terug naar standaard
           throw new TeKleinException();  //te klein
         }
       } catch(NumberFormatException nferij) {
         rij = 16; //zet terug naar standaard
         //een popup
         JOptionPane.showMessageDialog(
            this,
            "Uw invoer " + Trijen + " is geen getal, het is aangepast naar 16",
            "Invoerfout",
            JOptionPane.ERROR_MESSAGE
         );
       } catch(TeGrootException tgerij) {
         //een popup
         JOptionPane.showMessageDialog(
            this,
            "Uw invoer " + Trijen + " is te groot, de maximale waarde is 20. Het is aangepast naar 16",
            "Invoerfout",
            JOptionPane.ERROR_MESSAGE
         );
       } catch(TeKleinException tkerij) {
         //een popup
         JOptionPane.showMessageDialog(
            this,
            "Uw invoer " + Trijen + " is te klein, de minimale waarde is 5. Het is aangepast naar 16",
            "Invoerfout",
            JOptionPane.ERROR_MESSAGE
         );
       }
       //----KOLOM----
       //identiek aan rij, maar rij = kolom en standaardwaarde = 33
       try {
         kolom = Integer.parseInt(TKolommen);
         if (kolom > 40) {
           kolom = 33;
           throw new TeGrootException();
         } else if (kolom < 5) {
           kolom = 33;
           throw new TeKleinException();
         }
       } catch(NumberFormatException nfekolom) {
         kolom = 33;
         JOptionPane.showMessageDialog(
            this,
            "Uw invoer " + TKolommen + " is geen getal, het is aangepast naar 33.",
            "Invoerfout",
            JOptionPane.ERROR_MESSAGE
         );
       } catch(TeGrootException tgekolom) {
         JOptionPane.showMessageDialog(
            this,
            "Uw invoer " + TKolommen + " is te groot, de maximale waarde is 40. Het is aangepast naar 33",
            "Invoerfout",
            JOptionPane.ERROR_MESSAGE
         );

       } catch(TeKleinException tkekolom) {
         JOptionPane.showMessageDialog(
            this,
            "Uw invoer " + TKolommen + " is te klein, de minimale waarde is 5. Het is aangepast naar 33",
            "Invoerfout",
            JOptionPane.ERROR_MESSAGE
         );
       }
      dispose(); //verwijdert het huidige scherm
      new GUIMinesweeper(rij,kolom); //maakt het nieuwe scherm met Minesweeper
     }

   }

 }
