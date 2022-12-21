package Pieces;

public class PieceFactory {
    public static Piece newPiece(PieceColor color, PieceType type, char x, int y) {
        Piece piece = null;
        if (color == PieceColor.WHITE) {
            piece = new WhitePiece();
        } else if (color == PieceColor.BLACK) {
            piece = new BlackPiece();
        } else {
            assert (false);
        }

        if (type == PieceType.Pawn) {
            piece = new Pawn(piece, x, y);
        } else if (type == PieceType.Bishop) {
            piece = new Bishop(piece, x, y);
        } else if (type == PieceType.Knight) {
            piece = new Knight(piece, x, y);
        } else if (type == PieceType.Tower) {
            piece = new Tower(piece, x, y);
        } else if (type == PieceType.Queen) {
            piece = new Queen(piece, x, y);
        } else if (type == PieceType.King) {
            piece = new King(piece, x, y);
        } else {
            assert (false);
        }

        return piece;
    }
}
