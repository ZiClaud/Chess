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

    public int getPosY() {
        return posY;
    }

    public void setPosX(char posX) {
        this.posX = posX;
        isPositionAvailable();
    }

    public void setPosY(int posY) {
        this.posY = posY;
        isPositionAvailable();
    }

    public abstract BufferedImage getImg();

    protected void isPositionAvailable() {
        if (posX < 'a' || posX > 'h' || posY < 1 || posY > 8) {
            System.err.println("Invalid value of posXY: " + ((char) posX) + posY);
            throw new UnsupportedOperationException();
        }
    }
}
