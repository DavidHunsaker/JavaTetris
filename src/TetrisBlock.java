import java.util.Random;

/**
 * This class creates blocks and determines if block movements are valid or not.
 *
 * @author David Hunsaker
 */
public class TetrisBlock {

    //To create tetris blocks and their corresponding shapes, I determined that a 4D array would be the best
    //Different numbers are used for block color

    private int shape, rotation, xAxis, yAxis;
    private Random rand = new Random();

    private int[][][][] block = new int[][][][]{
            //Line
            {{{1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 1, 0, 0,}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}},
                    {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}},
                    {{0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}}},
            //T
            {{{0, 2, 0, 0}, {2, 2, 2, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 2, 0, 0}, {0, 2, 2, 0}, {0, 2, 0, 0}, {0, 0, 0, 0}},
                    {{0, 2, 2, 2}, {0, 0, 2, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 0, 2, 0}, {0, 2, 2, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}}},
            //L Facing Left
            {{{0, 3, 0, 0}, {0, 3, 3, 3}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 3, 3, 0}, {0, 3, 0, 0}, {0, 3, 0, 0}, {0, 0, 0, 0}},
                    {{0, 3, 3, 3}, {0, 0, 0, 3}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 0, 3, 0}, {0, 0, 3, 0}, {0, 3, 3, 0}, {0, 0, 0, 0}}},
            //L Facing Right
            {{{0, 0, 4, 0}, {4, 4, 4, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 4, 0, 0}, {0, 4, 0, 0}, {0, 4, 4, 0}, {0, 0, 0, 0}},
                    {{4, 4, 4, 0}, {4, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 4, 4, 0}, {0, 0, 4, 0}, {0, 0, 4, 0}, {0, 0, 0, 0}}},
            //Z
            {{{0, 5, 5, 0}, {5, 5, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 5, 0, 0}, {0, 5, 5, 0}, {0, 0, 5, 0}, {0, 0, 0, 0}},
                    {{0, 5, 5, 0}, {5, 5, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 5, 0, 0}, {0, 5, 5, 0}, {0, 0, 5, 0}, {0, 0, 0, 0}}},
            //Backwards Z
            {{{0, 6, 6, 0}, {0, 0, 6, 6}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 0, 6, 0}, {0, 6, 6, 0}, {0, 6, 0, 0}, {0, 0, 0, 0}},
                    {{0, 6, 6, 0}, {0, 0, 6, 6}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                    {{0, 0, 6, 0}, {0, 6, 6, 0}, {0, 6, 0, 0}, {0, 0, 0, 0}}},
            //Square
            {{{0, 7, 7, 0}, {0, 7, 7, 0}, {0, 7, 7, 0}, {0, 7, 7, 0}},
                    {{0, 7, 7, 0}, {0, 7, 7, 0}, {0, 7, 7, 0}, {0, 7, 7, 0}},
                    {{0, 7, 7, 0}, {0, 7, 7, 0}, {0, 7, 7, 0}, {0, 7, 7, 0}},
                    {{0, 7, 7, 0}, {0, 7, 7, 0}, {0, 7, 7, 0}, {0, 7, 7, 0}}}
    };

    /**
     * Constructor that creates a random block centered on the board
     */
    public TetrisBlock() {
        shape = rand.nextInt(block.length);
        rotation = 0;
        xAxis = 3;
        yAxis = 0;
    }

    /**
     * Moves the block to the right
     */
    private void moveRight() {
        xAxis++;
    }

    /**
     * Moves the block to the left
     */
    private void moveLeft() {
        xAxis--;
    }

    /**
     * Moves the block down
     */
    private void moveDown() {
        yAxis++;
    }

    /**
     * Rotates the block to the right and resets the rotation to 0 if it goes beyond 3
     */
    private void rotateRight() {
        rotation++;
        if (rotation > 3) {
            rotation = 0;
        }
    }

    /**
     * Rotates the block to the left and resets the rotation to 3 if it goes below 0
     */
    private void rotateLeft() {
        rotation--;
        if (rotation < 0) {
            rotation = 3;
        }
    }

    /**
     * Returns the block's shape and rotation in an array
     * @return returns the block's shape and rotation in an array
     */
    private int[][] getBlock() {
        return block[shape][rotation];
    }
}
