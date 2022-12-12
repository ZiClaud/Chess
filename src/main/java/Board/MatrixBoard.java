package Board;

import Pieces.PieceType;

import java.util.Arrays;

public class MatrixBoard {
    private PieceType[][] matrix;

    public MatrixBoard() {
        createVoidMatrixBoard();
    }

    public void createVoidMatrixBoard() {
        this.matrix = new PieceType[8][8];
        for (int i = 0; i < 8; i++) {
            Arrays.fill(matrix[i], PieceType.Void);
        }
    }

    public PieceType[][] getMatrix() {
        return matrix;
    }

    public void startingMatrixBoard() {
        matrix[0][0] = PieceType.Tower;
        matrix[0][1] = PieceType.Knight;
        matrix[0][2] = PieceType.Bishop;
        matrix[0][3] = PieceType.Queen;
        matrix[0][4] = PieceType.King;
        matrix[0][5] = PieceType.Bishop;
        matrix[0][6] = PieceType.Knight;
        matrix[0][7] = PieceType.Tower;

        matrix[7][0] = PieceType.Tower;
        matrix[7][1] = PieceType.Knight;
        matrix[7][2] = PieceType.Bishop;
        matrix[7][3] = PieceType.Queen;
        matrix[7][4] = PieceType.King;
        matrix[7][5] = PieceType.Bishop;
        matrix[7][6] = PieceType.Knight;
        matrix[7][7] = PieceType.Tower;
    }

    public void readMatrixBoard() {
        for (int y = 8; y >= 1; y--) {
            for (int x = 'a'; x <= 'h'; x++) {
                System.out.print("" + ((char) x) + y + ":");
                System.out.print(matrix[y - 1][x - 'a'].getPieceName() + "\t");
            }
            System.out.println();
        }
    }

    public void readMatrixBoard(char x, int y) {
        System.out.print("" + ((char) x) + y + ":");
        System.out.println(matrix[y - 1][x - 'a'].getPieceName() + " ");
    }

}