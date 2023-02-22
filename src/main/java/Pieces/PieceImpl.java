package Pieces;

import Board.BoardSize;
import Board.WindowBoard;
import Pieces.PossibleMoves.*;

import java.util.HashSet;

public class PieceImpl extends PieceAbst {
    public PieceImpl(PieceType pieceType, PieceColor pieceColor, Position position) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        this.position = position;

        setPossibleMoves();
    }

    private void setPossibleMoves() {
        if (pieceType == PieceType.Pawn) {
            if (pieceColor == PieceColor.WHITE) {
                this.possibleMoves = new PossibleMovesWhitePawn();
            } else if (pieceColor == PieceColor.BLACK) {
                this.possibleMoves = new PossibleMovesBlackPawn();
            } else {
                assert false;
            }
        } else if (pieceType == PieceType.Tower) {
            this.possibleMoves = new PossibleMovesTower();
        } else if (pieceType == PieceType.Knight) {
            this.possibleMoves = new PossibleMovesKnight();
        } else if (pieceType == PieceType.Bishop) {
            this.possibleMoves = new PossibleMovesBishop();
        } else if (pieceType == PieceType.Queen) {
            this.possibleMoves = new PossibleMovesQueen();
        } else if (pieceType == PieceType.King) {
            this.possibleMoves = new PossibleMovesKing();
        } else {
            assert false;
        }
    }

    @Override
    public void move(Position position, HashSet<Piece> pieces) {
        isValidPosition(WindowBoard.getInstance().getBoardSize());
        this.moved = true;
        take(position, pieces);
        setPosition(position);
    }

    private void take(Position position, HashSet<Piece> pieces) {
        for (Piece piece : pieces) {
            if (piece.getPosition().equals(position)) {
                pieces.remove(piece);
                break;
            }
        }
    }
}
