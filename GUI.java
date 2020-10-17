// Opstartklasse voor applicatie met paneel
// in dit geval een inhoud applicatie
import javax.swing.*;

class GUI extends JFrame {
  public GUI() {
    setContentPane( new MyGridLayout(16,33) );
  }

  public static void main( String args[] ) {
    JFrame frame = new GUI();
    frame.setLayout(null);
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.setSize( 600, 200 );
    frame.setTitle( "Inhoud kubus, bol of cilinder berekenen" );
    frame.setVisible( true );
  }
}
