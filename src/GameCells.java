public class GameCells {

    private int number;

    public GameCells() {
        number = 0;
    }

    // 0 represents empty cell
    public boolean cellEmpty() {
        return number == 0;
    }

    // Setting number to be in range from 1 to 9
    public void setNumber(int number) {
        if (number >= 0 && number <= 9) {
            this.number = number;
        }
    }

    public int getNumber() {
        return number;
    }

}
