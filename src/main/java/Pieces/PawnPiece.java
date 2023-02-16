package Pieces;

import java.util.ArrayList;

public class PawnPiece extends PieceImpl {
    public PawnPiece(PieceType pieceType, PieceColor pieceColor, Position position) {
        super(pieceType, pieceColor, position);
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
}
