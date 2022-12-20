package Player;

import Board.WindowBoard;
import BoardPieces.BoardConnectPieces;

import javax.swing.*;
import java.util.Arrays;

public class Game {
    public static final Player white = new PlayerWhite();
    public static final Player black = new PlayerBlack();
    private final WindowBoard windowBoard;
    private final BoardConnectPieces boardPieces;

    public Game() {
        Integer boardSize = askBoardSize();

        // Board
        windowBoard = new WindowBoard(boardSize, boardSize);
        // Pieces in board
        boardPieces = new BoardConnectPieces(windowBoard);
        boardPieces.drawPiecesOnBoard();
    }

    private Integer askBoardSize() {
        Integer[] options = {600, 800, 1000, 1200, 1400};
        Integer boardSize;
        boardSize = (Integer) JOptionPane.showInputDialog(null, "Board size", "Choose the board size", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (!Arrays.stream(options).toList().contains(boardSize)) {
            boardSize = options[0];
        }

        return boardSize;
    }

    public WindowBoard getWindowBoard() {
        return windowBoard;
    }

    public BoardConnectPieces getBoardPieces() {
        return boardPieces;
    }
}
