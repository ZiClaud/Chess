package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;

public class PossibleMovesKnight extends PossibleMoves {
    @Override
    public ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size) {
        char pX = piece.getPosition().getX();
        int pY = piece.getPosition().getY();

        ArrayList<Position> positions = new ArrayList<>();

        positions.add(new Position((char) (pX + 2), pY + 1));
        positions.add(new Position((char) (pX + 1), pY + 2));
        positions.add(new Position((char) (pX - 2), pY - 1));
        positions.add(new Position((char) (pX - 1), pY - 2));
        positions.add(new Position((char) (pX + 2), pY - 1));
        positions.add(new Position((char) (pX - 1), pY + 2));
        positions.add(new Position((char) (pX - 2), pY + 1));
        positions.add(new Position((char) (pX + 1), pY - 2));

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
