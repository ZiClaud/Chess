package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;

public class PossibleMovesBlackPawn extends PossibleMovesPawn {
    @Override
    public ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size) {
        Position piecePos = piece.getPosition();
        ArrayList<Position> positions = new ArrayList<>();
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
    protected ArrayList<Position> extraMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
        Position posBL = new Position((char) (piece.getPosition().getX() - 1), piece.getPosition().getY() - 1);
        Position posBR = new Position((char) (piece.getPosition().getX() + 1), piece.getPosition().getY() - 1);
        return getPawnTakesMoves(positions, piece, board, posBL, posBR);
    }

    @Override
    protected ArrayList<Position> removeTroughPieceIllegalMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
        return positions;
    }

    @Override
    protected ArrayList<Position> removeCheckIllegalMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
        return positions;
    }
}
