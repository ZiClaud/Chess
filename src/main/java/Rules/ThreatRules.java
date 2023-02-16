package Rules;

import Game.Game;
import Pieces.*;

import java.util.HashSet;

public class ThreatRules {
    public static boolean isCheck(HashSet<Piece> pieces) {
        if (Game.whitePlayer.isTurn()) {
            return isCheckThisColorK(PieceColor.WHITE, pieces);
        }
        if (Game.blackPlayer.isTurn()) {
            return isCheckThisColorK(PieceColor.BLACK, pieces);
        }

        assert false;
        return false;
    }

    private static boolean isCheckThisColorK(PieceColor pieceColor, HashSet<Piece> pieces) {
        Piece king = getKing(pieceColor, pieces);
        assert king != null;
        return isThisPositionThreatened(king.getPieceColor(), pieces, king.getPosition());
    }

    public static Piece getKing(PieceColor pieceColor, HashSet<Piece> pieces) {
        for (Piece king : pieces) {
            if (king.getPieceType() == PieceType.King &&
                    king.getPieceColor() == pieceColor) {
                return king;
            }
        }
        assert false;
        return null;
    }

    // TODO: Fix for pawns
    public static boolean isThisPositionThreatened(PieceColor pieceColor, HashSet<Piece> pieces, Position position) {
        HashSet<Position> enemyPossiblePositions = getEnemyPossiblePositions(pieceColor, pieces);

        for (Position enemyPosition : enemyPossiblePositions) {
            if (enemyPosition.equals(position)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isThisPositionThreatenedOptimized(PieceColor pieceColor, HashSet<Piece> pieces, Position position) {
        //TODO: NOT SURE IF IT WORKS, NEEDS TO BE TESTED AT THE END
        // (We use '.equals' in the other one, here it's probably used '==')
        HashSet<Position> enemyPossiblePositions = getEnemyPossiblePositions(pieceColor, pieces);

        //        System.out.println(enemyPossiblePositions.contains(position));
        return enemyPossiblePositions.contains(position);
    }

    private static HashSet<Position> getEnemyPossiblePositions(PieceColor allyPieceColor, HashSet<Piece> pieces) {
        HashSet<Piece> enemyPieces = new HashSet<>();
        HashSet<Piece> enemyPawns = new HashSet<>();
        HashSet<Position> enemyPossiblePositions = new HashSet<>();

        for (Piece enemyPiece : pieces) {
            if (enemyPiece.getPieceColor() != allyPieceColor) {
                if (enemyPiece.getPieceType() != PieceType.Pawn) {
                    enemyPieces.add(enemyPiece);
                } else {
                    enemyPawns.add(enemyPiece);
                }
            }
        }

        for (Piece enemyPiece : enemyPieces) {
            enemyPossiblePositions.addAll(enemyPiece.getPossibleMoves().getPositions());
        }
        for (Piece enemyPawn : enemyPawns) {
            enemyPossiblePositions.addAll(((PawnPiece)enemyPawn).getThreatPosition());
        }
//        System.out.println("enemyPossiblePositions: " + enemyPossiblePositions);
        return enemyPossiblePositions;
    }
}
    /*
    public static boolean doesStopCheck(HashSet<Piece> pieces, Piece piece, Position position) {
        if (piece.getPieceType() == PieceType.King) {
            return !isThisPositionThreatened(pieces, position);
        }

        HashSet<Piece> futurePieces = new HashSet<>(pieces);

        for (Piece enemyP : pieces) {
            if (enemyP.getPosition().equals(position) && enemyP.getPieceType() != PieceType.King) {
                futurePieces.remove(enemyP);
            }
        }

        futurePieces.add(PieceFactory.newPiece(piece.getPieceColor(), piece.getPieceType(), position));

        if (piece.getPieceColor() == PieceColor.WHITE) {
            return !isCheckWhiteK(futurePieces);
        } else {
            return !isCheckBlackK(futurePieces);
        }
    }
    */

    /*
    public static boolean willThisMoveCauseCheck(Piece piece, HashSet<Piece> pieces, Position position) {
        boolean ris = false;

        HashSet<Piece> futurePieces = new HashSet<>(pieces);
        futurePieces.remove(piece);
        for (Piece enemyP : pieces) {
            if (enemyP.getPosition().equals(position) && enemyP.getPieceType() != PieceType.King) {
                futurePieces.remove(enemyP);
            }
        }
        futurePieces.add(PieceFactory.newPiece(piece.getPieceColor(), piece.getPieceType(), position));

        if (isCheckWhiteK(futurePieces) && Game.whitePlayer.isTurn() ||
                isCheckBlackK(futurePieces) && Game.blackPlayer.isTurn()) {
            ris = true;
        }

        return ris;
    }
    */