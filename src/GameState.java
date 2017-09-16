import javax.swing.*;
import java.awt.*;

/**
 * This class will check to see if the spaces on the board are full or empty.
 * This will also check to see what shape it is, and sets the color accordingly
 *
 * @author David Hunsaker
 */
public class GameState {

    JPanel[][] panels = new JPanel[20][10];
    int[][] board = new int[20][10];
    TetrisBlock currentBlock;
    TetrisBlock nextBlock;

    JPanel[][] nextBlockPanel = new JPanel[4][4];
    int[][] nextBlockBoard = new int[4][4];

    public GameState() {
        TetrisBlock newBlock = new TetrisBlock();
        TetrisBlock block = new TetrisBlock();
        currentBlock = block;
        nextBlock = newBlock;

        for (int i = 0; i < block.getBlock().length; i++) {
            for (int j = 0; j < block.getBlock()[0].length; j++) {
                board[i][j + 3] = block.getBlock()[i][j];
            }
        }
        drawNextBlock();
    }

    public boolean isValid(int x, int y) {
        for (int i = 0; i < currentBlock.getBlock().length; i++) {
            for (int j = 0; j < currentBlock.getBlock()[0].length; j++) {

                if (currentBlock.getBlock()[j][i] != 0) {
                    if (currentBlock.getxAxis() + i + x < 0) {
                        return false;
                    } else if (currentBlock.getxAxis() + i + x > 9) {
                        return false;
                    } else if (currentBlock.getyAxis() + j + y > 19) {
                        return false;
                    } else if (board[currentBlock.getyAxis() + j + y][currentBlock.getxAxis() + i + x] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isRotateValid(int x) {
        if (x == 1) {
            eraseBlock();
            currentBlock.rotateRight();
            if (isValid(0, 0)) {
                currentBlock.rotateLeft();
                return true;
            } else {
                currentBlock.rotateLeft();
                return false;
            }
        }
        eraseBlock();
        if (x == -1) {
            currentBlock.rotateLeft();
            if (isValid(0, 0)) {
                currentBlock.rotateRight();
                return true;
            } else {
                currentBlock.rotateRight();
                return false;
            }
        }
        return true;
    }

    public void checkLine() {
        for (int i = 0; i < board.length; i++) {
            if (lineFull(i)) {
                clearLine(i);
            }
        }
    }

    public boolean lineFull(int x) {
        for (int i = 0; i < board[x].length; i++) {
            if (board[x][i] == 0) {
                return false;
            }
        }
        return true;
    }

    public void clearLine(int x) {
        for (int i = 0; i < board[x].length; i++) {
            board[x][i] = 0;
        }
        for (int j = x; j > 0; j--) {
            for (int k = 0; k < board[j].length; k++) {
                board[j][k] = board[j - 1][k];
            }
        }
        drawBoard();
    }

    public void drawBlock() {
        for (int i = 0; i < currentBlock.getBlock().length; i++) {
            for (int j = 0; j < currentBlock.getBlock()[0].length; j++) {
                if (currentBlock.getBlock()[i][j] != 0) {
                    board[i + currentBlock.getyAxis()][j + currentBlock.getxAxis()] = currentBlock.getBlock()[i][j];
                }
            }
        }
    }

    public void drawNextBlock() {
        for (int i = 0; i < nextBlock.getBlock().length - 1; i++) {
            for (int j = 0; j < nextBlock.getBlock()[0].length; j++) {
                nextBlockBoard[i + 1][j] = nextBlock.getBlock()[i][j];
            }
        }
    }

    public void eraseBlock() {
        for (int i = 0; i < currentBlock.getBlock().length; i++) {
            for (int j = 0; j < currentBlock.getBlock()[0].length; j++) {
                if (currentBlock.getBlock()[i][j] != 0) {
                    board[i + currentBlock.getyAxis()][j + currentBlock.getxAxis()] = 0;
                }
            }
        }
    }

    public void drawBoard() {
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[0].length; j++) {
                if (board[i][j] == 1) {
                    panels[i][j].setBackground(Color.RED);
                } else if (board[i][j] == 2) {
                    panels[i][j].setBackground(Color.MAGENTA);
                } else if (board[i][j] == 3) {
                    panels[i][j].setBackground(Color.YELLOW);
                } else if (board[i][j] == 4) {
                    panels[i][j].setBackground(Color.GREEN);
                } else if (board[i][j] == 5) {
                    panels[i][j].setBackground(Color.BLUE);
                } else if (board[i][j] == 6) {
                    panels[i][j].setBackground(Color.CYAN);
                } else if (board[i][j] == 7) {
                    panels[i][j].setBackground(Color.WHITE);
                } else {
                    panels[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }

    void drawNextBoard() {
        for (int i = 0; i < nextBlockPanel.length; i++) {
            for (int j = 0; j < nextBlockPanel[0].length; j++) {
                if (nextBlockBoard[i][j] == 1) {
                    nextBlockPanel[i][j].setBackground(Color.RED);
                } else if (nextBlockBoard[i][j] == 2) {
                    nextBlockPanel[i][j].setBackground(Color.MAGENTA);
                } else if (nextBlockBoard[i][j] == 3) {
                    nextBlockPanel[i][j].setBackground(Color.YELLOW);
                } else if (nextBlockBoard[i][j] == 4) {
                    nextBlockPanel[i][j].setBackground(Color.GREEN);
                } else if (nextBlockBoard[i][j] == 5) {
                    nextBlockPanel[i][j].setBackground(Color.BLUE);
                } else if (nextBlockBoard[i][j] == 6) {
                    nextBlockPanel[i][j].setBackground(Color.CYAN);
                } else if (nextBlockBoard[i][j] == 7) {
                    nextBlockPanel[i][j].setBackground(Color.WHITE);
                } else {
                    nextBlockPanel[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }
}
