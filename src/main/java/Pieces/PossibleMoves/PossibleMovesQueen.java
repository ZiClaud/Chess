package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;

public class PossibleMovesQueen extends PossibleMoves {
    @Override
    public ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size) {
        Position piecePos = piece.getPosition();
        ArrayList<Position> positions = new ArrayList<>();
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
