package Rules;

import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceType;

import java.util.HashMap;

public class PiecesRules {
    /**
     * Get all possible moves
     */
    public static HashMap<Integer, String> getPossibleMoves(Piece piece, BoardConnectPieces boardConnectPieces) {
        HashMap<Integer, String> ris = new HashMap<>();
        for (Character x = 'a'; x <= 'h'; x++) {
            for (Integer y = 1; y <= 8; y++) {
                if (isThisAPossibleMove(piece, boardConnectPieces, x, y)) {
                    ris.put((x * 10) + y, x.toString() + y.toString());
                }
            }
        }
        return ris;
    }

    /**
     * Can this piece move to that coordinate?
     */
    public static boolean isThisAPossibleMove(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        return canPieceMoveHere(piece, boardConnectPieces, x, y) && isntSameColorPieceThere(piece, boardConnectPieces, x, y) && ComplexRules.isThisALegalMove(piece, boardConnectPieces.getPieces(), x, y);
    }

    protected static boolean canPieceMoveHere(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
        PieceType pieceType = piece.getPieceType();
        boolean ris = false;
        if (pieceType == PieceType.Pawn) {
            ris = canPawnMoveHere(piece, boardConnectPieces, x, y);
        } else if (pieceType == PieceType.Bishop) {
            ris = canBishopMoveHere(piece, x, y);
        } else if (pieceType == PieceType.Knight) {
            ris = canKnightMoveHere(piece, x, y);
        } else if (pieceType == PieceType.Tower) {
            ris = canTowerMoveHere(piece, x, y);
        } else if (pieceType == PieceType.King) {
            ris = canKingMoveHere(piece, x, y);
        } else if (pieceType == PieceType.Queen) {
            ris = canQueenMoveHere(piece, x, y);
        }

        return ris;
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
        if (piece.getPieceColor() == PieceColor.WHITE) {
            return PawnRules.canWhitePawnMoveHere(piece, boardConnectPieces, x, y);
        } else if (piece.getPieceColor() == PieceColor.BLACK) {
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
        return (x + 1 == piece.getPosX() || x - 1 == piece.getPosX()) &&
                (y + 1 == piece.getPosY() || y - 1 == piece.getPosY()) ||
                (x == piece.getPosX()) && (y + 1 == piece.getPosY() || y - 1 == piece.getPosY()) ||
                (y == piece.getPosY()) && (x + 1 == piece.getPosX() || x - 1 == piece.getPosX());
    }

    private static boolean canQueenMoveHere(Piece piece, char x, int y) {
        return canTowerMoveHere(piece, x, y) || canBishopMoveHere(piece, x, y);
    }
}
