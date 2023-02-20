package Pieces.PossibleMoves;

import Board.BoardSize;
import Pieces.Piece;
import Pieces.Position;

public class PossibleMovesQueen extends PossibleMoves {
    @Override
    public void addPossibleMovesPerPiece(Piece piece, BoardSize size) {
        Position piecePos = piece.getPosition();
        char pX = piecePos.getX();
        int pY = piecePos.getY();

        for (char x = 'a'; x <= size.getX(); x++) {
            for (int y = 1; y <= size.getY(); y++) {
                if ((pX + pY == x + y) || (pX - pY == x - y)) {
                    positions.add(new Position(x, y));
                } else if (pX == x || pY == y) {
                    positions.add(new Position(x, y));
                }
            }
        }
        positions.remove(piecePos);
    }
}
