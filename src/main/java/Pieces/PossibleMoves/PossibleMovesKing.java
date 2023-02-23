package Pieces.PossibleMoves;

import Board.BoardSize;
import Pieces.Piece;
import Pieces.Position;
import Rules.ThreatRules;
import Utils.MyUtils;

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

    @Override
    protected void removeCheckIllegalMoves(Piece piece, HashSet<Piece> pieces) {
        int i = 0;
        while (i < positions.size()) {
            Position position = positions.get(i);
            if (ThreatRules.isThisPositionThreatened(piece.getPieceColor(), pieces, position)) {
                positions.remove(position);
                i = 0;
                continue;
            }
            i++;
        }
    }

    /**
     * Castle
     */
    @Override
    protected void extraMoves(Piece king, BoardSize boardSize, HashSet<Piece> pieces) {
        if (!king.hasMoved()) {
            HashSet<Piece> rooks = MyUtils.getCastlingRooks(king, pieces);
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
}
