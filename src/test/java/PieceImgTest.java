import Pieces.*;
import org.junit.jupiter.api.Test;

public class PieceImgTest {
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
}
