package Rules;

import Game.Game;
import Pieces.*;

import java.util.HashSet;

public class ThreatRules {
    public static boolean isCheck(HashSet<Piece> pieces) {
        if (Game.whitePlayer.isTurn()) {
            return isCheckThisColorK(PieceColor.WHITE, pieces);
        } else if (Game.blackPlayer.isTurn()) {
            return isCheckThisColorK(PieceColor.BLACK, pieces);
        }

        assert false;
        return false;
    }

    public static boolean isCheck2(HashSet<Piece> pieces) {
        Piece piece = getKing(PieceColor.WHITE, pieces);
        assert piece != null;
//        System.out.println("piece.isTurn(): " + piece.isTurn());
        if (piece.isTurn()) {
            return isCheckThisColorK(PieceColor.WHITE, pieces);
        } else {
            return isCheckThisColorK(PieceColor.BLACK, pieces);
        }
    }

    public static boolean isOpponentCheck(HashSet<Piece> pieces) { // TODO: Is this used right?
        if (Game.whitePlayer.isTurn()) {
            return isCheckThisColorK(PieceColor.BLACK, pieces);
        } else if (Game.blackPlayer.isTurn()) {
            return isCheckThisColorK(PieceColor.WHITE, pieces);
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

    public static boolean willThisMoveCauseCheck(Piece piece, HashSet<Piece> pieces, Position position) {
/*
        FutureBoard futureBoard = FutureBoard.getNewInstance();
        futureBoard.setBoardPieces();
        Piece futurePiece = PieceFactory.newPiece(piece.getPieceType(), piece.getPieceColor(), position);
        futureBoard.removePiece(piece);
        futureBoard.addPiece(futurePiece);

        //TODO: THERE'S THE BUG
        for (Piece fPiece : futureBoard.getPieces()) {
            fPiece.getPossibleMoves().setPossibleMovesOnBoard(fPiece, futureBoard.getPieces());
        }
        return isCheck(futureBoard.getPieces());
 */
        return false;
    }

    private static HashSet<Piece> getPiecesCopy(HashSet<Piece> pieces) {
        HashSet<Piece> ris = new HashSet<>();
        for (Piece p : pieces) {
            ris.add(PieceFactory.newPiece(p.getPieceType(), p.getPieceColor(), p.getPosition()));
        }
        return ris;
    }

    public static boolean isThisPositionThreatened(PieceColor pieceColor, HashSet<Piece> pieces, Position position) {
        HashSet<Position> enemyPossiblePositions = getEnemyPossiblePositions(pieceColor, pieces);

        for (Position enemyPosition : enemyPossiblePositions) {
            if (enemyPosition.equals(position)) {
                return true;
            }
        }

        for (Piece piece : pieces) {
            if (piece.getPosition().equals(position)) {
                return isThisPieceProtected(piece, pieces);
            }
        }

        return false;
    }

    private static boolean isThisPieceProtected(Piece piece, HashSet<Piece> pieces) {
        HashSet<Piece> piecesWithoutPiece = getEnemyPieces(piece.getPieceColor(), pieces);
        piecesWithoutPiece.remove(piece);
        // TODO: FIX THISSS
        HashSet<Position> possiblePositions = getEnemyPossiblePositions(piece.getPieceColor(), piecesWithoutPiece);
        if (possiblePositions.contains(piece.getPosition())) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isThisPositionThreatenedOptimized(PieceColor pieceColor, HashSet<Piece> pieces, Position position) {
        //TODO: NOT SURE IF IT WORKS, NEEDS TO BE TESTED AT THE END
        // (We use '.equals' in the other one, here it's probably used '==')
        HashSet<Position> enemyPossiblePositions = getEnemyPossiblePositions(pieceColor, pieces);

        //        System.out.println(enemyPossiblePositions.contains(position));
        return enemyPossiblePositions.contains(position);
    }

    private static HashSet<Piece> getEnemyPieces(PieceColor allyPieceColor, HashSet<Piece> pieces) {
        HashSet<Piece> enemyPieces = new HashSet<>();
        for (Piece enemyPiece : pieces) {
            if (enemyPiece.getPieceColor() != allyPieceColor) {
                if (enemyPiece.getPieceType() != PieceType.Pawn) {
                    enemyPieces.add(enemyPiece);
                }
            }
        }
        return enemyPieces;
    }

    private static HashSet<Piece> getEnemyPawns(PieceColor allyPieceColor, HashSet<Piece> pieces) {
        HashSet<Piece> enemyPawns = new HashSet<>();
        for (Piece enemyPiece : pieces) {
            if (enemyPiece.getPieceColor() != allyPieceColor) {
                if (enemyPiece.getPieceType() == PieceType.Pawn) {
                    enemyPawns.add(enemyPiece);
                }
            }
        }
        return enemyPawns;
    }

    private static HashSet<Position> getEnemyPossiblePositions(PieceColor allyPieceColor, HashSet<Piece> pieces) {
        HashSet<Piece> enemyPieces = getEnemyPieces(allyPieceColor, pieces);
        HashSet<Piece> enemyPawns = getEnemyPawns(allyPieceColor, pieces);
        HashSet<Position> enemyPossiblePositions = new HashSet<>();


        for (Piece enemyPiece : enemyPieces) {
            // TODO: FIX THISSSSSSS
            enemyPossiblePositions.addAll(enemyPiece.getPossibleMoves().getPositions());
        }
        for (Piece enemyPawn : enemyPawns) {
            enemyPossiblePositions.addAll(((PawnPiece) enemyPawn).getThreatPosition());
        }
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