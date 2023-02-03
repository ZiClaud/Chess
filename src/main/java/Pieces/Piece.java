package Pieces;

import Pieces.PossibleMoves.PossibleMoves;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface Piece {
    PossibleMoves getPossibleMoves();

    Position getPosition();

    boolean hasMoved();

    void move(Position position);

    boolean isTurn();

    PieceColor getPieceColor();

    PieceType getPieceType();

    BufferedImage getImg();
}
