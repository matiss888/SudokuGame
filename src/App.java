import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// import javax.swing.JButton;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        // Galvenais logs, kurā iekšā būs pati Sudoku spēle
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
        gamePanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int xCell = x / 70;
                int yCell = y / 70;
                System.out.println("Mouse clicked on " + xCell + ", " + yCell);
            }
        });
        gamePanel.setPreferredSize(new Dimension(630, 630));
        sudokuFrame.add(gamePanel);
        sudokuFrame.pack();
        sudokuFrame.setVisible(true);
    }
}
