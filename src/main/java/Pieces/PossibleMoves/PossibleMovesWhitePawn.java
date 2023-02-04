package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;

public class PossibleMovesWhitePawn extends PossibleMovesPawn {
    @Override
    public ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size) {
        ArrayList<Position> positions = new ArrayList<>();
        Position piecePos = piece.getPosition();
        char pX = piecePos.getX();
        int pY = piecePos.getY();

        positions.add(new Position(pX, pY + 1));
        if (!piece.hasMoved()) {
            positions.add(new Position(pX, pY + 2));
        }
        return positions;
    }

    /**
     * Take piece
     */
    @Override
    protected ArrayList<Position> extraMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
        Position posTL = new Position((char) (piece.getPosition().getX() - 1), piece.getPosition().getY() + 1);
        Position posTR = new Position((char) (piece.getPosition().getX() + 1), piece.getPosition().getY() + 1);
        return getPawnTakesMoves(positions, piece, board, posTL, posTR);
    }

    @Override
    protected ArrayList<Position> removeTroughPieceIllegalMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
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

        return removePawnForwardMovement(positions, board, removePos1, removePos2);
    }

    @Override
    protected ArrayList<Position> removeCheckIllegalMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
        return positions;
    }
}
