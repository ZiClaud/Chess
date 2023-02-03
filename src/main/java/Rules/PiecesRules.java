package Rules;

/*
public class PiecesRules {
    /**
     * Get all possible moves
     *
    public static HashMap<Integer, String> getPossibleMoves(Piece piece, HashSet<Piece> pieces) {
        HashMap<Integer, String> ris = new HashMap<>();
        for (Character x = 'a'; x <= 'h'; x++) {
            for (Integer y = 1; y <= 8; y++) {
                if (isThisAPossibleMove(piece, pieces, x, y)) {
                    ris.put((x * 10) + y, x.toString() + y.toString());
                }
            }
        }
        return ris;
    }

    /**
     * Can this piece move to that coordinate?
     *
    public static boolean isThisAPossibleMove(Piece piece, HashSet<Piece> pieces, char x, int y) {
        return canPieceMoveHere(piece, pieces, x, y) && isntSameColorPieceThere(piece, pieces, x, y)
                && ComplexRules.isThisALegalMove(piece, pieces, x, y)
                && !ThreatRules.willThisMoveCauseCheck(piece, pieces, x, y);
    }

    private static boolean canPieceMoveHere(Piece piece, HashSet<Piece> pieces, char x, int y) {
        PieceType pieceType = piece.getPieceType();
        boolean ris = false;
        if (pieceType == PieceType.Pawn) {
            ris = canPawnMoveHere(piece, pieces, x, y);
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

    protected static boolean isPieceThere(HashSet<Piece> pieces, char x, int y) {
        for (Piece boardPiece : pieces) {
            if (boardPiece.getPosX() == x && boardPiece.getPosY() == y) {
                return true;
            }
        }
        return false;
    }

    private static boolean isntSameColorPieceThere(Piece piece, HashSet<Piece> pieces, char x, int y) {
        for (Piece boardPiece : pieces) {
            if (boardPiece.getPosX() == x && boardPiece.getPosY() == y) {
                if (boardPiece.getPieceColor() == piece.getPieceColor()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canPawnMoveHere(Piece piece, HashSet<Piece> pieces, char x, int y) {
        if (piece.getPieceColor() == PieceColor.WHITE) {
            return PawnRules.canWhitePawnMoveHere(piece, pieces, x, y);
        } else if (piece.getPieceColor() == PieceColor.BLACK) {
            return PawnRules.canBlackPawnMoveHere(piece, pieces, x, y);
        }
        assert (false);
        return false;
    }


    private static boolean canBishopMoveHere(Piece piece, char x, int y) {
        char pX = piece.getPosition().getX();
        int pY = piece.getPosition().getY();
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
        return ((x + 1 == piece.getPosX() || x - 1 == piece.getPosX()) &&
                (y + 1 == piece.getPosY() || y - 1 == piece.getPosY()) ||
                (x == piece.getPosX()) && (y + 1 == piece.getPosY() || y - 1 == piece.getPosY()) ||
                (y == piece.getPosY()) && (x + 1 == piece.getPosX() || x - 1 == piece.getPosX()));
    }

    private static boolean canQueenMoveHere(Piece piece, char x, int y) {
        return canTowerMoveHere(piece, x, y) || canBishopMoveHere(piece, x, y);
    }
}
*/