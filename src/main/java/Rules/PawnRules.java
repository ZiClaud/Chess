package Rules;

import Pieces.*;

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

    public static boolean isThisPawnThreateningCastling(Piece piece, HashSet<Piece> pieces, char x, int y) {
        // TODO: FIX
        if (piece.getPieceType() == PieceType.Pawn) {
            HashSet<Piece> futurePieces = new HashSet<>(pieces);

            if (piece.getPieceColor() == PieceColor.WHITE) {
                futurePieces.add(PieceFactory.newPiece(PieceColor.WHITE, PieceType.Pawn, 'b', 1));
                futurePieces.add(PieceFactory.newPiece(PieceColor.WHITE, PieceType.Pawn, 'c', 1));
                futurePieces.add(PieceFactory.newPiece(PieceColor.WHITE, PieceType.Pawn, 'd', 1));
                futurePieces.add(PieceFactory.newPiece(PieceColor.WHITE, PieceType.Pawn, 'f', 1));
                futurePieces.add(PieceFactory.newPiece(PieceColor.WHITE, PieceType.Pawn, 'g', 1));
                return canWhitePawnEat(piece, futurePieces, x, y);
            }
            if (piece.getPieceColor() == PieceColor.BLACK) {
                futurePieces.add(PieceFactory.newPiece(PieceColor.BLACK, PieceType.Pawn, 'b', 8));
                futurePieces.add(PieceFactory.newPiece(PieceColor.BLACK, PieceType.Pawn, 'c', 8));
                futurePieces.add(PieceFactory.newPiece(PieceColor.BLACK, PieceType.Pawn, 'd', 8));
                futurePieces.add(PieceFactory.newPiece(PieceColor.BLACK, PieceType.Pawn, 'f', 8));
                futurePieces.add(PieceFactory.newPiece(PieceColor.BLACK, PieceType.Pawn, 'g', 8));
                return canBlackPawnEat(piece, futurePieces, x, y);
            }
        }
        assert false;
        return false;
    }

    private static boolean canWhitePawnEat(Piece piece, HashSet<Piece> pieces, char x, int y) {
        // Move top right/top left
        if (y == piece.getPosY() + 1 && (x == piece.getPosX() + 1 || x == piece.getPosX() - 1)) {
            if (PiecesRules.isPieceThere(pieces, x, y)) {
                return true;
            }
        }
        return false;
    }

    private static boolean canBlackPawnEat(Piece piece, HashSet<Piece> pieces, char x, int y) {
        // Move bottom right/bottom left
        if (y == piece.getPosY() - 1 && (x == piece.getPosX() - 1 || x == piece.getPosX() + 1)) {
            if (PiecesRules.isPieceThere(pieces, x, y)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isWhitePathBlocked(Piece piece, HashSet<Piece> pieces, char x, int y) {
        // stops if path is blocked
        if (x == piece.getPosX() && (y == piece.getPosY() + 1 || y == piece.getPosY() + 2)) {
            return PiecesRules.isPieceThere(pieces, x, y);
        }
        return false;
    }

    private static boolean isBlackPathBlocked(Piece piece, HashSet<Piece> pieces, char x, int y) {
        // stops if path is blocked
        if (x == piece.getPosX() && (y == piece.getPosY() - 1 || y == piece.getPosY() - 2)) {
            if (PiecesRules.isPieceThere(pieces, x, y)) {
                return true;
            }
        }
        return false;
    }
}
