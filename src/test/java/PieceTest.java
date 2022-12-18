import Pieces.*;
import Rules.ComplexRules;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class PieceTest {
    @Test
    public void PieceImg() {
        assert (PieceImg.getPieceImg(new BlackBishop('a', 1)) != null);
        assert (PieceImg.getPieceImg(new WhiteBishop('a', 1)) != null);
        assert (PieceImg.getPieceImg(new BlackKing('a', 1)) != null);
        assert (PieceImg.getPieceImg(new WhiteKing('a', 1)) != null);
        assert (PieceImg.getPieceImg(new BlackKnight('a', 1)) != null);
        assert (PieceImg.getPieceImg(new WhiteKnight('a', 1)) != null);
        assert (PieceImg.getPieceImg(new BlackQueen('a', 1)) != null);
        assert (PieceImg.getPieceImg(new WhiteQueen('a', 1)) != null);
        assert (PieceImg.getPieceImg(new BlackPawn('a', 1)) != null);
        assert (PieceImg.getPieceImg(new WhitePawn('a', 1)) != null);
        assert (PieceImg.getPieceImg(new BlackTower('a', 1)) != null);
        assert (PieceImg.getPieceImg(new WhiteTower('a', 1)) != null);
    }

    @Test
    public void isGoingThroughPieceToGetThere() {
        Piece tower = new WhiteTower('a', 3);
        Piece pawn = new BlackPawn('a', 5);
        Piece pawn2 = new BlackPawn('a', 2);

        HashSet<Piece> pieces = new HashSet<>(Set.of(tower, pawn, pawn2));

        assert (ComplexRules.isThisALegalMove(tower, pieces, 'a', 4));
        assert (!ComplexRules.isThisALegalMove(tower, pieces, 'a', 6));
        assert (ComplexRules.isThisALegalMove(tower, pieces, 'a', 5));
        assert (!ComplexRules.isThisALegalMove(tower, pieces, 'a', 1));
    }
}
