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
}