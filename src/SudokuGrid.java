
public class SudokuGrid {
    GameCells[][] gameGrid = new GameCells[9][9];

    int[][] startingNumbers = new int[9][9];

    public SudokuGrid() {
        startingNumbers[0][0] = 1;
        startingNumbers[2][0] = 3;
        startingNumbers[2][1] = 6;
        startingNumbers[0][2] = 7;
        startingNumbers[0][4] = 5;
        startingNumbers[0][8] = 9;
        startingNumbers[4][0] = 5;
        startingNumbers[6][0] = 7;
        startingNumbers[7][1] = 2;
        startingNumbers[7][2] = 5;
        startingNumbers[6][2] = 1;
    }

    public void fillTheGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameGrid[i][j] = new GameCells();
                if (startingNumbers[i][j] != 0) {
                    gameGrid[i][j].setNumber(startingNumbers[i][j]);
                }
            }
        }
    }

    public boolean canBePlacedOnRow(int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (gameGrid[row][i].getNumber() == number) {
                return false;
            }
        }
        return true;
    }

    public boolean canBePlacedOnCol(int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (gameGrid[i][col].getNumber() == number) {
                return false;
            }
        }
        return true;
    }

    public boolean thisCellIsEmpty(int row, int col) {
        if (gameGrid[row][col].getNumber() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidMove(int row, int col, int number) {
        if (thisCellIsEmpty(row, col) == true &&
                canBePlacedOnCol(col, number) == true &&
                canBePlacedOnRow(row, number) == true) {
            return true;
        } else {
            return false;
        }
    }

}
