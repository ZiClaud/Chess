import Pieces.PieceImg;
import org.junit.jupiter.api.Test;

public class PieceImgTest {
    @Test
    public void PieceImg() {
        assert (PieceImg.getBlackBishop() != null);
        assert (PieceImg.getWhiteBishop() != null);
        assert (PieceImg.getBlackKing() != null);
        assert (PieceImg.getWhiteKing() != null);
        assert (PieceImg.getBlackKnight() != null);
        assert (PieceImg.getWhiteKnight() != null);
        assert (PieceImg.getBlackQueen() != null);
        assert (PieceImg.getWhiteQueen() != null);
        assert (PieceImg.getBlackPawn() != null);
        assert (PieceImg.getWhitePawn() != null);
    }
}
