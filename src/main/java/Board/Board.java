package Board;

import Pieces.Piece;
import Pieces.PieceColor;
import Player.Player;
import Player.PlayerWhite;

import java.util.HashSet;

public class Board {
    private static Board instance;
    private final HashSet<Piece> allPieces = new HashSet<>();
    private final Player playerWhite = PlayerWhite.getInstance();
    private final Player playerBlack = PlayerWhite.getInstance();

    private Board() {
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public HashSet<Piece> getAllPieces() {
        allPieces.addAll(playerWhite.getPieces());
        allPieces.addAll(playerBlack.getPieces());
        return allPieces;
    }

    public void removePiece(Piece piece) {
        allPieces.remove(piece);
        if (playerWhite.getPieces().contains(piece)) {
            playerWhite.getPieces().remove(piece);
            return;
        }
        if (playerBlack.getPieces().contains(piece)) {
            playerBlack.getPieces().remove(piece);
            return;
        }
        assert false;
    }

    public void addPiece(Piece piece) {
        allPieces.add(piece);
        if (piece.getPieceColor() == PieceColor.WHITE) {
            playerWhite.getPieces().add(piece);
            return;
        }
        if (piece.getPieceColor() == PieceColor.BLACK) {
            playerBlack.getPieces().add(piece);
            return;
        }
        assert false;
    }
}
