
public class TTTBoard {
    private char[][] board;
    private TTTTileButton[][] buttons;

    public TTTBoard() {
        board = new char[3][3];
        buttons = new TTTTileButton[3][3];
        reset();
    }

    public boolean makeMove(int row, int col, char player) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }

        if (board[row][col] != '\0') {
            return false;
        }

        board[row][col] = player;
        return true;
    }

    public char checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '\0'
                    && board[i][0] == board[i][1]
                    && board[i][1] == board[i][2]) {
                return board[i][0];
            }

            if (board[0][i] != '\0'
                    && board[0][i] == board[1][i]
                    && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }

        if (board[0][0] != '\0'
                && board[0][0] == board[1][1]
                && board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] != '\0'
                && board[0][2] == board[1][1]
                && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return '\0';
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '\0') {
                    return false;
                }
            }
        }

        return true;
    }

    public void reset() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '\0';
            }
        }
    }

    public char getCell(int row, int col) {
        return board[row][col];
    }

    public TTTTileButton[][] getButtons() {
        return buttons;
    }

    public void setButton(int row, int col, TTTTileButton button) {
        buttons[row][col] = button;
    }
}
