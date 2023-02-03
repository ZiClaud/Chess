package Rules;

import Game.Game;
import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceType;

import javax.swing.*;
import java.util.HashSet;

/**
 * TODO: Draw rules
 * Mutual Agreement
 * When both players agree to Draw – this type of Draw is called a Mutual Agreement. At any point while playing Chess one of the players can offer the other to Draw – and if they accept the game is declared a Draw.
 * <p>
 * Threefold Repetition
 * While playing Chess, a Draw is declared when a player has made the same moves, or is about to make the same move, three times in a row – since the player cannot make any progress. For example, if a player’s King is threatened by another piece and moves to the same square three times in a row in order to escape – a Threefold Repetition Draw is called.
 * <p>
 * Fifty-Move Rule
 * The Fifty-Move Rule of Draw is a strange one – it states that if both players haven’t made any progress in fifty moves – the game is declared a Draw. If both players haven’t captured any of the other player’s pieces or moved their pawns in fifty moves – a Fifty-Move Draw is declared.
 * <p>
 * Insufficient Material
 * An Insufficient Material Draw is called in Chess when neither player has enough pieces left on the board so that they can Check-Mate the other player. For example, if both players only have their Kings left on the board, neither one of them can Check-Mate the other, since the Kings can’t get close enough to each other to do so. In instances like this, an Insufficient Material Draw is called.
 */
public class WinRules {
    public static void win(HashSet<Piece> pieces) {
        if (Game.whitePlayer.isTurn() && didWin(pieces, PieceColor.WHITE)) {
            if (true) {// (ThreatRules.isCheckWhiteK(pieces)) {
                JOptionPane.showMessageDialog(null, "Black won!");
            } else {
                JOptionPane.showMessageDialog(null, "Draw by stalemate!");
            }
            restart();
        }
        if (Game.blackPlayer.isTurn() && didWin(pieces, PieceColor.BLACK)) {
            if (true) {//(ThreatRules.isCheckBlackK(pieces)) {
                JOptionPane.showMessageDialog(null, "White won!");
            } else {
                JOptionPane.showMessageDialog(null, "Draw by stalemate!");
            }
            restart();
        }
        if (insufficientMaterial(pieces)) {
            JOptionPane.showMessageDialog(null, "Draw by insufficient material!");
            restart();
        }
    }

    private static void restart() {
        Game.restartGame();
        new Game();
    }

    private static boolean didWin(HashSet<Piece> pieces, PieceColor pieceColor) {
        for (Piece piece : pieces) {
            if (piece.getPieceColor() == pieceColor) {
                /*
                if (!PiecesRules.getPossibleMoves(piece, pieces).isEmpty()) {
                    return false;
                }
                 */
            }
        }

        return true;
    }

    private static boolean insufficientMaterial(HashSet<Piece> pieces) {
        int knightAndBishopNum = 0;

        for (Piece piece : pieces) {
            if (piece.getPieceType() == PieceType.Pawn || piece.getPieceType() == PieceType.Tower || piece.getPieceType() == PieceType.Queen) {
                return false;
            }
            if (piece.getPieceType() == PieceType.Knight || piece.getPieceType() == PieceType.Bishop) {
                knightAndBishopNum++;
            }
        }

        if (knightAndBishopNum > 1) {
            return false;
        }

        return true;
    }
}
