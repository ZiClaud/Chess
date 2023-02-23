package BoardPieces;

import Game.Game;
import Pieces.*;
import Player.PlayerBlack;
import Player.PlayerWhite;

import java.util.*;

public class SetupPieces {
    private static ArrayList<PieceType> pieceOrder = new ArrayList<>(List.of(PieceType.Tower, PieceType.Knight, PieceType.Bishop, PieceType.Queen, PieceType.King, PieceType.Bishop, PieceType.Knight, PieceType.Tower));

    public static void setupPieces() {
        if (!Game.gameMode.equals("Normal")) {
            do {
                Collections.shuffle(pieceOrder);
            } while (!isKingBetweenRooks(pieceOrder) || areBishopsOnSameColor(pieceOrder));
        }

        setupWhitePieces();
        setupBlackPieces();
    }


    public static void setupPieces(HashSet<Piece> allPieces) {
        if (!Game.gameMode.equals("Normal")) {
            do {
                Collections.shuffle(pieceOrder);
            } while (!isKingBetweenRooks(pieceOrder) || areBishopsOnSameColor(pieceOrder));
        }

        if (!allPieces.isEmpty()) {
            allPieces.removeAll(allPieces);
        }
        setupWhitePieces();
        setupBlackPieces();

        allPieces.addAll(PlayerWhite.getInstance().getPieces());
        allPieces.addAll(PlayerBlack.getInstance().getPieces());
    }

    private static void setupWhitePieces() {
        HashSet<Piece> pieces = PlayerWhite.getInstance().getPieces();
        if (!pieces.isEmpty()) {
            pieces.removeAll(pieces);
        }
        addPawns(pieces);
        addPieces(pieces);
    }

    private static void setupBlackPieces() {
        HashSet<Piece> pieces = PlayerBlack.getInstance().getPieces();
        if (!pieces.isEmpty()) {
            pieces.removeAll(pieces);
        }
        addPawns(pieces);
        addPieces(pieces);
    }

    private static void addPawns(HashSet<Piece> pieces) {
        addWhitePawns(pieces);
        addBlackPawns(pieces);
    }

    private static void addWhitePawns(HashSet<Piece> pieces) {
        pieces.addAll(Set.of(
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.WHITE, new Position('a', 2)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.WHITE, new Position('b', 2)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.WHITE, new Position('c', 2)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.WHITE, new Position('d', 2)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.WHITE, new Position('e', 2)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.WHITE, new Position('f', 2)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.WHITE, new Position('g', 2)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.WHITE, new Position('h', 2))
        ));
    }

    private static void addBlackPawns(HashSet<Piece> pieces) {
        pieces.addAll(Set.of(
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.BLACK, new Position('a', 7)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.BLACK, new Position('b', 7)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.BLACK, new Position('c', 7)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.BLACK, new Position('d', 7)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.BLACK, new Position('e', 7)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.BLACK, new Position('f', 7)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.BLACK, new Position('g', 7)),
                PieceFactory.newPiece(PieceType.Pawn, PieceColor.BLACK, new Position('h', 7))
        ));
    }

    private static void addPieces(HashSet<Piece> pieces) {
        addWhitePieces(pieces);
        addBlackPieces(pieces);
    }

    private static void addWhitePieces(HashSet<Piece> pieces) {
        pieces.addAll(Set.of(
                PieceFactory.newPiece(pieceOrder.get(0), PieceColor.WHITE, new Position('a', 1)),
                PieceFactory.newPiece(pieceOrder.get(1), PieceColor.WHITE, new Position('b', 1)),
                PieceFactory.newPiece(pieceOrder.get(2), PieceColor.WHITE, new Position('c', 1)),
                PieceFactory.newPiece(pieceOrder.get(3), PieceColor.WHITE, new Position('d', 1)),
                PieceFactory.newPiece(pieceOrder.get(4), PieceColor.WHITE, new Position('e', 1)),
                PieceFactory.newPiece(pieceOrder.get(5), PieceColor.WHITE, new Position('f', 1)),
                PieceFactory.newPiece(pieceOrder.get(6), PieceColor.WHITE, new Position('g', 1)),
                PieceFactory.newPiece(pieceOrder.get(7), PieceColor.WHITE, new Position('h', 1))
        ));
    }

    private static void addBlackPieces(HashSet<Piece> pieces) {
        pieces.addAll(Set.of(
                PieceFactory.newPiece(pieceOrder.get(0), PieceColor.BLACK, new Position('a', 8)),
                PieceFactory.newPiece(pieceOrder.get(1), PieceColor.BLACK, new Position('b', 8)),
                PieceFactory.newPiece(pieceOrder.get(2), PieceColor.BLACK, new Position('c', 8)),
                PieceFactory.newPiece(pieceOrder.get(3), PieceColor.BLACK, new Position('d', 8)),
                PieceFactory.newPiece(pieceOrder.get(4), PieceColor.BLACK, new Position('e', 8)),
                PieceFactory.newPiece(pieceOrder.get(5), PieceColor.BLACK, new Position('f', 8)),
                PieceFactory.newPiece(pieceOrder.get(6), PieceColor.BLACK, new Position('g', 8)),
                PieceFactory.newPiece(pieceOrder.get(7), PieceColor.BLACK, new Position('h', 8))
        ));
    }

    private static boolean isKingBetweenRooks(ArrayList<PieceType> pieces) {

        boolean rook1 = false;
        boolean king = false;
        boolean rook2 = false;

        for (PieceType piece : pieces) {
            if (piece == PieceType.Tower) {
                rook1 = true;
            }
            if (rook1 && piece == PieceType.King) {
                king = true;
            }
            if (king && piece == PieceType.Tower) {
                rook2 = true;
            }
        }

        return rook2;
    }

    private static boolean areBishopsOnSameColor(ArrayList<PieceType> pieces) {
        int bishop1 = -1;
        int bishop2 = -1;


        for (int i = 0; i < pieces.size(); i++) {
            PieceType piece = pieces.get(i);

            if (piece == PieceType.Bishop && bishop1 == -1) {
                bishop1 = i % 2;
            } else if (piece == PieceType.Bishop) {
                bishop2 = i % 2;
            }
        }

        return (bishop1 == bishop2);
    }
}
