
import javax.swing.*;
import java.awt.*;

public class Game {
    private TTTBoard board;
    private JFrame frame;
    private char currentPlayer;
    private JLabel statusLabel;

    public Game() {
        board = new TTTBoard();
        currentPlayer = 'X';

        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
        frame.add(statusLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                TTTTileButton button = new TTTTileButton(row, col);
                button.setFont(new Font("Arial", Font.BOLD, 48));

                board.setButton(row, col, button);
                boardPanel.add(button);

                button.addActionListener(e -> handleMove(button));
            }
        }

        frame.add(boardPanel, BorderLayout.CENTER);

        JButton resetButton = new JButton("New Game");
        resetButton.addActionListener(e -> resetGame());
        frame.add(resetButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void handleMove(TTTTileButton button) {
        int row = button.getRow();
        int col = button.getCol();

        if (!board.makeMove(row, col, currentPlayer)) {
            return;
        }

        button.setText(String.valueOf(currentPlayer));
        button.setEnabled(false);

        char winner = board.checkWinner();

        if (winner != '\0') {
            statusLabel.setText("Player " + winner + " wins!");
            disableBoard();
        } else if (board.isBoardFull()) {
            statusLabel.setText("It's a tie!");
        } else {
            switchPlayer();
            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void disableBoard() {
        TTTTileButton[][] buttons = board.getButtons();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        board.reset();
        currentPlayer = 'X';

        TTTTileButton[][] buttons = board.getButtons();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }

        statusLabel.setText("Player X's turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}
