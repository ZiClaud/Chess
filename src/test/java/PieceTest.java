/*
public class PieceTest {
    @Test
    public void PieceImg() {
        assert (PieceImg.getPieceImg(new BishopOld(new BlackPieceOld(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new BishopOld(new WhitePieceOld(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new KingOld(new BlackPieceOld(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new KingOld(new WhitePieceOld(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new KnightOld(new BlackPieceOld(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new KnightOld(new WhitePieceOld(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new QueenOld(new BlackPieceOld(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new QueenOld(new WhitePieceOld(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new PawnNotTooOld(new BlackPieceOld(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new PawnNotTooOld(new WhitePieceOld(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new TowerOld(new BlackPieceOld(), 'a', 1)) != null);
        assert (PieceImg.getPieceImg(new TowerOld(new WhitePieceOld(), 'a', 1)) != null);
    }

    @Test
    public void isGoingThroughPieceToGetThere() {
        PieceAbst tower = new TowerOld(new WhitePieceOld(), 'a', 3);
        PieceAbst pawn = new PawnNotTooOld(new BlackPieceOld(), 'a', 5);
        PieceAbst pawn2 = new PawnNotTooOld(new WhitePieceOld(), 'a', 2);

        HashSet<PieceAbst> pieces = new HashSet<>(Set.of(tower, pawn, pawn2));

        assert (ComplexRules.isThisALegalMove(tower, pieces, 'a', 4));
        assertFalse (ComplexRules.isThisALegalMove(tower, pieces, 'a', 6));
        assert (ComplexRules.isThisALegalMove(tower, pieces, 'a', 5));
        assertFalse (ComplexRules.isThisALegalMove(tower, pieces, 'a', 1));
    }
}
*/