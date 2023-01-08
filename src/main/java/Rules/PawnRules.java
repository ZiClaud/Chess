package Rules;

import Pieces.Pawn;
import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceType;

import java.util.HashSet;

public class PawnRules {
    protected static boolean canWhitePawnMoveHere(Piece piece, HashSet<Piece> pieces, char x, int y) {
        if (canWhitePawnDoElPassant(piece, pieces, x, y)) {
            return true;
        }
        if (PawnRules.canWhitePawnEat(piece, pieces, x, y)) {
            return true;
        }
        if (PawnRules.isWhitePathBlocked(piece, pieces, x, y)) {
            return false;
        }
        if (PawnRules.isWhitePawnInStartingPosition(piece)) {
            return piece.getPosX() == x &&
                    (piece.getPosY() == y - 1 ||
                            piece.getPosY() == y - 2);
        }
        return (piece.getPosX() == x && piece.getPosY() == y - 1);
    }

    private static boolean canWhitePawnDoElPassant(Piece whitePawn, HashSet<Piece> pieces, char x, int y) {
        for (Piece blackPawn : pieces) {
            if (blackPawn.getPieceType() == PieceType.Pawn && blackPawn.getPieceColor() == PieceColor.BLACK) {
                if (((Pawn) blackPawn).allowElPassant()) {
                    if (y == blackPawn.getPosY() + 1 && x == blackPawn.getPosX()) {
                        if ((whitePawn.getPosX() + 1 == x && whitePawn.getPosY() + 1 == y)) {
                            return true;
                        }
                        if ((whitePawn.getPosX() - 1 == x && whitePawn.getPosY() + 1 == y)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean canBlackPawnDoElPassant(Piece blackPawn, HashSet<Piece> pieces, char x, int y) {
        for (Piece whitePawn : pieces) {
            if (whitePawn.getPieceType() == PieceType.Pawn && whitePawn.getPieceColor() == PieceColor.WHITE) {
                if (((Pawn) whitePawn).allowElPassant()) {
                    if (y == whitePawn.getPosY() - 1 && x == whitePawn.getPosX()) {
                        if ((blackPawn.getPosX() + 1 == x && blackPawn.getPosY() - 1 == y)) {
                            return true;
                        }
                        if ((blackPawn.getPosX() - 1 == x && blackPawn.getPosY() - 1 == y)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    protected static void updateWhitePawnsPreviousPosition(HashSet<Piece> pieces) {
        for (Piece piece : pieces) {
            if (piece.getPieceColor() == PieceColor.WHITE && piece.getPieceType() == PieceType.Pawn) {
                ((Pawn) piece).updatePreviousPos();
            }
        }
    }

    protected static void updateBlackPawnsPreviousPosition(HashSet<Piece> pieces) {
        for (Piece piece : pieces) {
            if (piece.getPieceColor() == PieceColor.BLACK && piece.getPieceType() == PieceType.Pawn) {
                ((Pawn) piece).updatePreviousPos();
            }
        }
    }

    protected static boolean canBlackPawnMoveHere(Piece piece, HashSet<Piece> pieces, char x, int y) {
        if (canBlackPawnDoElPassant(piece, pieces, x, y)) {
            return true;
        }
        if (PawnRules.canBlackPawnEat(piece, pieces, x, y)) {
            return true;
        }
        if (PawnRules.isBlackPathBlocked(piece, pieces, x, y)) {
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

    private static boolean canThisPawnEat(Piece piece, HashSet<Piece> pieces, char x, int y) {
        if (piece.getPieceColor() == PieceColor.WHITE) {
            return canWhitePawnEat(piece, pieces, x, y);
        }
        return canBlackPawnEat(piece, pieces, x, y);
    }

    public static boolean canThisPawnThreaten(Piece piece, char x, int y) {
        if (piece.getPieceColor() == PieceColor.WHITE) {
            return canWhitePawnThreaten(piece, x, y);
        }
        return canBlackPawnThreaten(piece, x, y);
    }

    private static boolean canWhitePawnThreaten(Piece piece, char x, int y) {
        return y == piece.getPosY() + 1 && (x == piece.getPosX() + 1 || x == piece.getPosX() - 1);
    }

    private static boolean canBlackPawnThreaten(Piece piece, char x, int y) {
        return y == piece.getPosY() - 1 && (x == piece.getPosX() - 1 || x == piece.getPosX() + 1);
    }

    private static boolean canWhitePawnEat(Piece piece, HashSet<Piece> pieces, char x, int y) {
        if (canWhitePawnThreaten(piece, x, y)) {
            return PiecesRules.isPieceThere(pieces, x, y);
        }
        return false;
    }

    private static boolean canBlackPawnEat(Piece piece, HashSet<Piece> pieces, char x, int y) {
        if (canBlackPawnThreaten(piece, x, y)) {
            return PiecesRules.isPieceThere(pieces, x, y);
        }
        return false;
    }

    private static boolean isWhitePathBlocked(Piece piece, HashSet<Piece> pieces, char x, int y) {
        if (x == piece.getPosX() && (y == piece.getPosY() + 1 || y == piece.getPosY() + 2)) {
            return PiecesRules.isPieceThere(pieces, x, y);
        }
        return false;
    }

    private static boolean isBlackPathBlocked(Piece piece, HashSet<Piece> pieces, char x, int y) {
        if (x == piece.getPosX() && (y == piece.getPosY() - 1 || y == piece.getPosY() - 2)) {
            return PiecesRules.isPieceThere(pieces, x, y);
        }
        return false;
    }
}
