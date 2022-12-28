package Rules;

import BoardPieces.BoardConnectPieces;
import Pieces.King;
import Pieces.Piece;
import Pieces.PieceType;

import java.util.HashSet;

public class ComplexRules {
    public static boolean isThisALegalMove(Piece piece, HashSet<Piece> pieces, char x, int y) {
        return !isGoingThroughPieceToGetThere(piece, pieces, x, y);
    }

    private static boolean isGoingThroughPieceToGetThere(Piece piece, HashSet<Piece> pieces, char x, int y) {
        PieceType pieceType = piece.getPieceType();

        if (pieceType == PieceType.Pawn && isTowerGoingThroughPiecesToGetThere(piece, pieces, x, y)) {
            return true;
        } else if (pieceType == PieceType.Bishop && isBishopGoingThroughPiecesToGetThere(piece, pieces, x, y)) {
            return true;
        } else if (pieceType == PieceType.Tower && isTowerGoingThroughPiecesToGetThere(piece, pieces, x, y)) {
            return true;
        } else if (pieceType == PieceType.Queen && isQueenGoingThroughPiecesToGetThere(piece, pieces, x, y)) {
            return true;
        }

        return false;
    }

    private static boolean isQueenGoingThroughPiecesToGetThere(Piece queen, HashSet<Piece> pieces, char x, int y) {
        return (isBishopGoingThroughPiecesToGetThere(queen, pieces, x, y) || isTowerGoingThroughPiecesToGetThere(queen, pieces, x, y));
    }

    private static boolean isBishopGoingThroughPiecesToGetThere(Piece bishop, HashSet<Piece> pieces, char x, int y) {
        if (isBishopGoingTopLeft(bishop, x, y)) {
            return isPieceInBishopWayTopLeft(bishop, pieces, x, y);
        } else if (isBishopGoingBottomLeft(bishop, x, y)) {
            return isPieceInBishopWayBottomLeft(bishop, pieces, x, y);
        } else if (isBishopGoingTopRight(bishop, x, y)) {
            return isPieceInBishopWayTopRight(bishop, pieces, x, y);
        } else if (isBishopGoingBottomRight(bishop, x, y)) {
            return isPieceInBishopWayBottomRight(bishop, pieces, x, y);
        }
        assert (false);
        return false;
    }

    private static boolean isBishopGoingTopLeft(Piece bishop, char x, int y) {
        return (bishop.getPosX() > x && bishop.getPosY() < y);
    }

    private static boolean isPieceInBishopWayTopLeft(Piece bishop, HashSet<Piece> pieces, char x, int y) {
        for (Piece piece : pieces) {
            for (int i = 1; i < 8; i++) {
                if (piece.getPosX() == bishop.getPosX() - i &&
                        piece.getPosY() == bishop.getPosY() + i) {
                    if (piece.getPosX() > x && piece.getPosY() < y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isBishopGoingBottomLeft(Piece bishop, char x, int y) {
        return (bishop.getPosX() > x && bishop.getPosY() > y);
    }

    private static boolean isPieceInBishopWayBottomLeft(Piece bishop, HashSet<Piece> pieces, char x, int y) {
        for (Piece piece : pieces) {
            for (int i = 1; i < 8; i++) {
                if (piece.getPosX() == bishop.getPosX() - i &&
                        piece.getPosY() == bishop.getPosY() - i) {
                    if (piece.getPosX() > x && piece.getPosY() > y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isBishopGoingTopRight(Piece bishop, char x, int y) {
        return (bishop.getPosX() < x && bishop.getPosY() < y);
    }

    private static boolean isPieceInBishopWayTopRight(Piece bishop, HashSet<Piece> pieces, char x, int y) {
        for (Piece piece : pieces) {
            for (int i = 1; i < 8; i++) {
                if (piece.getPosX() == bishop.getPosX() + i &&
                        piece.getPosY() == bishop.getPosY() + i) {
                    if (piece.getPosX() < x && piece.getPosY() < y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isBishopGoingBottomRight(Piece bishop, char x, int y) {
        return (bishop.getPosX() < x && bishop.getPosY() > y);
    }

    private static boolean isPieceInBishopWayBottomRight(Piece bishop, HashSet<Piece> pieces, char x, int y) {
        for (Piece piece : pieces) {
            for (int i = 1; i < 8; i++) {
                if (piece.getPosX() == bishop.getPosX() + i &&
                        piece.getPosY() == bishop.getPosY() - i) {
                    if (piece.getPosX() < x && piece.getPosY() > y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isTowerGoingThroughPiecesToGetThere(Piece tower, HashSet<Piece> pieces, char x, int y) {
        if (tower.getPosX() == x) {
            return isXTowerGoingThroughPiecesToGetThere(tower, pieces, x, y);
        } else if (tower.getPosY() == y) {
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
                if (x < towerX && x < pieceX && towerX > pieceX) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canThisKingCastleRight(Piece king, BoardConnectPieces boardConnectPieces) {
        if (king.getPieceType() == PieceType.King && ((King) king).canCastle()) {
            for (Piece piece : boardConnectPieces.getPieces()) {
                if (piece.getPosY() == king.getPosY() && (piece.getPosX() == king.getPosX() + 1 || piece.getPosX() == king.getPosX() + 2)) {
                    return false;
                }
            }
            return true;
        }
        assert false;
        return false;
    }

    public static boolean canThisKingCastleLeft(Piece king, BoardConnectPieces boardConnectPieces) {
        if (king.getPieceType() == PieceType.King && ((King) king).canCastle()) {
            for (Piece piece : boardConnectPieces.getPieces()) {
                if (piece.getPosY() == king.getPosY() && (piece.getPosX() == king.getPosX() - 1 || piece.getPosX() == king.getPosX() - 2 || piece.getPosX() == king.getPosX() - 3)) {
                    return false;
                }
            }
            return true;
        }
        assert false;
        return false;
    }
}
