import Board.Board;
import Board.MatrixBoard;
import BoardPieces.BoardConnectPieces;
import BoardPieces.MatrixUtils;
import Pieces.BlackKing;
import Pieces.Piece;
import Pieces.PieceInt;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Board
        Board board = new Board(600, 600);

        // Pieces
        BlackKing king = new BlackKing('a', 1);
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(king);

        // Pieces in board
        BoardConnectPieces boardPieces = new BoardConnectPieces(board, pieces);

        // Matrix board - Board v2? idk what it is for, maybe testing, maybe I should work on this board, and what is displayed here will go in Board (aka graphfic)
        MatrixBoard matrixBoard = new MatrixBoard();
        matrixBoard.startingMatrixBoard();

        // Utils for tests
        MatrixUtils.readMatrixBoard(matrixBoard.getMatrix());
        MatrixUtils.readMatrixBoard(matrixBoard.getMatrix(), 'a', 1);

        MatrixUtils.readMatrixBoard(board.getMatrixPanels());
    }
}