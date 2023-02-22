package Pieces;

import Board.BoardSize;
import Pieces.PossibleMoves.PossibleMoves;

import java.awt.image.BufferedImage;
import java.util.HashSet;

public interface Piece {
    PossibleMoves getPossibleMoves();

    Position getPosition();

    boolean hasMoved();

    void move(Position position, HashSet<Piece> pieces);

    boolean isTurn();

    PieceColor getPieceColor();

    PieceType getPieceType();

    BufferedImage getImg();
}
