package Rules;

import BoardPieces.BoardConnectPieces;
import Game.Game;
import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceFactory;
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
            if (piece.getPieceColor() == PieceColor.BLACK && PiecesRules.isThisAPossibleMove(piece, boardConnectPieces, king.getPosX(), king.getPosY())) {
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
            if (piece.getPieceColor() == PieceColor.WHITE && PiecesRules.isThisAPossibleMove(piece, boardConnectPieces, king.getPosX(), king.getPosY())) {
                return true;
            }
        }

        return false;
    }

    public static boolean doesStopWhiteCheck(BoardConnectPieces boardConnectPieces, Piece piece, char x, int y) {
        if (piece.getPieceType() == PieceType.King){
            // TODO
        }

        Piece newPiecePos = PieceFactory.newPiece(piece.getPieceColor(), piece.getPieceType(), x, y);

        boardConnectPieces.getPieces().add(newPiecePos);

        if (!isCheckWhiteK(boardConnectPieces)) {
            boardConnectPieces.getPieces().remove(newPiecePos);
            return true;
        }

        boardConnectPieces.getPieces().remove(newPiecePos);
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
