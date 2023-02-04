package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;

public class PossibleMovesTower extends PossibleMoves {
    @Override
    public ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size) {
        Position piecePos = piece.getPosition();
        ArrayList<Position> positions = new ArrayList<>();
        char pX = piecePos.getX();
        int pY = piecePos.getY();

        for (char x = 'a'; x <= size.getX(); x++) {
            for (int y = 1; y <= size.getY(); y++) {
                if (pX == x || pY == y) {
                    positions.add(new Position(x, y));
                }
            }
        }
        positions.remove(piecePos);
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
