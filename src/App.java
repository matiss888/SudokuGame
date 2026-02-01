import java.awt.Dimension;

// import javax.swing.JButton;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        // Main sudoko game window which will contain grid
        JFrame sudokuFrame = new JFrame();
        sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sudokuFrame.setMinimumSize(new Dimension(645, 670));
        sudokuFrame.setTitle("MatjikaSudoku");
        sudokuFrame.setLocationRelativeTo(null);

        // Sudoku Grid fills the grid with 0 which is empty space and some prefilled
        // numbers.
        SudokuGrid grid = new SudokuGrid();
        // Pievienoju gamePanel, kas ir sudoku režģis
        GamePanel gamePanel = new GamePanel(grid);
        gamePanel.setPreferredSize(new Dimension(630, 630));
        sudokuFrame.add(gamePanel);
        sudokuFrame.pack();
        sudokuFrame.setVisible(true);
    }
}
