package Pieces;

import Game.Game;
import Pieces.PossibleMoves.PossibleMoves;

import java.awt.image.BufferedImage;

/**
 * Piece abstract class
 * Component
 */
public abstract class PieceAbst implements Piece {
    protected PieceColor pieceColor;
    protected PieceType pieceType;
    protected Position position;
    protected PossibleMoves possibleMoves;
    protected boolean moved = false;

    @Override
    public boolean hasMoved() {
        return moved;
    }

    @Override
    public boolean isTurn() {
        if (pieceColor == PieceColor.WHITE) {
            return Game.whitePlayer.isTurn();
        } else if (pieceColor == PieceColor.BLACK) {
            return Game.blackPlayer.isTurn();
        }
        assert false;
        return false;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    protected void setPosition(Position position) {
        this.position = position;
//        isValidPosition();
    }

    @Override
    public PossibleMoves getPossibleMoves() {
        return possibleMoves;
    }

    @Override
    public BufferedImage getImg() {
        return PieceImg.getPieceImg(this);
    }

    // TODO: Change using BoardSize, maybe move it
    protected void isValidPosition() {
        if (position.getX() < 'a' || position.getX() > 'h' || position.getY() < 1 || position.getY() > 8) {
            System.err.println("Invalid value of XY: " + position.getX() + position.getY());
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public PieceColor getPieceColor() {
        return pieceColor;
    }

    @Override
    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public String toString() {
        return "PieceAbst{" +
                "pieceColor=" + pieceColor +
                ", pieceType=" + pieceType +
                ", position=" + position +
                ", possibleMoves=" + possibleMoves +
                ", moved=" + moved +
                '}';
    }
}
