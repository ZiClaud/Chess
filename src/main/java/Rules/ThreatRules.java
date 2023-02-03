package Rules;

/*
public class ThreatRules {
    public static boolean isCheckWhiteK(HashSet<PieceAbst> pieces) {
        PieceAbst king = null;
        for (PieceAbst piece : pieces) {
            if (piece.getPieceColor() == PieceColor.WHITE && piece.getPieceType() == PieceType.King) {
                king = piece;
                break;
            }
        }

        assert king != null;

        for (PieceAbst piece : pieces) {
            if (piece.getPieceColor() == PieceColor.BLACK && PiecesRules.isThisAPossibleMove(piece, pieces, king.getPosX(), king.getPosY())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isCheckBlackK(HashSet<PieceAbst> pieces) {
        PieceAbst king = null;
        for (PieceAbst piece : pieces) {
            if (piece.getPieceColor() == PieceColor.BLACK && piece.getPieceType() == PieceType.King) {
                king = piece;
            }
        }

        assert king != null;

        for (PieceAbst piece : pieces) {
            if (piece.getPieceColor() == PieceColor.WHITE &&
                    PiecesRules.isThisAPossibleMove(piece, pieces, king.getPosX(), king.getPosY())) {
                return true;
            }
        }

        return false;
    }

    protected static boolean isThisPositionThreatened(HashSet<PieceAbst> pieces, Character x, Integer y) {
        String xy = x.toString() + y.toString();
        for (PieceAbst enemyPiece : pieces) {
            if (((Game.whitePlayer.isTurn() && enemyPiece.getPieceColor() == PieceColor.BLACK) ||
                    (Game.blackPlayer.isTurn() && enemyPiece.getPieceColor() == PieceColor.WHITE)) &&
                    PiecesRules.getPossibleMoves(enemyPiece, pieces).containsValue(xy)) {
                if (enemyPiece.getPieceType() != PieceType.Pawn) {
                    System.out.println("mhm2 " + enemyPiece + " " + x + y);
                    return true;
                } else if (PawnRules.canThisPawnThreaten(enemyPiece, x, y)) {
                    System.out.println("mhm " + enemyPiece + " " + x + y);
                    return true;
                } else {
                    System.out.println("ok " + enemyPiece + " " + x + y);
                    // TODO ??
                }
            }
        }
        return false;
    }

    public static boolean doesStopCheck(HashSet<PieceAbst> pieces, PieceAbst piece, char x, int y) {
        if (piece.getPieceType() == PieceType.King) {
            return !isThisPositionThreatened(pieces, x, y);
        }

        HashSet<PieceAbst> futurePieces = new HashSet<>(pieces);

        for (PieceAbst enemyP : pieces) {
            if (enemyP.getPosX() == x && enemyP.getPosY() == y && enemyP.getPieceType() != PieceType.King) {
                futurePieces.remove(enemyP);
            }
        }

        futurePieces.add(PieceFactory.newPiece(piece.getPieceColor(), piece.getPieceType(), x, y));

        if (piece.getPieceColor() == PieceColor.WHITE) {
            return !isCheckWhiteK(futurePieces);
        } else {
            return !isCheckBlackK(futurePieces);
        }
    }

    public static boolean willThisMoveCauseCheck(PieceAbst piece, HashSet<PieceAbst> pieces, Character x, Integer y) {
        boolean ris = false;

        HashSet<PieceAbst> futurePieces = new HashSet<>(pieces);
        futurePieces.remove(piece);
        for (PieceAbst enemyP : pieces) {
            if (enemyP.getPosX() == x && enemyP.getPosY() == y && enemyP.getPieceType() != PieceType.King) {
                futurePieces.remove(enemyP);
            }
        }
        futurePieces.add(PieceFactory.newPiece(piece.getPieceColor(), piece.getPieceType(), x, y));

        if (isCheckWhiteK(futurePieces) && Game.whitePlayer.isTurn() ||
                isCheckBlackK(futurePieces) && Game.blackPlayer.isTurn()) {
            ris = true;
        }

        return ris;
    }
}
*/