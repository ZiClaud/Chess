package BoardPieces;

import Board.WindowBoard;
import Pieces.*;
import Rules.PiecesRules;
import Rules.TurnRules;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that connects Board with Pieces
 * Draws the Pieces into the Board
 */
public class BoardConnectPieces {
    private final WindowBoard windowBoard;
    private final HashSet<Piece> pieces = new HashSet<>();
    private final HashSet<JButton> jMovesButtons = new HashSet<>();

    public BoardConnectPieces(WindowBoard windowBoard) {
        this.windowBoard = windowBoard;
        setupPieces();
    }

    public HashSet<Piece> getPieces() {
        return pieces;
    }

    public void drawPiecesOnBoard() {
        TurnRules.getTurn(this);
        for (Piece p : pieces) {
            placePieceOnPanel(p, windowBoard.getMatrixPanels()[p.getPosY() - 1][p.getPosX() - 'a']);
        }
    }

    public void resetBoard() {
        for (int y = 8; y >= 1; y--) {
            for (char x = 'a'; x <= 'h'; x++) {
                cleanPanel(windowBoard.getMatrixPanels()[y - 1][x - 'a']);
            }
        }
        drawPiecesOnBoard();
    }

    private void cleanPanel(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    private void placePieceOnPanel(Piece piece, JPanel panel) {
        BufferedImage myPicture = piece.getImg();
        Image scaledImage = myPicture.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);

        JButton picLabel = new JButton(new ImageIcon(scaledImage));
        picLabel.setOpaque(false);
        picLabel.setContentAreaFilled(false);
        picLabel.setBorderPainted(false);
        if (piece.isTurn()) {
            picLabel.addActionListener(e -> {
                pieceClicked(piece);
            });
        }
        cleanPanel(panel);
        panel.add(picLabel);
    }

    private void placeMoveOnPanel(Piece piece, char x, int y) {
        JButton moveHereBT = new JButton();
        moveHereBT.setOpaque(true);
        moveHereBT.setContentAreaFilled(false);
        moveHereBT.setBorderPainted(false);
        moveHereBT.addActionListener(actionEvent -> {
            moveClicked(piece, x, y);
        });

        windowBoard.getMatrixPanels()[y - 1][x - 'a'].setBackground(Color.GRAY);
        windowBoard.getMatrixPanels()[y - 1][x - 'a'].add(moveHereBT);
        jMovesButtons.add(moveHereBT);
    }

    private void pieceClicked(Piece piece) {
        windowBoard.colorTiles();
        resetBoard();
        showPossibleMoves(piece);
    }

    private void moveClicked(Piece piece, char x, int y) {
        for (Piece enemyP : pieces) {
            if (enemyP.getPosX() == x && enemyP.getPosY() == y) {
                pieces.remove(enemyP);
                break;
            }
        }
        windowBoard.colorTiles();
        removeMoveToCoordinatesPanels(windowBoard);
        moveToCoordinates(piece, x, y);

        TurnRules.switchTurn();

        resetBoard();
    }

    private void showPossibleMoves(Piece piece) {
        for (int y = 8; y >= 1; y--) {
            for (char x = 'a'; x <= 'h'; x++) {
                if (PiecesRules.canPieceMoveHere(piece, this, x, y)) {
                    placeMoveOnPanel(piece, x, y);
                }
            }
        }
    }

    private void moveToCoordinates(Piece piece, char x, int y) {
        piece.setPosX(x);
        piece.setPosY(y);

        placePieceOnPanel(piece, windowBoard.getMatrixPanels()[piece.getPosY() - 1][piece.getPosX() - 'a']);
        removeMoveToCoordinatesPanels(windowBoard);

        if (piece.getPieceName().endsWith("Pawn") && (piece.getPosY() == 1 || piece.getPosY() == 8)) {
            if (piece.getPieceName().startsWith("White")) {
                upgradeWhitePawn(piece, x, y);
            } else if (piece.getPieceName().startsWith("Black")) {
                upgradeBlackPawn(piece, x, y);
            } else {
                assert (false);
            }
        }
    }

    private void upgradeWhitePawn(Piece piece, char x, int y) {
        PieceType pieceType = chooseUpgradePiece();
        pieces.remove(piece);
        piece = PieceFactory.newPiece(PieceColor.WHITE, pieceType, x, y);
        pieces.add(piece);
    }

    private void upgradeBlackPawn(Piece piece, char x, int y) {
        PieceType pieceType = chooseUpgradePiece();
        pieces.remove(piece);
        piece = PieceFactory.newPiece(PieceColor.BLACK, pieceType, x, y);
        pieces.add(piece);
    }

    private PieceType chooseUpgradePiece() {
        PieceType[] options = {PieceType.Queen, PieceType.Knight, PieceType.Bishop, PieceType.Tower};
        PieceType selectedPiece;
        do {
            selectedPiece = (PieceType) JOptionPane.showInputDialog(null, "Board size", "Choose the board size", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        } while (selectedPiece == null);
        return selectedPiece;
    }

    private void removeMoveToCoordinatesPanels(WindowBoard windowBoard) {
        JPanel[][] matrixPanels = windowBoard.getMatrixPanels();
        for (int y = 8; y >= 1; y--) {
            for (char x = 'a'; x <= 'h'; x++) {
                for (JButton b : jMovesButtons) {
                    matrixPanels[y - 1][x - 'a'].remove(b);
                }
            }
        }
    }
// TODO: Use factory
    private void setupPieces() {
        pieces.addAll(Set.of(
                new Tower(new WhitePiece(), 'a', 1),
                new Knight(new WhitePiece(), 'b', 1),
                new Bishop(new WhitePiece(), 'c', 1),
                new Queen(new WhitePiece(), 'd', 1),
                new King(new WhitePiece(), 'e', 1),
                new Bishop(new WhitePiece(), 'f', 1),
                new Knight(new WhitePiece(), 'g', 1),
                new Tower(new WhitePiece(), 'h', 1),

                new Pawn(new WhitePiece(), 'a', 2),
                new Pawn(new WhitePiece(), 'b', 2),
                new Pawn(new WhitePiece(), 'c', 2),
                new Pawn(new WhitePiece(), 'd', 2),
                new Pawn(new WhitePiece(), 'e', 2),
                new Pawn(new WhitePiece(), 'f', 2),
                new Pawn(new WhitePiece(), 'g', 2),
                new Pawn(new WhitePiece(), 'h', 2),

                new Pawn(new BlackPiece(), 'a', 7),
                new Pawn(new BlackPiece(), 'b', 7),
                new Pawn(new BlackPiece(), 'c', 7),
                new Pawn(new BlackPiece(), 'd', 7),
                new Pawn(new BlackPiece(), 'e', 7),
                new Pawn(new BlackPiece(), 'f', 7),
                new Pawn(new BlackPiece(), 'g', 7),
                new Pawn(new BlackPiece(), 'h', 7),

                new Tower(new BlackPiece(), 'a', 8),
                new Knight(new BlackPiece(), 'b', 8),
                new Bishop(new BlackPiece(), 'c', 8),
                new Queen(new BlackPiece(), 'd', 8),
                new King(new BlackPiece(), 'e', 8),
                new Bishop(new BlackPiece(), 'f', 8),
                new Knight(new BlackPiece(), 'g', 8),
                new Tower(new BlackPiece(), 'h', 8)));
    }
}
