
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel {

    SudokuGrid sudokuGrid;

    // Grid value and cell values to reference
    int cellWidth = 70;
    int cellHeight = 70;
    int sudokuWidth = cellWidth * 9;
    int sudokuHeight = cellHeight * 9;
    int rows = 9;
    int cols = 9;

    // Filling the GamePanel with 0s
    public GamePanel(SudokuGrid grid) {
        this.sudokuGrid = grid;
        sudokuGrid.fillTheGrid();
    }

    public void drawHorizontalLines(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                g2.setStroke(new BasicStroke(2.0f));
                g2.drawLine(0, i * cellHeight, sudokuWidth, i * cellHeight);

            } else {
                g2.setStroke(new BasicStroke(1.0f));
                g2.drawLine(0, i * cellHeight, sudokuWidth, i * cellHeight);
            }
        }
    }

    public void drawVerticalLines(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                g2.setStroke(new BasicStroke(2.0f));
                g2.drawLine(cellWidth * i, 0, cellWidth * i, sudokuHeight);
            } else {
                g2.setStroke(new BasicStroke(1.0f));
                g2.drawLine(cellWidth * i, 0, cellWidth * i, sudokuHeight);
            }
        }
    }

    public void drawNumbers(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 30));
        FontMetrics fm = g.getFontMetrics();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int number = sudokuGrid.gameGrid[row][col].getNumber();
                if (number != 0) {
                    String text = Integer.toString(number);
                    int x = col * cellWidth + (cellWidth - fm.stringWidth(text)) / 2;
                    int y = row * cellHeight + ((cellHeight - fm.getHeight()) / 2) + fm.getAscent();
                    g.drawString(text, x, y);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHorizontalLines(g);
        drawVerticalLines(g);
        drawNumbers(g);
    }

}
