package Rules;

import BoardPieces.BoardConnectPieces;
import Game.Game;
import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceType;

import java.util.Collection;

public class ThreatRules {
    // TODO: King rules - Castle and Move/Be protected if there's a check
    public static boolean isCheckWhiteK(BoardConnectPieces boardConnectPieces) {
        Piece king = null;
        for (Piece piece : boardConnectPieces.getPieces()) {
            if (piece.getPieceColor() == PieceColor.WHITE && piece.getPieceType() == PieceType.King) {
                king = piece;
                break;
            }
        }

        assert king != null;

        for (Piece piece : boardConnectPieces.getPieces()) {
            if (piece.getPieceColor() == PieceColor.BLACK && PiecesRules.canPieceMoveHere(piece, boardConnectPieces, king.getPosX(), king.getPosY())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isCheckBlackK(BoardConnectPieces boardConnectPieces) {
        Piece king = null;
        for (Piece piece : boardConnectPieces.getPieces()) {
            if (piece.getPieceColor() == PieceColor.BLACK && piece.getPieceType() == PieceType.King) {
                king = piece;
            }
        }

        assert king != null;

        for (Piece piece : boardConnectPieces.getPieces()) {
            if (piece.getPieceColor() == PieceColor.WHITE && PiecesRules.canPieceMoveHere(piece, boardConnectPieces, king.getPosX(), king.getPosY())) {
                return true;
            }
        }

        return false;
    }

    protected static boolean doesStopWhiteCheck(BoardConnectPieces boardConnectPieces) {
        Collection<String> moves;
        char x = 0;
        int y = 0;
        for (Piece piece : boardConnectPieces.getPieces()) {
            if (piece.getPieceColor() == PieceColor.WHITE) {
                moves = PiecesRules.getPossibleMoves(piece, boardConnectPieces).values();
                for (String s : moves) {
                    x = s.charAt(0);
                    y = s.charAt(1) - '0';
                }
            }
        }
        return false;
    }

    protected static boolean isThisPositionThreatened(BoardConnectPieces boardConnectPieces, Character x, Integer y) {
        String xy = x.toString() + y.toString();
        for (Piece piece : boardConnectPieces.getPieces()) {
            if ((Game.whitePlayer.isTurn() && piece.getPieceColor() == PieceColor.BLACK) ||
                    (Game.blackPlayer.isTurn() && piece.getPieceColor() == PieceColor.WHITE)) {
                if (PiecesRules.getPossibleMoves(piece, boardConnectPieces).containsValue(xy)) {
                    return true;
                }
            }
        }
        return false;
    }
}
