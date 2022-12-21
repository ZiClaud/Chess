package Rules;

import BoardPieces.BoardConnectPieces;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceType;

public class PawnRules {
    // TODO: Remove pawn when pawns does El Passant
    protected static boolean canWhitePawnMoveHere(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        if (canWhitePawnDoElPassant(piece, boardConnectPieces, x, y)) {
            return true;
        }
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

    private static boolean canWhitePawnDoElPassant(Piece whitePawn, BoardConnectPieces boardConnectPieces, char x, int y) {
        for (Piece blackPawn : boardConnectPieces.getPieces()) {
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

    private static boolean canBlackPawnDoElPassant(Piece blackPawn, BoardConnectPieces boardConnectPieces, char x, int y) {
        for (Piece whitePawn : boardConnectPieces.getPieces()) {
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

    protected static void updateWhitePawnsPreviousPosition(BoardConnectPieces boardConnectPieces) {
        for (Piece piece : boardConnectPieces.getPieces()) {
            if (piece.getPieceColor() == PieceColor.WHITE && piece.getPieceType() == PieceType.Pawn) {
                ((Pawn) piece).updatePreviousPos();
            }
        }
    }

    protected static void updateBlackPawnsPreviousPosition(BoardConnectPieces boardConnectPieces) {
        for (Piece piece : boardConnectPieces.getPieces()) {
            if (piece.getPieceColor() == PieceColor.BLACK && piece.getPieceType() == PieceType.Pawn) {
                ((Pawn) piece).updatePreviousPos();
            }
        }
    }

    protected static boolean canBlackPawnMoveHere(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        if (canBlackPawnDoElPassant(piece, boardConnectPieces, x, y)) {
            return true;
        }
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
