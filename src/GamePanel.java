
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    SudokuGrid sudokuGrid;

    // Grid value and cell values to reference
    int cellWidth = 70;
    int cellHeight = 70;
    int sudokuWidth = cellWidth * 9;
    int sudokuHeight = cellHeight * 9;
    int rows = 9;
    int cols = 9;
    int selectedRow = -1;
    int selectedCol = -1;
    int topCornerOfCellX = 0;
    int topCornerOfCellY = 0;

    // Filling the GamePanel with 0s
    public GamePanel(SudokuGrid grid) {
        this.sudokuGrid = grid;
        sudokuGrid.fillTheGrid();
        setFocusable(true);
        requestFocusInWindow();

        // Keyboard input when I select game cell.
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                if (selectedRow == -1 || selectedCol == -1)
                    return;

                char c = e.getKeyChar();

                // Entering a number from 1 - 9
                if (c >= '1' && c <= '9') {
                    int number = c - '0';
                    sudokuGrid.gameGrid[selectedRow][selectedCol].setNumber(number);
                    repaint();
                }

                // Deleting a number
                if (c == '0' || c == '\b') {
                    sudokuGrid.gameGrid[selectedRow][selectedCol].setNumber(0);
                    repaint();
                }
            }
        });

        // Tracking mouse location on grid so I can select cell
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int xCell = x / cellWidth;
                int yCell = y / cellHeight;
                System.out.println("Mouse clicked on " + xCell + ", " + yCell);
                selectedRow = yCell;
                selectedCol = xCell;
                repaint();
            };
        });
    }

    // Select a cell to enter a number and making a cross to see numbers in same row
    // and col
    public void selectedCell(Graphics g) {
        if (selectedRow == -1 || selectedCol == -1) {
            return;
        }
        g.setColor(Color.GRAY);
        g.fillRect(cellWidth * selectedCol, 0, cellWidth, sudokuHeight);
        g.fillRect(0, cellHeight * selectedRow, sudokuWidth, cellHeight);
        g.setColor(Color.GREEN);
        g.fillRect(cellWidth * selectedCol, cellHeight * selectedRow, cellWidth, cellHeight);
    }

    // Drawing horizontal lines on grid
    public void drawHorizontalLines(Graphics g) {
        g.setColor(Color.BLACK);
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

    // Drawing vertical lines on grid. Basic stroke 2.0 for biggr lines
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

    // Drawing starting numbers and centering them in the middle of the gamecell
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

    // Calling Paint method with components.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        selectedCell(g);
        drawHorizontalLines(g);
        drawVerticalLines(g);
        drawNumbers(g);

    }

}
