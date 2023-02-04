package Game;

import Board.WindowBoard;
import BoardPieces.BoardConnectPieces;
import Player.Player;
import Player.PlayerBlack;
import Player.PlayerWhite;
import Rules.TurnRules;

import javax.swing.*;
import java.util.Arrays;

public class Game {
    public static final Player whitePlayer = new PlayerWhite();
    public static final Player blackPlayer = new PlayerBlack();
    private static WindowBoard windowBoard;
    private final BoardConnectPieces boardPieces;

    public Game() {
        Integer boardSize = askBoardSize();

        // Board
        windowBoard = new WindowBoard(boardSize, boardSize);
        // PiecesOld in board
        boardPieces = new BoardConnectPieces(windowBoard);
        boardPieces.drawPiecesOnBoard();
    }

    public static void restartGame() {
        if (blackPlayer.isTurn()) {
            TurnRules.switchTurn();
        }
        windowBoard.removeWindow();
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

    public BoardConnectPieces getBoardPieces() {
        return boardPieces;
    }
}
