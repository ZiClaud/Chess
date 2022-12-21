package Pieces;


/**
 * Piece: Black King
 */
public class Tower extends PieceDecorator {
    private boolean allowCastle = true;
    public Tower(Piece piece, char posX, int posY) {
        this.piece = piece;
        this.posX = posX;
        this.posY = posY;
        isValidPosition();
    }

    @Override
    public String getPieceName() {
        return piece.getPieceName() + ", Tower";
    }

    public boolean isAllowCastle() {
        return allowCastle;
    }

    public void setAllowCastle(boolean allowCastle) {
        this.allowCastle = allowCastle;
    }
}
