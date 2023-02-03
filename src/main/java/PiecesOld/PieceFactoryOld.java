package PiecesOld;

/*
public class PieceFactoryOld {
    public static PieceImpl newPiece(PieceColor color, PieceType type, char x, int y) {
        PieceImpl piece = null;
        if (color == PieceColor.WHITE) {
            piece = new WhitePieceOld();
        } else if (color == PieceColor.BLACK) {
            piece = new BlackPieceOld();
        } else {
            assert (false);
        }

        if (type == PieceType.Pawn) {
            piece = new PawnNotTooOld(piece, x, y);
        } else if (type == PieceType.Bishop) {
            piece = new BishopOld(piece, x, y);
        } else if (type == PieceType.Knight) {
            piece = new KnightOld(piece, x, y);
        } else if (type == PieceType.Tower) {
            piece = new TowerOld(piece, x, y);
        } else if (type == PieceType.Queen) {
            piece = new QueenOld(piece, x, y);
        } else if (type == PieceType.King) {
            piece = new KingOld(piece, x, y);
        } else {
            assert (false);
        }

        return piece;
    }
}

*/