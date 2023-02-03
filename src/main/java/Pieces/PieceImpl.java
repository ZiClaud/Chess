package Pieces;

import Pieces.PossibleMoves.PossibleMovesTower;

public class PieceImpl extends PieceAbst {

    public PieceImpl(PieceType pieceType, PieceColor pieceColor, Position position) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        this.position = position;

        setPossibleMoves();
    }

    private void setPossibleMoves() {
        if (pieceType == PieceType.Pawn) {
            //this.possibleMoves = new PossibleMovesPawn();
        } else if (pieceType == PieceType.Tower) {
            this.possibleMoves = new PossibleMovesTower();
        } else if (true) {
            // TODO: Finish this
        }
        // TODO: Finish this
    }

    @Override
    public void move(Position position) {
        setPosition(position);
    }
}
