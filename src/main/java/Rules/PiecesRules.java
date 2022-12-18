package Rules;

import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.PieceType;

import java.awt.*;

public class PiecesRules {
    /**
     * Can this piece move to that coordinate?
     */
    public static boolean canPieceMoveHere(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        PieceType pieceType = piece.getPieceType();
        if (pieceType == PieceType.Pawn) {
            return canPawnMoveHere(piece, boardConnectPieces, x, y) && isntSameColorPieceThere(piece, boardConnectPieces, x, y);
        } else if (pieceType == PieceType.Bishop) {
            return canBishopMoveHere(piece, x, y) && isntSameColorPieceThere(piece, boardConnectPieces, x, y);
        } else if (pieceType == PieceType.Knight) {
            return canKnightMoveHere(piece, x, y) && isntSameColorPieceThere(piece, boardConnectPieces, x, y);
        } else if (pieceType == PieceType.Tower) {
            return canTowerMoveHere(piece, x, y) && isntSameColorPieceThere(piece, boardConnectPieces, x, y) && ComplexRules.isThisALegalMove(piece, boardConnectPieces.getPieces(), x ,y);
        } else if (pieceType == PieceType.King) {
            return canKingMoveHere(piece, x, y) && isntSameColorPieceThere(piece, boardConnectPieces, x, y);
        } else if (pieceType == PieceType.Queen) {
            return canQueenMoveHere(piece, x, y) && isntSameColorPieceThere(piece, boardConnectPieces, x, y);
        }

        assert (false);
        return false;
    }

    protected static boolean isPieceThere(BoardConnectPieces boardConnectPieces, char x, int y) {
        for (Piece boardPiece : boardConnectPieces.getPieces()) {
            if (boardPiece.getPosX() == x && boardPiece.getPosY() == y) {
                return true;
            }
        }
        return false;
    }

    private static boolean isntSameColorPieceThere(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        for (Piece boardPiece : boardConnectPieces.getPieces()) {
            if (boardPiece.getPosX() == x && boardPiece.getPosY() == y) {
                if (boardPiece.getPieceColor() == piece.getPieceColor()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canPawnMoveHere(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        if (piece.getPieceColor() == Color.WHITE) {
            return PawnRules.canWhitePawnMoveHere(piece, boardConnectPieces, x, y);
        }
        if (piece.getPieceColor() == Color.BLACK) {
            return PawnRules.canBlackPawnMoveHere(piece, boardConnectPieces, x, y);
        }
        assert (false);
        return false;
    }


    private static boolean canBishopMoveHere(Piece piece, char x, int y) {
        char pX = piece.getPosX();
        int pY = piece.getPosY();
        return (pX + pY == x + y) ||
                (pX - pY == x - y);
    }

    private static boolean canKnightMoveHere(Piece piece, char x, int y) {
        char pX = piece.getPosX();
        int pY = piece.getPosY();

        return (x == pX + 2 && y == pY + 1) ||
                (x == pX + 1 && y == pY + 2) ||
                (x == pX - 2 && y == pY - 1) ||
                (x == pX - 1 && y == pY - 2) ||
                (x == pX + 2 && y == pY - 1) ||
                (x == pX - 1 && y == pY + 2) ||
                (x == pX - 2 && y == pY + 1) ||
                (x == pX + 1 && y == pY - 2);
    }

    private static boolean canTowerMoveHere(Piece piece, char x, int y) {
        return x == piece.getPosX() || y == piece.getPosY();
    }

    private static boolean canKingMoveHere(Piece piece, char x, int y) {
        // TODO: Caslte
        // TODO: Don't let king walk in a capturable tile
        return (x + 1 == piece.getPosX() || x - 1 == piece.getPosX()) &&
                (y + 1 == piece.getPosY() || y - 1 == piece.getPosY()) ||
                (x == piece.getPosX()) && (y + 1 == piece.getPosY() || y - 1 == piece.getPosY()) ||
                (y == piece.getPosY()) && (x + 1 == piece.getPosX() || x - 1 == piece.getPosX());
    }

    private static boolean canQueenMoveHere(Piece piece, char x, int y) {
        return canTowerMoveHere(piece, x, y) || canBishopMoveHere(piece, x, y);
    }
}
