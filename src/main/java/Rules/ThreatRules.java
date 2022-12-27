package Rules;

import BoardPieces.BoardConnectPieces;
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
}
