package Pieces.PossibleMoves;

import Board.BoardSize;
import Pieces.Piece;
import Pieces.Position;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * This is abstract because it can be both white and black pawn
 */
public abstract class PossibleMovesPawn extends PossibleMoves {
    protected boolean allowsElPassant = false;

    protected void addPawnTakesMoves(Piece piece, Position posTakeLeft, Position posTakeRight, BoardSize boardSize, HashSet<Piece> pieces) {
        for (Piece enemyPiece : pieces) {
            if (enemyPiece.getPieceColor() != piece.getPieceColor()) {
                if (enemyPiece.getPosition().equals(posTakeLeft)) { // TODO: if is pawn and allowsElPassant then..
                    positions.add(enemyPiece.getPosition());
                }
                if (enemyPiece.getPosition().equals(posTakeRight)) { // TODO: if is pawn and allowsElPassant then..
                    positions.add(enemyPiece.getPosition());
                }
            }
        }
    }

    protected void removePawnForwardMovement(Position removePos1, Position removePos2, BoardSize boardSize, HashSet<Piece> pieces){
        for (Piece boardPiece : pieces) {
            if (boardPiece.getPosition().equals(removePos1)) {
                positions.remove(removePos1);
                positions.remove(removePos2);
                break;
            }
            if (boardPiece.getPosition().equals(removePos2)) {
                positions.remove(removePos2);
            }
        }
    }
}