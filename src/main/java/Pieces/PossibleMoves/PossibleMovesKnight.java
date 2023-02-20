package Pieces.PossibleMoves;

import Board.BoardSize;
import Pieces.Piece;
import Pieces.Position;

import java.util.HashSet;

public class PossibleMovesKnight extends PossibleMoves {
    @Override
    public void addPossibleMovesPerPiece(Piece piece, BoardSize size) {
        char pX = piece.getPosition().getX();
        int pY = piece.getPosition().getY();

        positions.add(new Position((char) (pX + 2), pY + 1));
        positions.add(new Position((char) (pX + 1), pY + 2));
        positions.add(new Position((char) (pX - 2), pY - 1));
        positions.add(new Position((char) (pX - 1), pY - 2));
        positions.add(new Position((char) (pX + 2), pY - 1));
        positions.add(new Position((char) (pX - 1), pY + 2));
        positions.add(new Position((char) (pX - 2), pY + 1));
        positions.add(new Position((char) (pX + 1), pY - 2));
    }
}
