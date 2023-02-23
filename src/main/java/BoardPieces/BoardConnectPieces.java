package BoardPieces;

import Board.WindowBoard;
import Pieces.Piece;
import Player.PlayerBlack;
import Player.PlayerWhite;

import java.util.HashSet;

/**
 * Class that connects Board with PiecesOld
 * Draws the PiecesOld into the Board
 */
public class BoardConnectPieces {
    private static BoardConnectPieces instance;
    private final HashSet<Piece> whitePieces = PlayerWhite.getInstance().getPieces();
    private final HashSet<Piece> blackPieces = PlayerBlack.getInstance().getPieces();

    private BoardConnectPieces() {
        SetupPieces.setupPieces();
    }

    public static BoardConnectPieces getInstance() {
        if (instance == null) {
            instance = new BoardConnectPieces();
        }
        return instance;
    }

    public static BoardConnectPieces getNewInstance() {
        WindowBoard.getInstance().removeWindow();
        WindowBoard.getNewInstance();
        instance = new BoardConnectPieces();
        return instance;
    }

    public HashSet<Piece> getWhitePieces() {
        return whitePieces;
    }

    public HashSet<Piece> getBlackPieces() {
        return blackPieces;
    }
}

/*
    private boolean elPassant(Piece piece, Piece enemyP, char x, int y) {
        if (piece.getPieceType() == PieceType.Pawn && piece.getPieceColor() == PieceColor.WHITE) {
            if (enemyP.getPosX() == x && enemyP.getPosY() == y - 1 && enemyP.getPieceColor() == PieceColor.BLACK && enemyP.getPieceType() == PieceType.Pawn) {
                pieces.remove(enemyP);
                return true;
            }
        }
        if (piece.getPieceType() == PieceType.Pawn && piece.getPieceColor() == PieceColor.BLACK) {
            if (enemyP.getPosX() == x && enemyP.getPosY() == y + 1 && enemyP.getPieceColor() == PieceColor.WHITE && enemyP.getPieceType() == PieceType.Pawn) {
                pieces.remove(enemyP);
                return true;
            }
        }
        return false;
    }
 */