package Rules;

import Pieces.Piece;
import Pieces.PieceType;

import java.awt.*;

public class Rules {
    /**
     * Can this piece move to that coordinate?
     */
    public static boolean canPieceMoveHere(Piece piece, char x, int y) {
        PieceType pieceType = piece.getPieceType();
        if (pieceType == PieceType.Pawn) {
            return canPawnMoveHere(piece, x, y);
        } else if (pieceType == PieceType.Bishop) {
            return canBishopMoveHere(piece, x, y);
        } else if (pieceType == PieceType.Knight) {
            return canKnightMoveHere(piece, x, y);
        } else if (pieceType == PieceType.Tower) {
            return canTowerMoveHere(piece, x, y);
        } else if (pieceType == PieceType.King) {
            return canKingMoveHere(piece, x, y);
        } else if (pieceType == PieceType.Queen) {
            return canQueenMoveHere(piece, x, y);
        }

        assert (false);
        return false;
    }

    private static boolean canPawnMoveHere(Piece piece, char x, int y) {
        // TODO: El Passant
        // TODO: if piece on top right and top left
        if (piece.getPieceColor() == Color.WHITE) {
            if (piece.getPosX() == x &&
                    (piece.getPosY() == y - 1 ||
                            piece.getPosY() == y - 2)) {
                return true;
            } else {
                return false;
            }
        } else {    // If pawn is black
            if (piece.getPosX() == x &&
                    (piece.getPosY() == y + 1 ||
                            piece.getPosY() == y + 2)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static boolean canBishopMoveHere(Piece piece, char x, int y) {
        char pX = piece.getPosX();
        int pY = piece.getPosY();
        if ((pX + pY == x + y) ||
                (pX - pY == x - y)) {
            return true;
        }
            return false;
    }

    private static boolean canKnightMoveHere(Piece piece, char x, int y) {
        char pX = piece.getPosX();
        int pY = piece.getPosY();

        if ((x == pX + 2 && y == pY + 1) ||
                (x == pX + 1 && y == pY + 2) ||
                (x == pX - 2 && y == pY - 1) ||
                (x == pX - 1 && y == pY - 2) ||
                (x == pX + 2 && y == pY - 1) ||
                (x == pX - 1 && y == pY + 2) ||
                (x == pX - 2 && y == pY + 1) ||
                (x == pX + 1 && y == pY - 2)
        ) {
            return true;
        }

        return false;
    }

    private static boolean canTowerMoveHere(Piece piece, char x, int y) {
        if (x == piece.getPosX() || y == piece.getPosY()) {
            return true;
        }
        return false;
    }

    private static boolean canKingMoveHere(Piece piece, char x, int y) {
        if ((x + 1 == piece.getPosX() || x - 1 == piece.getPosX()) &&
                (y + 1 == piece.getPosY() || y - 1 == piece.getPosY()) ||
                (x == piece.getPosX()) && (y + 1 == piece.getPosY() || y - 1 == piece.getPosY()) ||
                (y == piece.getPosY()) && (x + 1 == piece.getPosX() || x - 1 == piece.getPosX())) {
            return true;
        }
        return false;
    }

    private static boolean canQueenMoveHere(Piece piece, char x, int y) {
        return canTowerMoveHere(piece, x, y) || canBishopMoveHere(piece, x, y);
    }
}
