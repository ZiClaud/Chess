package Rules;

import BoardPieces.BoardConnectPieces;
import Pieces.Piece;

import java.awt.*;

public class PawnRules {
    // TODO: El Passant
    protected static boolean canWhitePawnMoveHere(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        if (PawnRules.canWhitePawnEat(piece, boardConnectPieces, x, y)) {
            return true;
        }
        if (PawnRules.isWhitePathBlocked(piece, boardConnectPieces, x, y)) {
            return false;
        }
        if (PawnRules.isWhitePawnInStartingPosition(piece)) {
            return piece.getPosX() == x &&
                    (piece.getPosY() == y - 1 ||
                            piece.getPosY() == y - 2);
        }
        return (piece.getPosX() == x && piece.getPosY() == y - 1);
    }

    protected static boolean canBlackPawnMoveHere(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        if (PawnRules.canBlackPawnEat(piece, boardConnectPieces, x, y)) {
            return true;
        }
        if (PawnRules.isBlackPathBlocked(piece, boardConnectPieces, x, y)) {
            return false;
        }
        if (PawnRules.isBlackPawnInStartingPosition(piece)) {
            return piece.getPosX() == x &&
                    (piece.getPosY() == y + 1 ||
                            piece.getPosY() == y + 2);
        }
        return (piece.getPosX() == x && piece.getPosY() == y + 1);
    }

    private static boolean isBlackPawnInStartingPosition(Piece piece) {
        return (piece.getPosY() == 7);
    }

    private static boolean isWhitePawnInStartingPosition(Piece piece) {
        return (piece.getPosY() == 2);
    }

    private static boolean canBlackPawnEat(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        // Move bottom right/bottom left
        if (x == piece.getPosX() - 1 && y == piece.getPosY() - 1 ||
                x == piece.getPosX() + 1 && y == piece.getPosY() - 1) {
            if (PiecesRules.isPieceThere(boardConnectPieces, x, y)) {
                return true;
            }
        }
        return false;
    }

    private static boolean canWhitePawnEat(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        // Move top right/top left
        if (x == piece.getPosX() + 1 && y == piece.getPosY() + 1 ||
                x == piece.getPosX() - 1 && y == piece.getPosY() + 1) {
            if (PiecesRules.isPieceThere(boardConnectPieces, x, y)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isWhitePathBlocked(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        // stops if path is blocked
        if (x == piece.getPosX() && y == piece.getPosY() + 1) {
            if (PiecesRules.isPieceThere(boardConnectPieces, x, y)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBlackPathBlocked(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        // stops if path is blocked
        if (x == piece.getPosX() && y == piece.getPosY() - 1) {
            if (PiecesRules.isPieceThere(boardConnectPieces, x, y)) {
                return true;
            }
        }
        return false;
    }
}
