package Rules;

import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceType;
import Pieces.Position;

import java.util.HashSet;

public class ThreatRules {
    public static boolean isCheckWhiteK(HashSet<Piece> pieces) {
        Piece king = null;
        for (Piece piece : pieces) {
            if (piece.getPieceColor() == PieceColor.WHITE && piece.getPieceType() == PieceType.King) {
                king = piece;
                break;
            }
        }

        assert king != null;

        for (Piece piece : pieces) {
            if (piece.getPosition().equals(king.getPosition())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isCheckBlackK(HashSet<Piece> pieces) {
        Piece king = null;
        for (Piece piece : pieces) {
            if (piece.getPieceColor() == PieceColor.BLACK && piece.getPieceType() == PieceType.King) {
                king = piece;
            }
        }

        assert king != null;

        for (Piece piece : pieces) {
            if (piece.getPieceColor() == PieceColor.WHITE &&
                    piece.getPossibleMoves().getPositions().contains(king.getPosition())) {
                return true;
            }
        }

        return false;
    }

    protected static boolean isThisPositionThreatened(HashSet<Piece> pieces, Position position) {
        // TODO: Finish this
        return false;
    }
    /*
    public static boolean doesStopCheck(HashSet<Piece> pieces, Piece piece, Position position) {
        if (piece.getPieceType() == PieceType.King) {
            return !isThisPositionThreatened(pieces, position);
        }

        HashSet<Piece> futurePieces = new HashSet<>(pieces);

        for (Piece enemyP : pieces) {
            if (enemyP.getPosition().equals(position) && enemyP.getPieceType() != PieceType.King) {
                futurePieces.remove(enemyP);
            }
        }

        futurePieces.add(PieceFactory.newPiece(piece.getPieceColor(), piece.getPieceType(), position));

        if (piece.getPieceColor() == PieceColor.WHITE) {
            return !isCheckWhiteK(futurePieces);
        } else {
            return !isCheckBlackK(futurePieces);
        }
    }
    */

    /*
    public static boolean willThisMoveCauseCheck(Piece piece, HashSet<Piece> pieces, Position position) {
        boolean ris = false;

        HashSet<Piece> futurePieces = new HashSet<>(pieces);
        futurePieces.remove(piece);
        for (Piece enemyP : pieces) {
            if (enemyP.getPosition().equals(position) && enemyP.getPieceType() != PieceType.King) {
                futurePieces.remove(enemyP);
            }
        }
        futurePieces.add(PieceFactory.newPiece(piece.getPieceColor(), piece.getPieceType(), position));

        if (isCheckWhiteK(futurePieces) && Game.whitePlayer.isTurn() ||
                isCheckBlackK(futurePieces) && Game.blackPlayer.isTurn()) {
            ris = true;
        }

        return ris;
    }
    */
}
