import Pieces.*;
import Rules.ComplexRules;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class PieceTest {
    @Test
    public void PieceImg() {
        assert (PieceImg.getPieceImg(new Bishop(new BlackPiece(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new Bishop(new WhitePiece(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new King(new BlackPiece(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new King(new WhitePiece(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new Knight(new BlackPiece(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new Knight(new WhitePiece(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new Queen(new BlackPiece(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new Queen(new WhitePiece(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new Pawn(new BlackPiece(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new Pawn(new WhitePiece(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new Tower(new BlackPiece(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new Tower(new WhitePiece(), 'a', 1)) != null);
    }

    @Test
    public void isGoingThroughPieceToGetThere() {
        Piece tower = new Tower(new WhitePiece(), 'a', 3);
        Piece pawn = new Pawn(new BlackPiece(), 'a', 5);
        Piece pawn2 = new Pawn(new BlackPiece(), 'a', 2);

        HashSet<Piece> pieces = new HashSet<>(Set.of(tower, pawn, pawn2));

        assert (ComplexRules.isThisALegalMove(tower, pieces, 'a', 4));
        assert (!ComplexRules.isThisALegalMove(tower, pieces, 'a', 6));
        assert (ComplexRules.isThisALegalMove(tower, pieces, 'a', 5));
        assert (!ComplexRules.isThisALegalMove(tower, pieces, 'a', 1));
    }
}
