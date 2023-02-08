package Rules;

import Pieces.Piece;
import Pieces.PieceType;
import Pieces.Position;

import java.util.HashSet;

public class ComplexRules {
    public static boolean isGoingThroughPieceToGetThere(Piece piece, HashSet<Piece> pieces, Position position) {
        char x = position.getX();
        int y = position.getY();

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
        return (bishop.getPosition().getX() > x && bishop.getPosition().getY() < y);
    }

    private static boolean isPieceInBishopWayTopLeft(Piece bishop, HashSet<Piece> pieces, char x, int y) {
        for (Piece piece : pieces) {
            for (int i = 1; i < 8; i++) {
                if (piece.getPosition().getX() == bishop.getPosition().getX() - i &&
                        piece.getPosition().getY() == bishop.getPosition().getY() + i) {
                    if (piece.getPosition().getX() > x && piece.getPosition().getY() < y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isBishopGoingBottomLeft(Piece bishop, char x, int y) {
        return (bishop.getPosition().getX() > x && bishop.getPosition().getY() > y);
    }

    private static boolean isPieceInBishopWayBottomLeft(Piece bishop, HashSet<Piece> pieces, char x, int y) {
        for (Piece piece : pieces) {
            for (int i = 1; i < 8; i++) {
                if (piece.getPosition().getX() == bishop.getPosition().getX() - i &&
                        piece.getPosition().getY() == bishop.getPosition().getY() - i) {
                    if (piece.getPosition().getX() > x && piece.getPosition().getY() > y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isBishopGoingTopRight(Piece bishop, char x, int y) {
        return (bishop.getPosition().getX() < x && bishop.getPosition().getY() < y);
    }

    private static boolean isPieceInBishopWayTopRight(Piece bishop, HashSet<Piece> pieces, char x, int y) {
        for (Piece piece : pieces) {
            for (int i = 1; i < 8; i++) {
                if (piece.getPosition().getX() == bishop.getPosition().getX() + i &&
                        piece.getPosition().getY() == bishop.getPosition().getY() + i) {
                    if (piece.getPosition().getX() < x && piece.getPosition().getY() < y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isBishopGoingBottomRight(Piece bishop, char x, int y) {
        return (bishop.getPosition().getX() < x && bishop.getPosition().getY() > y);
    }

    private static boolean isPieceInBishopWayBottomRight(Piece bishop, HashSet<Piece> pieces, char x, int y) {
        for (Piece piece : pieces) {
            for (int i = 1; i < 8; i++) {
                if (piece.getPosition().getX() == bishop.getPosition().getX() + i &&
                        piece.getPosition().getY() == bishop.getPosition().getY() - i) {
                    if (piece.getPosition().getX() < x && piece.getPosition().getY() > y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isTowerGoingThroughPiecesToGetThere(Piece tower, HashSet<Piece> pieces, char x, int y) {
        if (tower.getPosition().getX() == x) {
            return isXTowerGoingThroughPiecesToGetThere(tower, pieces, x, y);
        } else if (tower.getPosition().getY() == y) {
            return isYTowerGoingThroughPiecesToGetThere(tower, pieces, x, y);
        }
        assert (false);
        return false;
    }

    private static boolean isXTowerGoingThroughPiecesToGetThere(Piece tower, HashSet<Piece> pieces, char x, int y) {
        char towerX = tower.getPosition().getX();
        int towerY = tower.getPosition().getY();
        char pieceX;
        int pieceY;

        for (Piece piece : pieces) {
            pieceX = piece.getPosition().getX();
            pieceY = piece.getPosition().getY();
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
        char towerX = tower.getPosition().getX();
        int towerY = tower.getPosition().getY();
        char pieceX;
        int pieceY;

        for (Piece piece : pieces) {
            pieceX = piece.getPosition().getX();
            pieceY = piece.getPosition().getY();
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
/*
    public static boolean isThisALegalMove(Piece piece, HashSet<Piece> pieces, Position position) {
        return !isGoingThroughPieceToGetThere(piece, pieces, position);
    }

    public static boolean canThisKingCastleRight(Piece king, HashSet<Piece> pieces) {
        if (king.getPieceType() == PieceType.King && ((KingOld) king).canCastle()) {
            for (Piece piece : pieces) {
                if (piece.getPosition().getY() == king.getPosition().getY()
                        && (piece.getPosition().getX() == king.getPosition().getX() + 1
                        || piece.getPosition().getX() == king.getPosition().getX() + 2)) {
                    return false;
                }
            }
            if (ThreatRules.isThisPositionThreatened(pieces, king.getPosition().getX(), king.getPosition().getY())
                    || ThreatRules.isThisPositionThreatened(pieces, (char) (king.getPosition().getX() + 1), king.getPosition().getY())
                    || ThreatRules.isThisPositionThreatened(pieces, (char) (king.getPosition().getX() + 2), king.getPosition().getY())) {
                return false;
            }
            return true;
        }
        assert false;
        return false;
    }

    public static boolean canThisKingCastleLeft(Piece king, HashSet<Piece> pieces) {
        if (king.getPieceType() == PieceType.King && ((KingOld) king).canCastle()) {
            for (Piece piece : pieces) {
                if (piece.getPosition().getY() == king.getPosition().getY()
                        && (piece.getPosition().getX() == king.getPosition().getX() - 1
                        || piece.getPosition().getX() == king.getPosition().getX() - 2
                        || piece.getPosition().getX() == king.getPosition().getX() - 3)) {
                    return false;
                }
            }
            if (ThreatRules.isThisPositionThreatened(pieces, king.getPosition().getX(), king.getPosition().getY()) ||
                    ThreatRules.isThisPositionThreatened(pieces, (char) (king.getPosition().getX() - 1), king.getPosition().getY()) ||
                    ThreatRules.isThisPositionThreatened(pieces, (char) (king.getPosition().getX() - 2), king.getPosition().getY()) ||
                    ThreatRules.isThisPositionThreatened(pieces, (char) (king.getPosition().getX() - 3), king.getPosition().getY())) {
                return false;
            }
            return true;
        }
        assert false;
        return false;
    }
    */
}