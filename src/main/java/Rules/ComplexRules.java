package Rules;

/*
public class ComplexRules {
    public static boolean isThisALegalMove(PieceAbst piece, HashSet<PieceAbst> pieces, char x, int y) {
        return !isGoingThroughPieceToGetThere(piece, pieces, x, y);
    }

    private static boolean isGoingThroughPieceToGetThere(PieceAbst piece, HashSet<PieceAbst> pieces, char x, int y) {
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

    private static boolean isQueenGoingThroughPiecesToGetThere(PieceAbst queen, HashSet<PieceAbst> pieces, char x, int y) {
        return (isBishopGoingThroughPiecesToGetThere(queen, pieces, x, y) || isTowerGoingThroughPiecesToGetThere(queen, pieces, x, y));
    }

    private static boolean isBishopGoingThroughPiecesToGetThere(PieceAbst bishop, HashSet<PieceAbst> pieces, char x, int y) {
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

    private static boolean isBishopGoingTopLeft(PieceAbst bishop, char x, int y) {
        return (bishop.getPosX() > x && bishop.getPosY() < y);
    }

    private static boolean isPieceInBishopWayTopLeft(PieceAbst bishop, HashSet<PieceAbst> pieces, char x, int y) {
        for (PieceAbst piece : pieces) {
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

    private static boolean isBishopGoingBottomLeft(PieceAbst bishop, char x, int y) {
        return (bishop.getPosX() > x && bishop.getPosY() > y);
    }

    private static boolean isPieceInBishopWayBottomLeft(PieceAbst bishop, HashSet<PieceAbst> pieces, char x, int y) {
        for (PieceAbst piece : pieces) {
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

    private static boolean isBishopGoingTopRight(PieceAbst bishop, char x, int y) {
        return (bishop.getPosX() < x && bishop.getPosY() < y);
    }

    private static boolean isPieceInBishopWayTopRight(PieceAbst bishop, HashSet<PieceAbst> pieces, char x, int y) {
        for (PieceAbst piece : pieces) {
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

    private static boolean isBishopGoingBottomRight(PieceAbst bishop, char x, int y) {
        return (bishop.getPosX() < x && bishop.getPosY() > y);
    }

    private static boolean isPieceInBishopWayBottomRight(PieceAbst bishop, HashSet<PieceAbst> pieces, char x, int y) {
        for (PieceAbst piece : pieces) {
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

    private static boolean isTowerGoingThroughPiecesToGetThere(PieceAbst tower, HashSet<PieceAbst> pieces, char x, int y) {
        if (tower.getPosX() == x) {
            return isXTowerGoingThroughPiecesToGetThere(tower, pieces, x, y);
        } else if (tower.getPosY() == y) {
            return isYTowerGoingThroughPiecesToGetThere(tower, pieces, x, y);
        }
        assert (false);
        return false;
    }

    private static boolean isXTowerGoingThroughPiecesToGetThere(PieceAbst tower, HashSet<PieceAbst> pieces, char x, int y) {
        char towerX = tower.getPosX();
        int towerY = tower.getPosY();
        char pieceX;
        int pieceY;

        for (PieceAbst piece : pieces) {
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

    private static boolean isYTowerGoingThroughPiecesToGetThere(PieceAbst tower, HashSet<PieceAbst> pieces, char x, int y) {
        char towerX = tower.getPosX();
        int towerY = tower.getPosY();
        char pieceX;
        int pieceY;

        for (PieceAbst piece : pieces) {
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

    public static boolean canThisKingCastleRight(PieceAbst king, HashSet<PieceAbst> pieces) {
        if (king.getPieceType() == PieceType.King && ((KingOld) king).canCastle()) {
            for (PieceAbst piece : pieces) {
                if (piece.getPosY() == king.getPosY()
                        && (piece.getPosX() == king.getPosX() + 1
                        || piece.getPosX() == king.getPosX() + 2)) {
                    return false;
                }
            }
            if (ThreatRules.isThisPositionThreatened(pieces, king.getPosX(), king.getPosY())
                    || ThreatRules.isThisPositionThreatened(pieces, (char) (king.getPosX() + 1), king.getPosY())
                    || ThreatRules.isThisPositionThreatened(pieces, (char) (king.getPosX() + 2), king.getPosY())) {
                return false;
            }
            return true;
        }
        assert false;
        return false;
    }

    public static boolean canThisKingCastleLeft(PieceAbst king, HashSet<PieceAbst> pieces) {
        if (king.getPieceType() == PieceType.King && ((KingOld) king).canCastle()) {
            for (PieceAbst piece : pieces) {
                if (piece.getPosY() == king.getPosY()
                        && (piece.getPosX() == king.getPosX() - 1
                        || piece.getPosX() == king.getPosX() - 2
                        || piece.getPosX() == king.getPosX() - 3)) {
                    return false;
                }
            }
            if (ThreatRules.isThisPositionThreatened(pieces, king.getPosX(), king.getPosY()) ||
                    ThreatRules.isThisPositionThreatened(pieces, (char) (king.getPosX() - 1), king.getPosY()) ||
                    ThreatRules.isThisPositionThreatened(pieces, (char) (king.getPosX() - 2), king.getPosY()) ||
                    ThreatRules.isThisPositionThreatened(pieces, (char) (king.getPosX() - 3), king.getPosY())) {
                return false;
            }
            return true;
        }
        assert false;
        return false;
    }
}
*/