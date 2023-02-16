package Pieces;

public class PieceFactory {
    public static Piece newPiece(PieceType pieceType, PieceColor pieceColor, Position position) {
        if (pieceType == PieceType.Pawn){
            return new PieceImpl(pieceType, pieceColor, position);
        }
        if (pieceType == PieceType.King){
            return new PieceImpl(pieceType, pieceColor, position);
        }
        return new PieceImpl(pieceType, pieceColor, position);
    }
}
