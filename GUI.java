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
       } catch(NumberFormatException nferij) {
         JOptionPane.showMessageDialog(
            this,
            "Uw invoer " + Trijen + " is geen getal",
            "Invoerfout",
            JOptionPane.ERROR_MESSAGE
         );
       }
       try {
         kolom = Integer.parseInt(TKolommen);
       } catch(NumberFormatException nfekolom) {
         JOptionPane.showMessageDialog(
            this,
            "Uw invoer " + TKolommen + " is geen getal",
            "Invoerfout",
            JOptionPane.ERROR_MESSAGE
         );
       }
      dispose();
      new GUIMinesweeper(rij,kolom);
     }

   }

 }
