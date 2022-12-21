package Pieces;

import java.awt.image.BufferedImage;

/**
 * Piece abstract class
 * Component
 */
public abstract class Piece {
    protected char posX;
    protected int posY;
    protected boolean isTurn;

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public char getPosX() {
        return posX;
    }

    public void setPosX(char posX) {
        this.posX = posX;
        isValidPosition();
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
        isValidPosition();
    }

    public BufferedImage getImg() {
        return PieceImg.getPieceImg(this);
    }

    protected void isValidPosition() {
        if (posX < 'a' || posX > 'h' || posY < 1 || posY > 8) {
            System.err.println("Invalid value of XY: " + posX + posY);
            throw new UnsupportedOperationException();
        }
    }

    public abstract String getPieceName();

    public PieceColor getPieceColor() {
        if (getPieceName().startsWith("White")) {
            return PieceColor.WHITE;
        } else if (getPieceName().startsWith("Black")) {
            return PieceColor.BLACK;
        } else {
            assert (false);
            return null;
        }
    }

    public PieceType getPieceType() {
        if (getPieceName().endsWith("Pawn")) {
            return PieceType.Pawn;
        } else if (getPieceName().endsWith("Bishop")) {
            return PieceType.Bishop;
        } else if (getPieceName().endsWith("Knight")) {
            return PieceType.Knight;
        } else if (getPieceName().endsWith("Tower")) {
            return PieceType.Tower;
        } else if (getPieceName().endsWith("Queen")) {
            return PieceType.Queen;
        } else if (getPieceName().endsWith("King")) {
            return PieceType.King;
        } else {
            assert (false);
            return null;
        }
    }
}
