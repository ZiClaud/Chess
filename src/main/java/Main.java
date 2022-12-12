import Board.*;

public class Main {
    public static void main(String[] args) {
        new Board(600, 600);

        MatrixBoard matrixBoard = new MatrixBoard();
        matrixBoard.startingMatrixBoard();
        matrixBoard.readMatrixBoard();
        matrixBoard.readMatrixBoard('a', 1);

    }
}