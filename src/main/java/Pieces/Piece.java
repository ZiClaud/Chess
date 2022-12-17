package Pieces;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Piece abstract class
 */
public abstract class Piece implements PieceInt {
    protected PieceType pieceType;
    protected PieceColor pieceColor;
    protected char posX;
    protected int posY;

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getPieceColor() {
        return (pieceColor == PieceColor.WHITE) ? Color.WHITE : Color.BLACK;
    }

    public char getPosX() {
        return posX;
    }

    public void setPosX(char posX) {
        this.posX = posX;
        isPositionAvailable();
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
        isPositionAvailable();
    }

    public BufferedImage getImg() {
        return PieceImg.getPieceImg(this);
    }

    protected void isPositionAvailable() {
        if (posX < 'a' || posX > 'h' || posY < 1 || posY > 8) {
            System.err.println("Invalid value of XY: " + posX + posY);
            throw new UnsupportedOperationException();
        }
    }
}
