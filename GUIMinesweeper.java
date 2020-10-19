/**
 *Het scherm waar de applicatie op draait
 *
 * @Madelon
 * @1.0
 */
import javax.swing.*;
class GUIMinesweeper extends JFrame {
  JPanel game;
  int width;
  int height;
  public GUIMinesweeper(int rows, int columns) {
    height = rows;
    width = columns;
    setContentPane( new MyGridLayout(height, width) ); //dit is wat op het scherm komt
    setLayout(null);
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    setTitle( "Minesweeper" );
    setVisible(true);
    //de grootte past zich aan op basis van de input. De kolommen iets meer, aangezien dat er standaard iets meer zijn
    setSize(columns*33, rows*35);
  }

}
