package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;
import java.util.HashSet;

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
    protected void removeCheckIllegalMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
    }
}
