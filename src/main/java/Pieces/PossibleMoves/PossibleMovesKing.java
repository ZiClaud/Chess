package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;

public class PossibleMovesKing extends PossibleMoves {
    @Override
    public ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size) {
        Position piecePos = piece.getPosition();
        ArrayList<Position> positions = new ArrayList<>();
        char pX = piecePos.getX();
        int pY = piecePos.getY();

        positions.add(new Position(pX, pY + 1));
        positions.add(new Position((char) (pX + 1), pY));
        positions.add(new Position((char) (pX + 1), pY + 1));
        positions.add(new Position((char) (pX + 1), pY - 1));

        positions.add(new Position(pX, pY - 1));
        positions.add(new Position((char) (pX - 1), pY));
        positions.add(new Position((char) (pX - 1), pY - 1));
        positions.add(new Position((char) (pX - 1), pY + 1));

        return positions;
    }

    /**
     * Castle
     */
    @Override
    protected ArrayList<Position> extraMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
        return positions;
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
