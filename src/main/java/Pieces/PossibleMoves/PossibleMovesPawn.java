package Pieces.PossibleMoves;

import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;

/**
 * This is abstract because it can be both white and black pawn
 */
public abstract class PossibleMovesPawn extends PossibleMoves {
    protected boolean allowsElPassant = false;

    protected static ArrayList<Position> getPawnTakesMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board, Position posTakeLeft, Position posTakeRight) {
        for (Piece enemyPiece : board.getPieces()) {
            if (enemyPiece.getPieceColor() != piece.getPieceColor()) {
                if (enemyPiece.getPosition().equals(posTakeLeft)) { // TODO: if is pawn and allowsElPassant then..
                    positions.add(enemyPiece.getPosition());
                }
                if (enemyPiece.getPosition().equals(posTakeRight)) { // TODO: if is pawn and allowsElPassant then..
                    positions.add(enemyPiece.getPosition());
                }
            }
        }
        return positions;
    }
}
    /*
        @Override
        public ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size) {
            if (piece.getPieceType() == PieceType.Pawn) {
                ArrayList<Position> positions = new ArrayList<>();
                Pawn pawn = (Pawn) piece;
                if (pawn.hasMoved()) {
                    if (pawn.getPieceColor() == PieceColor.WHITE) {
                        positions = whitePawnInStartingPos(pawn);
                    } else if (pawn.getPieceColor() == PieceColor.BLACK) {
                        positions = blackPawnInStartingPos(pawn);
                    }
                } else if (!pawn.hasMoved()) {
                    if (pawn.getPieceColor() == PieceColor.WHITE) {
                        positions = whitePawnNotInStartingPos(pawn);
                    } else if (pawn.getPieceColor() == PieceColor.BLACK) {
                        positions = blackPawnNotInStartingPos(pawn);
                    }
                }
                return positions;
            }
            assert false;
            return null;
        }

        private ArrayList<Position> whitePawnInStartingPos(Pawn pawn) {
            ArrayList<Position> positions = new ArrayList<>();
            positions.add(new Position(pawn.getPosition().getX(), pawn.getPosition().getY() + 1));
            positions.add(new Position(pawn.getPosition().getX(), pawn.getPosition().getY() + 2));
            return positions;
        }

        private ArrayList<Position> blackPawnInStartingPos(Pawn pawn) {
            ArrayList<Position> positions = new ArrayList<>();
            positions.add(new Position(pawn.getPosition().getX(), pawn.getPosition().getY() - 1));
            positions.add(new Position(pawn.getPosition().getX(), pawn.getPosition().getY() - 2));
            return positions;
        }

        private ArrayList<Position> whitePawnNotInStartingPos(Pawn pawn) {
            ArrayList<Position> positions = new ArrayList<>();
            positions.add(new Position(pawn.getPosition().getX(), pawn.getPosition().getY() + 1));
            return positions;
        }

        private ArrayList<Position> blackPawnNotInStartingPos(Pawn pawn) {
            ArrayList<Position> positions = new ArrayList<>();
            positions.add(new Position(pawn.getPosition().getX(), pawn.getPosition().getY() - 1));
            return positions;
        }

        /**
         * extraMoves: When pawn can take topRight/topLeft
         *
    protected ArrayList<Position> extraMoves(ArrayList<Position> positions, PieceAbst piece, BoardConnectPieces board) {
        if (piece.getPieceType() == PieceType.Pawn) {
            // TODO: Finish
            return positions;
        }
        assert false;
        return null;
    }

    private boolean whitePawnCanTakeRight() {
        // TODO: Finish
        return false;
    }

    private boolean whitePawnCanTakeLeft() {
        // TODO: Finish
        return false;
    }

    private boolean blackPawnCanTakeRight() {
        // TODO: Finish
        return false;
    }

    private boolean blackPawnCanTakeLeft() {
        // TODO: Finish
        return false;
    }
}
*/
