package Pieces;

import java.awt.image.BufferedImage;

public abstract class Piece implements PieceInt {
    protected PieceType pieceType;
    protected PieceColor pieceColor;
    protected char posX;
    protected int posY;


    abstract BufferedImage getImg();
}
