package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;

// TODO: Template?
public abstract class PossibleMoves {
    public ArrayList<Position> getPossibleMovesOnBoard(Piece piece, BoardConnectPieces board) {
        ArrayList<Position> positions = getPossibleMovesPerPiece(piece, board.getWindowBoard().getBoardSize());
        positions = extraMoves(positions, piece, board);
        positions = removeIllegalMoves(positions, piece, board);

        return positions;
    }

    protected ArrayList<Position> removeIllegalMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
        positions = removeTroughPiecesIllegalMove(positions, piece, board);
        positions = removeCheckIllegalMove(positions, piece, board);
        return positions;
    }

    /**
     * Hook
     */
    protected ArrayList<Position> extraMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
        return positions;
    }

    protected abstract ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size);

    protected abstract ArrayList<Position> removeTroughPiecesIllegalMove(ArrayList<Position> positions, Piece piece, BoardConnectPieces board);

    protected abstract ArrayList<Position> removeCheckIllegalMove(ArrayList<Position> positions, Piece piece, BoardConnectPieces board);
}
