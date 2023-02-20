import Pieces.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class PieceTest {
    @Test
    public void PieceImg() {
        Position position = new Position('a', 1);
        assert (PieceImg.getPieceImg(PieceFactory.newPiece(PieceType.Bishop, PieceColor.BLACK, position)) != null);
        assert (PieceImg.getPieceImg(PieceFactory.newPiece(PieceType.Bishop, PieceColor.WHITE, position)) != null);
        assert (PieceImg.getPieceImg(PieceFactory.newPiece(PieceType.King, PieceColor.BLACK, position)) != null);
        assert (PieceImg.getPieceImg(PieceFactory.newPiece(PieceType.King, PieceColor.WHITE, position)) != null);
        assert (PieceImg.getPieceImg(PieceFactory.newPiece(PieceType.Knight, PieceColor.BLACK, position)) != null);
        assert (PieceImg.getPieceImg(PieceFactory.newPiece(PieceType.Knight, PieceColor.WHITE, position)) != null);
        assert (PieceImg.getPieceImg(PieceFactory.newPiece(PieceType.Queen, PieceColor.BLACK, position)) != null);
        assert (PieceImg.getPieceImg(PieceFactory.newPiece(PieceType.Queen, PieceColor.WHITE, position)) != null);
        assert (PieceImg.getPieceImg(PieceFactory.newPiece(PieceType.Pawn, PieceColor.BLACK, position)) != null);
        assert (PieceImg.getPieceImg(PieceFactory.newPiece(PieceType.Pawn, PieceColor.WHITE, position)) != null);
        assert (PieceImg.getPieceImg(PieceFactory.newPiece(PieceType.Tower, PieceColor.BLACK, position)) != null);
        assert (PieceImg.getPieceImg(PieceFactory.newPiece(PieceType.Tower, PieceColor.WHITE, position)) != null);
    }

    @Test
    public void isGoingThroughPieceToGetThere() {
        Piece tower = PieceFactory.newPiece(PieceType.Tower, PieceColor.WHITE, new Position('a', 3));
        Piece pawn = PieceFactory.newPiece(PieceType.Pawn, PieceColor.BLACK, new Position('a', 5));
        Piece pawn2 = PieceFactory.newPiece(PieceType.Pawn, PieceColor.WHITE, new Position('a', 2));

        HashSet<Piece> pieces = new HashSet<>(Set.of(tower, pawn, pawn2));

        Position a4 = null;
        Position a6 = null;
        Position a5 = null;
        Position a1 = null;


        for (Position p : tower.getPossibleMoves().getPositions()) {
            if (p.getX() == 'a') {
                if (p.getY() == 1) {
                    a1 = p;
                } else if (p.getY() == 4) {
                    a4 = p;
                } else if (p.getY() == 5) {
                    a5 = p;
                } else if (p.getY() == 6) {
                    a6 = p;
                }
            }
        }

        assert (tower.getPossibleMoves().getPositions().contains(a4));
        assertFalse(tower.getPossibleMoves().getPositions().contains(a6));
        assert (tower.getPossibleMoves().getPositions().contains(a5));
        assertFalse(tower.getPossibleMoves().getPositions().contains(a1));
    }
}