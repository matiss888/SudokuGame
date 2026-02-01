// import java.awt.Font;

// import javax.swing.JButton;

public class SudokuGrid {
    GameCells[][] gameGrid = new GameCells[9][9];
    // JButton[][] buttons = new JButton[9][9];

    int[][] startingNumbers = new int[9][9];

    // Setting sudoku starting numbers to already be filled
    public SudokuGrid() {
        startingNumbers[0][0] = 9;
        startingNumbers[0][1] = 3;
        startingNumbers[4][5] = 3;
        startingNumbers[1][6] = 3;
        startingNumbers[3][7] = 3;
        startingNumbers[2][8] = 8;
        startingNumbers[3][6] = 8;
        startingNumbers[4][1] = 9;
        startingNumbers[8][2] = 9;
        startingNumbers[3][3] = 9;
        startingNumbers[6][6] = 9;
        startingNumbers[2][0] = 5;
        startingNumbers[2][1] = 4;
        startingNumbers[0][2] = 8;
        startingNumbers[0][4] = 7;
        startingNumbers[0][8] = 2;
        startingNumbers[4][0] = 8;
        startingNumbers[6][0] = 6;
        startingNumbers[7][1] = 7;
        startingNumbers[7][2] = 2;
        startingNumbers[6][2] = 3;
        startingNumbers[7][8] = 3;
        startingNumbers[2][3] = 3;
    }

    // Filling the grid with numbers or empty spaces (0s)
    public void fillTheGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameGrid[i][j] = new GameCells();
                // buttons[i][j] = new JButton();
                // buttons[i][j].setFont(new Font("Serif", Font.BOLD, 30));
                // int row = i;
                // int col = j;
                // buttons[i][j].addActionListener(e -> {
                // System.out.println("Click on: " + row + ", " + col);
                // });
                if (startingNumbers[i][j] != 0) {
                    gameGrid[i][j].setNumber(startingNumbers[i][j]);
                    // buttons[i][j].setText(startingNumbers.toString());
                }
            }
        }
    }

    // Checking if my number is not in one of the smaller 9 3x3 cubes
    public boolean canBePlacedInSmallCube(int row, int col, int number) {
        int startingRow = row / 3 * 3;
        int startingCol = col / 3 * 3;
        for (int i = startingRow; i <= startingRow + 2; i++) {
            for (int j = startingCol; j <= startingCol + 2; j++) {
                if (gameGrid[i][j].getNumber() == number) {
                    return false;
                }
            }
        }
        return true;
    }

    // Checking if this number can be placed in the same row
    public boolean canBePlacedOnRow(int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (gameGrid[row][i].getNumber() == number) {
                return false;
            }
        }
        return true;
    }

    // Checking if this number can be placed in the same col
    public boolean canBePlacedOnCol(int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (gameGrid[i][col].getNumber() == number) {
                return false;
            }
        }
        return true;
    }

    // Checking if where i am placing number is an empty cell or not
    public boolean thisCellIsEmpty(int row, int col) {
        if (gameGrid[row][col].getNumber() == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Checking if all previous funcs are true and if they are that means number can
    // be placed in this spot.
    public boolean isValidMove(int row, int col, int number) {
        if (thisCellIsEmpty(row, col) == true &&
                canBePlacedOnCol(col, number) == true &&
                canBePlacedOnRow(row, number) == true &&
                canBePlacedInSmallCube(row, col, number)) {
            return true;
        } else {
            return false;
        }
    }
    // Sakuma laikam mēģināšu uztaisīt UI
    // public int placeNumber(int row, int col, int number) {

    // }

}
