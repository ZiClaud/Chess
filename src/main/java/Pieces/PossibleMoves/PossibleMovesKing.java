package Pieces.PossibleMoves;

import Board.BoardSize;
import Pieces.Piece;
import Pieces.PieceType;
import Pieces.Position;

import java.util.HashSet;

public class PossibleMovesKing extends PossibleMoves {
    @Override
    public void addPossibleMovesPerPiece(Piece piece, BoardSize size) {
        Position piecePos = piece.getPosition();
        char pX = piecePos.getX();
        int pY = piecePos.getY();

        positions.add(new Position(pX, pY + 1));
        positions.add(new Position((char) (pX + 1), pY));
        positions.add(new Position((char) (pX + 1), pY + 1));
        positions.add(new Position((char) (pX + 1), pY - 1));

        positions.add(new Position(pX, pY - 1));
        positions.add(new Position((char) (pX - 1), pY));
        positions.add(new Position((char) (pX - 1), pY - 1));
        positions.add(new Position((char) (pX - 1), pY + 1));
    }

    /**
     * Castle
     */
    @Override
    protected void extraMoves(Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
        if (!piece.hasMoved()) {
            HashSet<Piece> rooks = getCastlingRooks(piece, pieces);
            for (Piece rook : rooks) {
                if (rook.getPosition().getX() > piece.getPosition().getX()) {
                    positions.add(new Position((char) (piece.getPosition().getX() + 2), piece.getPosition().getY()));
                }
                if (rook.getPosition().getX() < piece.getPosition().getX()) {
                    positions.add(new Position((char) (piece.getPosition().getX() - 2), piece.getPosition().getY()));
                }
            }
        }
    }

    /**
     * returns rooks that are the same color of piece (aka King), that haven't moved
     */
    private HashSet<Piece> getCastlingRooks(Piece piece, HashSet<Piece> pieces) {
        HashSet<Piece> rooks = new HashSet<>();

        for (Piece rook : pieces) {
            if (rook.getPieceColor() == piece.getPieceColor() &&
                    rook.getPieceType() == PieceType.Tower &&
                    !rook.hasMoved()) {
                rooks.add(rook);
            }
        }
        return rooks;
    }

    @Override
    protected void removeCheckIllegalMoves(Piece piece, BoardSize boardSize, HashSet<Piece> pieces) {
    }
}
