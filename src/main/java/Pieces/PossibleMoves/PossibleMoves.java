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
        extraMoves(positions, piece, boardSize, piecesOnBoard);
        removeIllegalMoves(positions, piece, boardSize, piecesOnBoard);

        return positions;
    }

    protected void removeIllegalMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
        removeOutOfBoardIllegalMoves(positions, boardSize, pieces);
        removeTroughPieceIllegalMoves(positions, piece, boardSize, pieces);
        removeCheckIllegalMoves(positions, piece, boardSize, pieces);
        removeSamePieceAlreadyThereMoves(positions, piece, pieces);
    }

    private void removeSamePieceAlreadyThereMoves(ArrayList<Position> positions, Piece piece, HashSet<Piece> pieces) {
        Position position;
        for (int i = 0; i < positions.size(); i++) {
            position = positions.get(i);
            if (isSameColorPieceThere(piece, pieces, position)) {
                positions.remove(position);
                i = -1;
            }
        }
    }

    private void removeOutOfBoardIllegalMoves(ArrayList<Position> positions, BoardSize boardSize, HashSet<Piece> pieces) {
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
    }

    protected boolean isPieceThere(HashSet<Piece> pieces, Position position) {
        for (Piece boardPiece : pieces) {
            if (boardPiece.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    /*
        protected boolean isThisPositionThreatened(PieceColor pieceColor, HashSet<Piece> pieces, Position position) {
            Character x = position.getX();
            Integer y = position.getY();

            for (Piece enemyPiece : pieces) {
                if (enemyPiece.getPieceColor() != pieceColor) {

                }
                /*
                if (((Game.whitePlayer.isTurn() && enemyPiece.getPieceColor() == PieceColor.BLACK) ||
                        (Game.blackPlayer.isTurn() && enemyPiece.getPieceColor() == PieceColor.WHITE)) &&
                        PiecesRules.getPossibleMoves(enemyPiece, pieces).containsValue(xy)) {
                    if (enemyPiece.getPieceType() != PieceType.Pawn) {
                        System.out.println("mhm2 " + enemyPiece + " " + x + y);
                        return true;
                    } else if (PawnRules.canThisPawnThreaten(enemyPiece, x, y)) {
                        System.out.println("mhm " + enemyPiece + " " + x + y);
                        return true;
                    } else {
                        System.out.println("ok " + enemyPiece + " " + x + y);
                        // TODO ??
                    }
                }
                * /
    }
            return false;
                    }
        protected boolean isOpponentThreateningThere(Piece piece, HashSet<Piece> pieces, Position position) {
            PieceColor pieceColor = piece.getPieceColor();
            for (Piece boardPiece : pieces) {
                if (boardPiece.getPieceColor() != pieceColor) {
    //                if (boardPiece.getPossibleMoves().contains(position)) {
    //                    return true;
    //                }
                }
            }
            return false;
        }
    */
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
    protected void extraMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
    }

    protected abstract ArrayList<Position> getPossibleMovesPerPiece(Piece piece, BoardSize size);

    protected abstract void removeTroughPieceIllegalMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces);

    protected abstract void removeCheckIllegalMoves(ArrayList<Position> positions, Piece piece, BoardSize boardSize, HashSet<Piece> pieces);
}
