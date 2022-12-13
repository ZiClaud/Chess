package Board;

import Pieces.PieceType;

import java.util.Arrays;

/**
 * Second version of Board - Text version, not UI one
 */
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

    // TODO -> Put piece color too
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

        matrix[1][0] = PieceType.Pawn;
        matrix[1][1] = PieceType.Pawn;
        matrix[1][2] = PieceType.Pawn;
        matrix[1][3] = PieceType.Pawn;
        matrix[1][4] = PieceType.Pawn;
        matrix[1][5] = PieceType.Pawn;
        matrix[1][6] = PieceType.Pawn;
        matrix[1][7] = PieceType.Pawn;

        matrix[6][0] = PieceType.Pawn;
        matrix[6][1] = PieceType.Pawn;
        matrix[6][2] = PieceType.Pawn;
        matrix[6][3] = PieceType.Pawn;
        matrix[6][4] = PieceType.Pawn;
        matrix[6][5] = PieceType.Pawn;
        matrix[6][6] = PieceType.Pawn;
        matrix[6][7] = PieceType.Pawn;
    }
}