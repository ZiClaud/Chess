package Pieces.PossibleMoves;

import Board.BoardSize;
import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.Position;
import Rules.ComplexRules;
import Rules.ThreatRules;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class PossibleMoves {
    ArrayList<Position> positions = new ArrayList<>();

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void updatePossibleMoves() {
        BoardConnectPieces board = BoardConnectPieces.getInstance();
        HashSet<Piece> pieces = board.getPieces();

        for (Piece piece : pieces) {
            setPossibleMovesOnBoard(piece, pieces);
        }
    }

    public void updatePossibleMoves(HashSet<Piece> pieces) {
        for (Piece piece : pieces) {
            setPossibleMovesOnBoard(piece, pieces);
        }
    }

    public void setPossibleMovesOnBoard(Piece piece, HashSet<Piece> pieces) {
        BoardConnectPieces board = BoardConnectPieces.getInstance();
        BoardSize boardSize = board.getWindowBoard().getBoardSize();
        positions = new ArrayList<>();

        addPossibleMovesPerPiece(piece, boardSize);
        extraMoves(piece, boardSize, pieces);
        removeIllegalMoves(piece, boardSize, pieces);
    }

    protected void removeIllegalMoves(Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
        removeOutOfBoardIllegalMoves(boardSize, pieces);
        removeThroughPieceIllegalMoves(piece, boardSize, pieces);
        removeSamePieceAlreadyThereMoves(piece, pieces);
        removeCheckIllegalMoves(piece, pieces);
    }

    private void removeSamePieceAlreadyThereMoves(Piece piece, HashSet<Piece> pieces) {
        Position position;
        int i = 0;
        while (i < positions.size()) {
            position = positions.get(i);
            if (isSameColorPieceThere(piece, pieces, position)) {
                positions.remove(position);
                i = 0;
                continue;
            }
            i++;
        }
    }

    private void removeOutOfBoardIllegalMoves(BoardSize boardSize, HashSet<Piece> pieces) {
        int i = 0;

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
        for (Piece pieceOnBoard : pieces) {
            if (pieceOnBoard.getPosition().equals(position) &&
                    pieceOnBoard.getPieceColor() == piece.getPieceColor()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Hook
     */
    protected void extraMoves(Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
    }

    protected abstract void addPossibleMovesPerPiece(Piece piece, BoardSize size);

    protected void removeThroughPieceIllegalMoves(Piece piece, BoardSize size, HashSet<Piece> pieces) {
        for (int i = 0; i < positions.size(); i++) {
            Position position = positions.get(i);
            if (ComplexRules.isGoingThroughPieceToGetThere(piece, pieces, position)) {
                positions.remove(position);
                i = -1;
            }
        }
    }

    protected void removeCheckIllegalMoves(Piece piece, HashSet<Piece> pieces) {
        int i = 0;
        while (i < positions.size()) {
            Position position = positions.get(i);
            if (ThreatRules.willThisMoveCauseCheck(piece, pieces, position)) {
                positions.remove(position);
                i = 0;
                continue;
            }
            i++;
        }
    }
}
