package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;

public class PossibleMovesBishop extends PossibleMoves {
    @Override
    public ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size) {
        Position piecePos = piece.getPosition();
        ArrayList<Position> positions = new ArrayList<>();

        return positions;
    }


    @Override
    protected ArrayList<Position> removeTroughPiecesIllegalMove(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
        return positions;
    }

    @Override
    protected ArrayList<Position> removeCheckIllegalMove(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
        return positions;
    }
}
