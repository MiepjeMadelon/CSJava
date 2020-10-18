/**
 *Het scherm waar de applicatie op draait
 *
 * @Madelon
 * @1.0
 */
import javax.swing.*;

class GUI extends JFrame {
  public GUI() {
    setContentPane( new MyGridLayout(16,33)/*geeft de grootte van de map*/ );
  }

  public static void main( String args[] ) {
    JFrame frame = new GUI();
    frame.setLayout(null);
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.setSize( 600, 200 );
    frame.setTitle( "Minesweeper" );
    frame.setVisible( true );
  }
}
