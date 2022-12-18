package Rules;

import Pieces.Piece;
import Pieces.PieceType;

import java.util.HashSet;

public class ComplexRules {
    public static boolean isThisALegalMove(Piece piece, HashSet<Piece> pieces, char x, int y) {

        return !isGoingThroughPieceToGetThere(piece, pieces, x, y);
    }

    private static boolean isGoingThroughPieceToGetThere(Piece piece, HashSet<Piece> pieces, char x, int y) {
        PieceType pieceType = piece.getPieceType();
        if (pieceType == PieceType.Tower && isTowerGoingThroughPiecesToGetThere(piece, pieces, x, y)) {
            return true;
        }

        return false;
    }

    private static boolean isTowerGoingThroughPiecesToGetThere(Piece tower, HashSet<Piece> pieces, char x, int y) {
        if (tower.getPosX() == x){
            return isXTowerGoingThroughPiecesToGetThere(tower, pieces, x, y);
        } else if (tower.getPosY() == y){
            return isYTowerGoingThroughPiecesToGetThere(tower, pieces, x, y);
        }
        assert (false);
        return false;
    }

    private static boolean isXTowerGoingThroughPiecesToGetThere(Piece tower, HashSet<Piece> pieces, char x, int y) {
        char towerX = tower.getPosX();
        int towerY = tower.getPosY();
        char pieceX;
        int pieceY;

        for (Piece piece : pieces) {
            pieceX = piece.getPosX();
            pieceY = piece.getPosY();
            if (pieceX == towerX && towerX == x) {
                if (y > towerY && y > pieceY && towerY < pieceY) {
                    return true;
                }
                if (y < towerY && y < pieceY && towerY > pieceY) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isYTowerGoingThroughPiecesToGetThere(Piece tower, HashSet<Piece> pieces, char x, int y) {
        char towerX = tower.getPosX();
        int towerY = tower.getPosY();
        char pieceX;
        int pieceY;

        for (Piece piece : pieces) {
            pieceX = piece.getPosX();
            pieceY = piece.getPosY();
            if (pieceY == towerY && towerY == y) {
                if (x > towerX && x > pieceX && towerX < pieceX) {
                    return true;
                }
                if (x < towerX && y < pieceX && towerX > pieceX) {
                    return true;
                }
            }
        }
        return false;
    }
}
