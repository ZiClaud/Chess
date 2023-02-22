package BoardPieces;

import Board.WindowBoard;
import Game.Game;
import Pieces.*;
import Rules.ThreatRules;
import Rules.TurnRules;
import Rules.WinRules;
import Utils.MyUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;

import static BoardPieces.DrawOnPanel.*;

public class DrawOnBoard {
    private static final HashSet<JButton> jMovesButtons = new HashSet<>();
    private static BoardConnectPieces board = BoardConnectPieces.getInstance();

    public static void resetBoard() {
        for (int y = 8; y >= 1; y--) {
            for (char x = 'a'; x <= 'h'; x++) {
                cleanPanel(WindowBoard.getInstance().getMatrixPanels()[y - 1][x - 'a']);
            }
        }
        updatePossibleMoves();
        drawPiecesOnBoard();
        colorCheck();
    }

    public static void drawPiecesOnBoard() {
        TurnRules.getTurn(board.getPieces());
        for (Piece p : board.getPieces()) {
            placePieceOnPanel(p, WindowBoard.getInstance().getMatrixPanels()[p.getPosition().getY() - 1][p.getPosition().getX() - 'a']);
        }
    }

    protected static void moveClicked(Piece piece, char x, int y) {
        WindowBoard.getInstance().colorTiles();
        removeMoveToCoordinatesPanels();
        moveToCoordinates(piece, x, y);
        TurnRules.switchTurn();

        resetBoard();
        WinRules.win(board.getPieces());
    }

    protected static void pieceClicked(Piece piece) {
        WindowBoard.getInstance().colorTiles();
        resetBoard();
        showPossibleMoves(piece);
    }

    private static void updatePossibleMoves() {
        for (Piece piece : board.getPieces()) {
            piece.getPossibleMoves().setPossibleMovesOnBoard(piece, board.getPieces());
        }
    }

    private static void moveToCoordinates(Piece piece, char x, int y) {
        Position oldPosition = piece.getPosition();
        Position position = new Position(x, y);
        piece.move(position, board.getPieces());

        placePieceOnPanel(piece, WindowBoard.getInstance().getMatrixPanels()[piece.getPosition().getY() - 1][piece.getPosition().getX() - 'a']);
        removeMoveToCoordinatesPanels();

        if (piece.getPieceType() == PieceType.Pawn && (piece.getPosition().getY() == 1 || piece.getPosition().getY() == 8)) {
            upgradePawn(piece);
        }

        if (piece.getPieceType() == PieceType.King) {
            MyUtils.getCastlingRooks(piece, board.getPieces());
            if (position.getX() == (oldPosition.getX() + 2)) {
                castleRightRook(piece);
            }
            if (position.getX() == (oldPosition.getX() - 2)) {
                castleLeftRook(piece);
            }
        }
    }

    private static void castleRightRook(Piece king) {
        HashSet<Piece> rooks = MyUtils.getCastlingRooks(king, board.getPieces());
        Position position = new Position('f', king.getPosition().getY());
        for (Piece rook : rooks) {
            if (rook.getPosition().getX() > king.getPosition().getX()) {
                rook.move(position, board.getPieces());
            }
        }
    }

    private static void castleLeftRook(Piece king) {
        HashSet<Piece> rooks = MyUtils.getCastlingRooks(king, board.getPieces());
        Position position = new Position('d', king.getPosition().getY());
        for (Piece rook : rooks) {
            if (rook.getPosition().getX() < king.getPosition().getX()) {
                rook.move(position, board.getPieces());
            }
        }
    }

    private static void upgradePawn(Piece piece) {
        PieceType pieceType = chooseUpgradePiece();
        board.getPieces().remove(piece);
        piece = new PieceImpl(pieceType, piece.getPieceColor(), piece.getPosition());
        board.getPieces().add(piece);
    }

    private static PieceType chooseUpgradePiece() {
        PieceType[] options = {PieceType.Queen, PieceType.Knight, PieceType.Bishop, PieceType.Tower};
        PieceType selectedPiece;
        do {
            selectedPiece = (PieceType) JOptionPane.showInputDialog(null, "Board size", "Choose the board size", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        } while (selectedPiece == null);
        return selectedPiece;
    }

    private static void colorCheck() {
        if (ThreatRules.isCheck(board.getPieces())) {
            Piece king = getKingWhoseTurnIsIt();
            assert king != null;
            char x = king.getPosition().getX();
            int y = king.getPosition().getY();

            WindowBoard.getInstance().colorThisTileRed(x, y);
        }
    }

    private static Piece getKingWhoseTurnIsIt() {
        if (Game.whitePlayer.isTurn()) {
            return ThreatRules.getKing(PieceColor.WHITE, board.getPieces());
        }
        if (Game.blackPlayer.isTurn()) {
            return ThreatRules.getKing(PieceColor.BLACK, board.getPieces());
        }

        assert false;
        return null;
    }

    private static void removeMoveToCoordinatesPanels() {
        JPanel[][] matrixPanels = WindowBoard.getInstance().getMatrixPanels();
        for (int y = 8; y >= 1; y--) {
            for (char x = 'a'; x <= 'h'; x++) {
                for (JButton b : jMovesButtons) {
                    matrixPanels[y - 1][x - 'a'].remove(b);
                }
            }
        }
    }

    private static void showPossibleMoves(Piece piece) {
        ArrayList<Position> positions = piece.getPossibleMoves().getPositions();

        for (Position position : positions) {
            placeMoveOnPanel(piece, position.getX(), position.getY(), jMovesButtons);
        }
    }
}
