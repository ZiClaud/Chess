package Rules;

import Game.Game;
import Pieces.Piece;
import Pieces.PieceColor;

import javax.swing.*;
import java.util.HashSet;

public class WinRules {
    public static void win(HashSet<Piece> pieces) {
        if (Game.whitePlayer.isTurn() && didWin(pieces, PieceColor.WHITE)) {
            JOptionPane.showMessageDialog(null, "Black won!");
        }
        if (Game.blackPlayer.isTurn() && didWin(pieces, PieceColor.BLACK)) {
            JOptionPane.showMessageDialog(null, "White won!");
        }
    }

    private static boolean didWin(HashSet<Piece> pieces, PieceColor pieceColor) {
        for (Piece piece : pieces) {
            if (piece.getPieceColor() == pieceColor) {
                if (!PiecesRules.getPossibleMoves(piece, pieces).isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }
}
