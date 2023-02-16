package Pieces.PossibleMoves;

import Board.BoardSize;
import Pieces.Piece;
import Pieces.PieceType;
import Pieces.Position;
import Rules.ThreatRules;

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
    protected void extraMoves(Piece king, BoardSize boardSize, HashSet<Piece> pieces) {
        if (!king.hasMoved()) {
            HashSet<Piece> rooks = getCastlingRooks(king, pieces);
            for (Piece rook : rooks) {
                if (rook.getPosition().getX() > king.getPosition().getX()) {
                    positions.add(new Position((char) (king.getPosition().getX() + 2), king.getPosition().getY()));
                }
                if (rook.getPosition().getX() < king.getPosition().getX()) {
                    positions.add(new Position((char) (king.getPosition().getX() - 2), king.getPosition().getY()));
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
    protected void removeCheckIllegalMoves(Piece king, BoardSize boardSize, HashSet<Piece> pieces) {
        for (int i = 0; i < positions.size(); i++) {
            Position position = positions.get(i);
            if (ThreatRules.isThisPositionThreatened(king.getPieceColor(), pieces, position)) {
                king.getPossibleMoves().getPositions().remove(position);
                i = -1;
            }
        }
    }
}
