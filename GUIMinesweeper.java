/**
 *Het scherm waar de applicatie op draait
 *
 * @Madelon
 * @1.0
 */
import javax.swing.*;
//outdated versie!
class GUIMinesweeper extends JFrame {
  JPanel game;
  int width;
  int height;
  public GUIMinesweeper(int rows, int columns) {
    height = rows;
    width = columns;
    setContentPane( new MyGridLayout(height, width) );
    setLayout(null);
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    //frame.setSize( width*32 , height*32 );
    setTitle( "Minesweeper" );
    setVisible(true);
    setSize(600,600);
  }

  public static void main( String args[] ) {
    JFrame frame = new GUIMinesweeper(16, 33);
  }
}
