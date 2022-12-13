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

    public abstract BufferedImage getImg();
}
