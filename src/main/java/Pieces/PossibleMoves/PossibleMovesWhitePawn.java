package Pieces.PossibleMoves;

import Board.BoardSize;
import Pieces.Piece;
import Pieces.Position;

import java.util.HashSet;

public class PossibleMovesWhitePawn extends PossibleMovesPawn {
    @Override
    public void addPossibleMovesPerPiece(Piece piece, BoardSize size) {
        Position piecePos = piece.getPosition();
        char pX = piecePos.getX();
        int pY = piecePos.getY();

        positions.add(new Position(pX, pY + 1));
        if (!piece.hasMoved()) {
            positions.add(new Position(pX, pY + 2));
        }
    }

    /**
     * Take piece
     */
    @Override
    protected void extraMoves(Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
        Position posTL = new Position((char) (piece.getPosition().getX() - 1), piece.getPosition().getY() + 1);
        Position posTR = new Position((char) (piece.getPosition().getX() + 1), piece.getPosition().getY() + 1);

        addPawnTakesMoves(piece, posTL, posTR, boardSize, pieces);
    }

    @Override
    protected void removeThroughPieceIllegalMoves(Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
        Position piecePos = piece.getPosition();
        char pX = piecePos.getX();
        int pY = piecePos.getY();

        Position removePos1 = null;
        Position removePos2 = null;

        for (Position pos : positions) {
            if (pos.equals(new Position(pX, pY + 1))) {
                removePos1 = pos;
            }
            if (pos.equals(new Position(pX, pY + 2))) {
                removePos2 = pos;
            }
        }

        removePawnForwardMovement(removePos1, removePos2, boardSize, pieces);
    }
}
