package Pieces;

import java.util.ArrayList;
import java.util.HashSet;

public class PawnPiece extends PieceImpl {
    private boolean allowsElPassant = false;

    public PawnPiece(PieceType pieceType, PieceColor pieceColor, Position position) {
        super(pieceType, pieceColor, position);
    }

    public boolean allowsElPassant() {
        return allowsElPassant;
    }

    public void setAllowsElPassant(boolean allowsElPassant) {
        this.allowsElPassant = allowsElPassant;
    }

    public ArrayList<Position> getThreatPosition() {
        ArrayList<Position> positions = new ArrayList<>();

        if (pieceColor == PieceColor.WHITE) {
            getThreatPositionWhite(positions);
        } else if (pieceColor == PieceColor.BLACK) {
            getThreatPositionBlack(positions);
        } else {
            assert false;
        }

        return positions;
    }

    private ArrayList<Position> getThreatPositionWhite(ArrayList<Position> positions) {
        positions.add(new Position((char) (position.x - 1), position.y + 1));
        positions.add(new Position((char) (position.x + 1), position.y + 1));
        return positions;
    }

    private ArrayList<Position> getThreatPositionBlack(ArrayList<Position> positions) {
        positions.add(new Position((char) (position.x - 1), position.y - 1));
        positions.add(new Position((char) (position.x + 1), position.y - 1));
        return positions;
    }

    @Override
    public void move(Position position, HashSet<Piece> pieces) {
        Position previousPosition = this.getPosition();
        super.move(position, pieces);
        if ((previousPosition.getY() == (position.getY() + 2)) ||
                (previousPosition.getY() == (position.getY() - 2))) {
            System.out.println("This move allows El Passant");
            allowsElPassant = true;
        } else {
            System.out.println("This move does not allow El Passant");
        }
    }
}
