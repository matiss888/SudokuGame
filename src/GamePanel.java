
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
    Font boldFont = new Font("Serif", Font.BOLD, 30);
    Font plainFont = new Font("Serif", Font.PLAIN, 30);

    // Filling the GamePanel with 0s
    public GamePanel(SudokuGrid grid) {
        this.sudokuGrid = grid;
        sudokuGrid.fillTheGrid();

        // Keyboard input when I select game cell.
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                // Do this if incase no selected cells and game doesnt crash for out of bound
                // error since -1, -1.
                if (selectedRow == -1 || selectedCol == -1)
                    return;

                char c = e.getKeyChar();

                // Entering a number from 1 - 9
                if (c >= '1' && c <= '9') {
                    int number = c - '0';
                    if (sudokuGrid.isValidMove(selectedRow, selectedCol, number)) {
                        sudokuGrid.gameGrid[selectedRow][selectedCol].setNumber(number);
                        repaint();
                    }
                }
            }

            public void keyPressed(java.awt.event.KeyEvent e) {
                // Do this if incase no selected cells and game doesnt crash for out of bound
                // error since -1, -1.
                if (selectedRow == -1 || selectedCol == -1)
                    return;
                if (e.getKeyChar() == '\b') {
                    // System.out.println("pressed");
                    // System.out.println(e.getKeyChar());
                    // System.out.println(
                    // sudokuGrid.gameGrid[selectedRow][selectedCol].getNumber());
                    if (!sudokuGrid.thisCellIsInitialCell(selectedRow, selectedCol)) {
                        sudokuGrid.gameGrid[selectedRow][selectedCol].setNumber(0);
                        repaint();
                    }
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
                System.out.println("Mouse clicked on " + yCell + ", " + xCell);
                selectedRow = yCell;
                selectedCol = xCell;
                repaint();
            };
        });
    }

    public void sameNumberAsSelectedCell(Graphics g) {
        if (selectedRow == -1 || selectedCol == -1) {
            return;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuGrid.gameGrid[i][j].getNumber() == 0) {
                    continue;
                }
                if (sudokuGrid.gameGrid[i][j].getNumber() == sudokuGrid.gameGrid[selectedRow][selectedCol]
                        .getNumber()) {
                    g.setColor(Color.BLUE);
                    g.fillRect(cellWidth * j, cellHeight * i, cellWidth, cellHeight);
                }
            }
        }
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

    // Drawing horizontal lines on grid. Basic stroke 3.0 for biggr lines
    public void drawHorizontalLines(Graphics g) {
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                g2.setStroke(new BasicStroke(3.0f));
                g2.drawLine(0, i * cellHeight, sudokuWidth, i * cellHeight);
            } else {
                g2.setStroke(new BasicStroke(1.0f));
                g2.drawLine(0, i * cellHeight, sudokuWidth, i * cellHeight);
            }
        }
    }

    // Drawing vertical lines on grid. Basic stroke 3.0 for biggr lines
    public void drawVerticalLines(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                g2.setStroke(new BasicStroke(3.0f));
                g2.drawLine(cellWidth * i, 0, cellWidth * i, sudokuHeight);
            } else {
                g2.setStroke(new BasicStroke(1.0f));
                g2.drawLine(cellWidth * i, 0, cellWidth * i, sudokuHeight);
            }
        }
    }

    // Drawing numbers and centering them in the middle of the gamecell
    public void drawNumbers(Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int number = sudokuGrid.gameGrid[row][col].getNumber();
                if (number != 0) {
                    String text = Integer.toString(number);
                    if (sudokuGrid.thisCellIsInitialCell(row, col)) {
                        g.setFont(boldFont);
                    } else {
                        g.setFont(plainFont);
                    }
                    int x = col * cellWidth + (cellWidth - fm.stringWidth(text)) / 2;
                    int y = row * cellHeight + ((cellHeight - fm.getHeight()) / 2) + fm.getAscent();
                    g.drawString(text, x, y);
                }
            }
        }
    }

    // Calling Paint method with methods.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        sameNumberAsSelectedCell(g);
        selectedCell(g);
        drawHorizontalLines(g);
        drawVerticalLines(g);
        drawNumbers(g);
    }

}
