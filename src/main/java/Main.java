import Board.*;
import BoardPieces.BoardConnectPieces;
import Pieces.BlackKing;
import Pieces.Piece;
import Pieces.PieceInt;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(600, 600);

        BlackKing king = new BlackKing('a', 1);
        ArrayList<PieceInt> pieces = new ArrayList<>();
        pieces.add(king);
        BoardConnectPieces boardPieces = new BoardConnectPieces(board, pieces);

        MatrixBoard matrixBoard = new MatrixBoard();
        matrixBoard.startingMatrixBoard();
        BoardUtils.readMatrixBoard(matrixBoard.getMatrix());
        BoardUtils.readMatrixBoard(matrixBoard.getMatrix(), 'a', 1);

        BoardUtils.readMatrixBoard(board.getMatrixPanels());
    }
}