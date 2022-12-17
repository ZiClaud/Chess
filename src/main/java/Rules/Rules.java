package Rules;

import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.PieceType;

import java.awt.*;

public class Rules {
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
            return canTowerMoveHere(piece, x, y) && isntSameColorPieceThere(piece, boardConnectPieces, x, y);
        } else if (pieceType == PieceType.King) {
            return canKingMoveHere(piece, x, y) && isntSameColorPieceThere(piece, boardConnectPieces, x, y);
        } else if (pieceType == PieceType.Queen) {
            return canQueenMoveHere(piece, x, y) && isntSameColorPieceThere(piece, boardConnectPieces, x, y);
        }

        assert (false);
        return false;
    }

    private static boolean isPieceThere(Piece piece, BoardConnectPieces boardConnectPieces, char x, int y) {
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
        // TODO: El Passant
        if (piece.getPieceColor() == Color.WHITE) {
            // Move top right/top left
            if (x == piece.getPosX() + 1 && y == piece.getPosY() + 1 ||
                    x == piece.getPosX() - 1 && y == piece.getPosY() + 1) {
                if (isPieceThere(piece, boardConnectPieces, ((char) (piece.getPosX() + 1)), piece.getPosY() + 1) ||
                        isPieceThere(piece, boardConnectPieces, ((char) (piece.getPosX() - 1)), piece.getPosY() + 1)) {
                    return true;
                }
            }

            // stops if path is blocked
            if (x == piece.getPosX() && y == piece.getPosY() + 1) {
                if (isPieceThere(piece, boardConnectPieces, x, y)){
                    return false;
                }
            }

            if (piece.getPosY() == 2) {
                return piece.getPosX() == x &&
                        (piece.getPosY() == y - 1 ||
                                piece.getPosY() == y - 2);
            } else {
                return piece.getPosX() == x &&
                        piece.getPosY() == y - 1;
            }
        } else {    // If pawn is black
            // Move bottom right/bottom left
            if (x == piece.getPosX() - 1 && y == piece.getPosY() - 1 ||
                    x == piece.getPosX() + 1 && y == piece.getPosY() - 1) {
                if (isPieceThere(piece, boardConnectPieces, ((char) (piece.getPosX() - 1)), piece.getPosY() - 1) ||
                        isPieceThere(piece, boardConnectPieces, ((char) (piece.getPosX() + 1)), piece.getPosY() - 1)) {
                    return true;
                }
            }

            // stops if path is blocked
            if (x == piece.getPosX() && y == piece.getPosY() - 1) {
                if (isPieceThere(piece, boardConnectPieces, x, y)){
                    return false;
                }
            }

            if (piece.getPosY() == 7) {
                return piece.getPosX() == x &&
                        (piece.getPosY() == y + 1 ||
                                piece.getPosY() == y + 2);
            } else {
                return piece.getPosX() == x &&
                        piece.getPosY() == y + 1;
            }
        }
    }

    private static boolean canBishopMoveHere(Piece piece, char x, int y) {
        char pX = piece.getPosX();
        int pY = piece.getPosY();
        return (pX + pY == x + y) ||
                (pX - pY == x - y);
    }

    private static boolean canKnightMoveHere(Piece piece, char x, int y) {
        // TODO: Caslte
        // TODO: Don't let king walk in a capturable tile
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
