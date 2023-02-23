package Board;

import Pieces.Piece;

import java.util.HashSet;

public class FutureBoard {
    private static FutureBoard instance;
    private final HashSet<Piece> allPieces = new HashSet<>();

    public static FutureBoard getInstance() {
        if (instance == null) {
            instance = new FutureBoard();
        }
        return instance;
    }

    public static FutureBoard getNewInstance() {
        instance = new FutureBoard();
        return instance;
    }

    public void setBoardPieces() {
        allPieces.addAll(Board.getInstance().getAllPieces());
    }

    public HashSet<Piece> getPieces() {
        return allPieces;
    }

    public void removePiece(Piece piece) {
        allPieces.remove(piece);
    }

    public void addPiece(Piece piece) {
        allPieces.add(piece);
    }
}
