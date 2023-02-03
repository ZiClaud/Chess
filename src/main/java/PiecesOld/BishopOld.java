package PiecesOld;

/**
 * Piece: Bishop
 * Decorator

public class BishopOld extends PieceDecoratorOld {
    public BishopOld(PieceImpl piece, char posX, int posY) {
        this.piece = piece;
        isValidPosition();
    }

    @Override
    public void move(Position position) {
        setPosition(position);
        isValidPosition();
    }

    /*
    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> positions = new ArrayList<>();
        char pX = getPosition().getX();
        int pY = getPosition().getY();

        for (char x = 'a'; x < 'a' + 20; x++) {
            for (int y = 0; y < 20; y++) {
                if ((pX + pY == x + y) || (pX - pY == x - y)){
                    positions.add(new Position(x, y));
                }
            }
        }

        return positions;
    }
    //

    @Override
    public String getPieceName() {
        return piece.getPieceName() + ", Bishop";
    }
}
        */