package Rules;

import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceType;

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

    protected static boolean doesThisMoveStopWhiteCheck(BoardConnectPieces boardConnectPieces, char x, int y) {
        for (Piece piece : boardConnectPieces.getPieces()) {
            if (piece.getPieceColor() == PieceColor.WHITE) {
                if (PiecesRules.isThisAPossibleMove(piece, boardConnectPieces, x, y)) {
                    //TODO: Maybe instead of isThisAPossibleMove -> getPossibleMoves(piece, boardConnectPieces)
                }
            }
        }

        return false;
    }
}
