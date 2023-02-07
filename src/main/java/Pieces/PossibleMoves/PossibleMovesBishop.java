package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;
import java.util.HashSet;

public class PossibleMovesBishop extends PossibleMoves {
    @Override
    public void addPossibleMovesPerPiece(Piece piece, BoardSize size) {
        Position piecePos = piece.getPosition();
        char pX = piecePos.getX();
        int pY = piecePos.getY();

        for (char x = 'a'; x <= size.getX(); x++) {
            for (int y = 1; y <= size.getY(); y++) {
                if ((pX + pY == x + y) || (pX - pY == x - y)) {
                    positions.add(new Position(x, y));
                }
            }
        }
        positions.remove(piecePos);
    }

    @Override
    protected void removeCheckIllegalMoves(Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
    }
}
