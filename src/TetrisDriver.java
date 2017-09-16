import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Main driver class for Tetris
 *
 * @author David Hunsaker
 */
public class TetrisDriver {

    public static void main(String[] args) {
        GameState Tetris = new GameState();

        JFrame title = new JFrame("Tetris");
        title.setPreferredSize(new Dimension(1000, 1000));
        title.getContentPane().setBackground(Color.BLACK);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        Border whiteLine, greyLine;
        whiteLine = BorderFactory.createLineBorder(Color.WHITE);
        greyLine = BorderFactory.createLineBorder(Color.lightGray);

        panel1.setBackground(Color.WHITE);
        panel1.setBorder(whiteLine);
        panel1.setPreferredSize(new Dimension(500, 800));
        panel1.setLayout(new GridLayout(20, 10));

        panel2.setBackground(Color.BLACK);
        panel2.setBorder(whiteLine);
        panel2.setPreferredSize(new Dimension(200, 200));
        panel2.setLayout(new GridLayout(4, 4));

        JPanel[][] panel3 = Tetris.panels;
        JPanel[][] nextBlockPanel = Tetris.nextBlockPanel;

        title.setLayout(new FlowLayout());

        title.add(panel1);
        title.add(panel2);


        for (int i = 0; i < nextBlockPanel.length; i++) {
            for (int j = 0; j < nextBlockPanel[0].length; j++) {

                nextBlockPanel[i][j] = new JPanel();
                nextBlockPanel[i][j].setBorder(greyLine);
                panel2.add(nextBlockPanel[i][j]);

            }
        }

        for (int i = 0; i < panel3.length; i++) {
            for (int j = 0; j < panel3[0].length; j++) {
                panel3[i][j] = new JPanel();
                panel3[i][j].setBorder(greyLine);
                panel1.add(panel3[i][j]);
            }
        }


        title.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        title.pack();

        title.setLocationRelativeTo(null);
        title.setVisible(true);

        Tetris.drawBoard();
        Tetris.drawNextBoard();

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                Tetris.eraseBlock();

                if (e.getKeyCode() == KeyEvent.VK_X) {
                    if (Tetris.isRotateValid(1)) {
                        Tetris.eraseBlock();
                        Tetris.checkLine();
                        Tetris.currentBlock.rotateRight();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_Z) {
                    if (Tetris.isRotateValid(-1)) {
                        Tetris.eraseBlock();
                        Tetris.checkLine();
                        Tetris.currentBlock.rotateLeft();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (Tetris.isValid(1, 0)) {
                        Tetris.eraseBlock();
                        Tetris.checkLine();
                        Tetris.currentBlock.moveRight();
                    }

                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (Tetris.isValid(-1, 0)) {
                        Tetris.eraseBlock();
                        Tetris.checkLine();
                        Tetris.currentBlock.moveLeft();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    Tetris.eraseBlock();
                    if (Tetris.isValid(0, 1)) {
                        Tetris.eraseBlock();
                        Tetris.checkLine();
                        Tetris.currentBlock.moveDown();
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        if (Tetris.isRotateValid(1)) {
                            Tetris.eraseBlock();
                            Tetris.checkLine();
                            Tetris.currentBlock.rotateRight();
                        }
                    } else {
                        Tetris.checkLine();
                        Tetris.drawBlock();
                        Tetris.currentBlock = Tetris.nextBlock;
                        Tetris.nextBlock = new TetrisBlock();
                        Tetris.drawNextBlock();
                        Tetris.drawNextBoard();
                    }
                }

                Tetris.checkLine();
                Tetris.drawBlock();
                Tetris.drawBoard();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        title.addKeyListener(keyListener);
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.eraseBlock();

                if (Tetris.isValid(0, 1)) {
                    Tetris.checkLine();
                    Tetris.currentBlock.moveDown();
                    Tetris.drawBlock();
                    Tetris.drawBoard();
                } else {
                    Tetris.checkLine();
                    Tetris.drawBlock();
                    Tetris.currentBlock = Tetris.nextBlock;
                    Tetris.nextBlock = new TetrisBlock();
                    Tetris.drawBlock();
                    Tetris.drawBoard();
                    Tetris.drawNextBlock();
                    Tetris.drawNextBoard();
                }
            }
        };

        Timer timer = new Timer(1000, listener);
        timer.start();

    }

}
