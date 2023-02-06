package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;
import java.util.HashSet;

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
    protected void removeTroughPieceIllegalMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
    }

    @Override
    protected void removeCheckIllegalMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
    }
}
