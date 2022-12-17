package Player;

import Board.WindowBoard;
import BoardPieces.BoardConnectPieces;

import javax.swing.*;
import java.util.Arrays;

public class Game {
    private final Player white = new PlayerWhite();
    private final Player black = new PlayerBlack();
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
        do {
            boardSize = (Integer) JOptionPane.showInputDialog(null, "Board size", "Choose the board size", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        } while (!Arrays.stream(options).toList().contains(boardSize));

        return boardSize;
    }

    public Player getWhitePlayer() {
        return white;
    }

    public Player getBlackPlayer() {
        return black;
    }

    public WindowBoard getWindowBoard() {
        return windowBoard;
    }

    public BoardConnectPieces getBoardPieces() {
        return boardPieces;
    }
}
