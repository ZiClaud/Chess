package Game;

import Board.WindowBoard;
import Draw.DrawOnBoard;
import Player.Player;
import Player.PlayerBlack;
import Player.PlayerWhite;
import Rules.TurnRules;

import javax.swing.*;
import java.util.Arrays;

public class Game {
    public static final Player whitePlayer = PlayerWhite.getInstance();
    public static final Player blackPlayer = PlayerBlack.getInstance();
    public static String gameMode;
    public static Integer boardSize;
    private static WindowBoard windowBoard;

    public Game() {
        if (boardSize == null) {
            boardSize = askBoardSize();
        }
        gameMode = askGameMode();

        // Board
        windowBoard = WindowBoard.getInstance();

        // Pieces in board
        DrawOnBoard.drawPiecesOnBoard();
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

    private String askGameMode() {
        String[] options = {"Normal", "Fisher Random"};
        String answer;
        answer = (String) JOptionPane.showInputDialog(null, "Game mode", "Choose the game mode", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (!Arrays.stream(options).toList().contains(answer)) {
            answer = options[0];
        }

        System.out.println(answer);
        System.out.println(answer.contains("Normal"));

        return answer;
    }
}
