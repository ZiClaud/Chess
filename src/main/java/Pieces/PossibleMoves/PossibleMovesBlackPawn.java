package Pieces.PossibleMoves;

import Board.BoardSize;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;
import java.util.HashSet;

public class PossibleMovesBlackPawn extends PossibleMovesPawn {
    @Override
    public ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size) {
        ArrayList<Position> positions = new ArrayList<>();
        Position piecePos = piece.getPosition();
        char pX = piecePos.getX();
        int pY = piecePos.getY();

        positions.add(new Position(pX, pY - 1));
        if (!piece.hasMoved()) {
            positions.add(new Position(pX, pY - 2));
        }
        return positions;
    }

    /**
     * Take piece
     */
    @Override
    protected void extraMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
        Position posBL = new Position((char) (piece.getPosition().getX() - 1), piece.getPosition().getY() - 1);
        Position posBR = new Position((char) (piece.getPosition().getX() + 1), piece.getPosition().getY() - 1);
        addPawnTakesMoves(positions, piece, posBL, posBR, boardSize, pieces);
    }

    @Override
    protected void removeThroughPieceIllegalMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
        Position piecePos = piece.getPosition();
        char pX = piecePos.getX();
        int pY = piecePos.getY();

        Position removePos1 = null;
        Position removePos2 = null;

        for (Position pos : positions) {
            if (pos.equals(new Position(pX, pY - 1))) {
                removePos1 = pos;
            }
            if (pos.equals(new Position(pX, pY - 2))) {
                removePos2 = pos;
            }
        }

        removePawnForwardMovement(positions, removePos1, removePos2, boardSize, pieces);
    }

    @Override
    protected void removeCheckIllegalMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
    }
}
