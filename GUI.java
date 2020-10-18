/**
 *Het scherm waar de applicatie op draait
 *
 * @Madelon
 * @1.0
 */
import javax.swing.*;
//outdated versie!
class GUI extends JFrame {
  public GUI() {
    /*geeft de grootte van de map*/
  }

  public static void main( String args[] ) {
    int height = 16;
    int width = 33;
    JFrame frame = new GUI();
    MyGridLayout mnswp = new MyGridLayout(height,width);
    frame.add(mnswp);
    frame.setLayout(null);
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.setSize( width*32 , height*32 );
    frame.setTitle( "Minesweeper" );
    frame.setVisible(true);
  }
}
