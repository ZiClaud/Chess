package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;

public abstract class PossibleMoves {
    public ArrayList<Position> getPossibleMovesOnBoard(Piece piece, BoardConnectPieces board) {
        ArrayList<Position> positions = getPossibleMovesPerPiece(piece, board.getWindowBoard().getBoardSize());
        positions = extraMoves(positions, piece, board);
        positions = removeIllegalMoves(positions, piece, board);

        return positions;
    }

    protected ArrayList<Position> removeIllegalMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
        positions = removeOutOfBoardIllegalMoves(positions, board);
        positions = removeTroughPieceIllegalMoves(positions, piece, board);
        positions = removeCheckIllegalMoves(positions, piece, board);
        return positions;
    }

    private ArrayList<Position> removeOutOfBoardIllegalMoves(ArrayList<Position> positions, BoardConnectPieces board) {
        int i = 0;
//        System.out.println(positions);
//        System.out.println("Removing...");
        while (i < positions.size()) {
            Position position = positions.get(i);

            if (position.getX() >= 'a' && position.getX() <= board.getWindowBoard().getBoardSize().getX() &&
                    position.getY() >= 1 && position.getY() <= board.getWindowBoard().getBoardSize().getY()) {
                i++;
                continue;
            }
            positions.remove(position);
            i = 0;
        }
//        System.out.println("...Removed");
//        System.out.println(positions);
        return positions;
    }

    protected boolean isPieceThere(ArrayList<Piece> pieces, Position position) {
        for (Piece boardPiece : pieces) {
            if (boardPiece.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameColorPieceThere(Piece piece, ArrayList<Piece> pieces, Position position) {
        for (Piece boardPiece : pieces) {
            if (boardPiece.getPosition().equals(position)) {
                if (boardPiece.getPieceColor() == piece.getPieceColor()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Hook
     */
    protected ArrayList<Position> extraMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board) {
        return positions;
    }

    protected abstract ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size);

    protected abstract ArrayList<Position> removeTroughPieceIllegalMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board);

    protected abstract ArrayList<Position> removeCheckIllegalMoves(ArrayList<Position> positions, Piece piece, BoardConnectPieces board);
}
