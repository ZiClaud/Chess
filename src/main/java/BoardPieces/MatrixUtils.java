package BoardPieces;

import Pieces.PieceType;

/**
 * Utils for Matrix
 */
public class MatrixUtils {
    public static void readMatrixBoard(Object[][] board) {
        for (int y = 8; y >= 1; y--) {
            for (int x = 'a'; x <= 'h'; x++) {
                System.out.print("" + ((char) x) + y + ":");
                System.out.print(board[y - 1][x - 'a'].toString() + "\t");
            }
            System.out.println();
        }
    }

    public static void readMatrixBoard(Object[][] board, char x, int y) {
        System.out.print("" + x + y + ":");
        System.out.println(board[y - 1][x - 'a'].toString() + " ");
    }

    public static Object[][] createMatrix(Object[][] board, Object object) {
        for (int y = 8; y >= 1; y--) {
            for (int x = 'a'; x <= 'h'; x++) {
                board[y - 1][x - 'a'] = object;
            }
        }
        return board;
    }
}