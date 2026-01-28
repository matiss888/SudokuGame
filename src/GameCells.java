public class GameCells {

    // GameCells glabā:
    // vienu ciparu (vai neko)
    // vai šobrīd ir kļūda
    // vai to drīkst mainīt

    private int number;

    public GameCells() {
        number = 0;
    }

    public boolean cellEmpty() {
        return number == 0;
    }

    public void setNumber(int number) {
        if (number >= 1 && number <= 9) {
            this.number = number;
        }
    }

    public int getNumber() {
        return number;
    }

}
