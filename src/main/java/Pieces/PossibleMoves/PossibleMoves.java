package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class PossibleMoves {
    public ArrayList<Position> getPossibleMovesOnBoard(Piece piece, BoardConnectPieces board) {
        BoardSize boardSize = board.getWindowBoard().getBoardSize();
        HashSet<Piece> piecesOnBoard = board.getPieces(); //TODO: Use only ArrayList, not HashSet

        ArrayList<Position> positions = getPossibleMovesPerPiece(piece, boardSize);
        positions = extraMoves(positions, piece, boardSize, piecesOnBoard);
        positions = removeIllegalMoves(positions, piece, boardSize, piecesOnBoard);

        return positions;
    }

    protected ArrayList<Position> removeIllegalMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
        positions = removeOutOfBoardIllegalMoves(positions, boardSize, pieces);
        positions = removeTroughPieceIllegalMoves(positions, piece, boardSize, pieces);
        positions = removeCheckIllegalMoves(positions, piece, boardSize, pieces);
        return positions;
    }

    private ArrayList<Position> removeOutOfBoardIllegalMoves(ArrayList<Position> positions, BoardSize boardSize, HashSet<Piece> pieces) {
        int i = 0;
//        System.out.println(positions);
//        System.out.println("Removing...");
        while (i < positions.size()) {
            Position position = positions.get(i);

            if (position.getX() >= 'a' && position.getX() <= boardSize.getX() &&
                    position.getY() >= 1 && position.getY() <= boardSize.getY()) {
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

    protected boolean isPieceThere(HashSet<Piece> pieces, Position position) {
        for (Piece boardPiece : pieces) {
            if (boardPiece.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameColorPieceThere(Piece piece, HashSet<Piece> pieces, Position position) {
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
    protected ArrayList<Position> extraMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
        return positions;
    }

    protected abstract ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size);

    protected abstract ArrayList<Position> removeTroughPieceIllegalMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces);

    protected abstract ArrayList<Position> removeCheckIllegalMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces);
}
