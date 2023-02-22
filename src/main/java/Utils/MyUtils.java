package Utils;

import Pieces.Piece;
import Pieces.PieceType;

import java.util.HashSet;

public class MyUtils {
    /**
     * returns rooks that are the same color of piece (aka King), that haven't moved
     */
    public static HashSet<Piece> getCastlingRooks(Piece piece, HashSet<Piece> pieces) {
        HashSet<Piece> rooks = new HashSet<>();

        for (Piece rook : pieces) {
            if (rook.getPieceColor() == piece.getPieceColor() &&
                    rook.getPieceType() == PieceType.Tower &&
                    !rook.hasMoved()) {
                rooks.add(rook);
            }
        }
        return rooks;
    }
}
