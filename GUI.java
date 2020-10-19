/**
 *Het scherm waar de applicatie op draait
 *
 * @Madelon
 * @1.0
 */
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;

 class GUI extends JFrame implements ActionListener{
   //JFrame f;
   JPanel info;
   JButton start;
   JTextField rijen;
   JTextField kolommen;
   public GUI() {
     //f = new JFrame();
     info = new JPanel();
     info.setSize(300,200);
     JLabel Rijennum   = new JLabel( "Voer het aantal rijen in" );
     rijen   = new JTextField( 10 );
     JLabel Kolommennum   = new JLabel( "Voer het aantal kolommen in" );
     kolommen    = new JTextField( 10 );
     start = new JButton("Start Minesweeper");

     start.addActionListener(this);
     add(info);
     info.add(Rijennum);
     info.add(rijen);
     info.add(Kolommennum);
     info.add(kolommen);
     info.add(start);
     setSize(600,600);
   }

   public static void main( String args[] ) {
     int height = 16;
     int width = 33;
     JFrame frame = new GUI();
     /*MyGridLayout mnswp = new MyGridLayout(height,width);
     frame.add(mnswp);*/
     frame.setLayout(null);
     frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
     //frame.setSize( width*32 , height*32 );
     frame.setTitle( "Minesweeper" );
     frame.setVisible( true );
   }
   public void actionPerformed( ActionEvent e ) {
     if(e.getSource() == start) {
       System.out.println("click");
       String Trijen = rijen.getText();
       String TKolommen = kolommen.getText();
       int rij = 16;
       int kolom = 33;
       try {
         rij = Integer.parseInt(Trijen);
         if (rij > 20) {
           rij = 16;
           throw new TeGrootException();
         }

       } catch(NumberFormatException nferij) {
         rij = 16;
         JOptionPane.showMessageDialog(
            this,
            "Uw invoer " + Trijen + " is geen getal, het is aangepast naar 16",
            "Invoerfout",
            JOptionPane.ERROR_MESSAGE
         );
       } catch(TeGrootException tgerij) {
         JOptionPane.showMessageDialog(
            this,
            "Uw invoer " + Trijen + " is te groot, de maximale waarde is 20. Het is aangepast naar 16",
            "Invoerfout",
            JOptionPane.ERROR_MESSAGE
         );

       }
       try {
         kolom = Integer.parseInt(TKolommen);
         if (kolom > 40) {
           kolom = 33;
           throw new TeGrootException();
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

       }
      dispose();
      new GUIMinesweeper(rij,kolom);
     }

   }

 }
