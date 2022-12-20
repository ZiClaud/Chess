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
                pieceClicked(piece, panel);
            });
        }
        cleanPanel(panel);
        panel.add(picLabel);
    }

    private void placeMoveOnPanel(Piece piece, JPanel panel, char x, int y) {
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

    private void pieceClicked(Piece piece, JPanel panel) {
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
                    char finalX = x;
                    int finalY = y;
                    placeMoveOnPanel(piece, windowBoard.getMatrixPanels()[y - 1][x - 'a'], x, y);
                }
            }
        }
    }

    private void moveToCoordinates(Piece piece, char x, int y) {
        piece.setPosX(x);
        piece.setPosY(y);

        placePieceOnPanel(piece, windowBoard.getMatrixPanels()[piece.getPosY() - 1][piece.getPosX() - 'a']);
        removeMoveToCoordinatesPanels(windowBoard);

        if (piece.getPieceType() == PieceType.Pawn && (piece.getPosY() == 1 || piece.getPosY() == 8)) {
            if (piece.getPieceColor() == PieceColor.WHITE) {
                upgradeWhitePawn(piece, x, y);
            }
            if (piece.getPieceColor() == PieceColor.BLACK) {
                upgradeBlackPawn(piece, x, y);
            }
        }
    }

    private void upgradeWhitePawn(Piece piece, char x, int y) {
        PieceType pieceType = chooseUpgradePiece();
        pieces.remove(piece);
        if (pieceType == PieceType.Queen) {
            piece = new WhiteQueen(x, y);
        } else if (pieceType == PieceType.Knight) {
            piece = new WhiteKnight(x, y);
        } else if (pieceType == PieceType.Bishop) {
            piece = new WhiteBishop(x, y);
        } else if (pieceType == PieceType.Tower) {
            piece = new WhiteTower(x, y);
        }
        pieces.add(piece);
    }

    private void upgradeBlackPawn(Piece piece, char x, int y) {
        PieceType pieceType = chooseUpgradePiece();
        pieces.remove(piece);
        if (pieceType == PieceType.Queen) {
            piece = new BlackQueen(x, y);
        } else if (pieceType == PieceType.Knight) {
            piece = new BlackKnight(x, y);
        } else if (pieceType == PieceType.Bishop) {
            piece = new BlackBishop(x, y);
        } else if (pieceType == PieceType.Tower) {
            piece = new BlackTower(x, y);
        }
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

    private void setupPieces() {
        pieces.addAll(Set.of(
                new WhiteTower('a', 1),
                new WhiteKnight('b', 1),
                new WhiteBishop('c', 1),
                new WhiteQueen('d', 1),
                new WhiteKing('e', 1),
                new WhiteBishop('f', 1),
                new WhiteKnight('g', 1),
                new WhiteTower('h', 1),

                new WhitePawn('a', 2),
                new WhitePawn('b', 2),
                new WhitePawn('c', 2),
                new WhitePawn('d', 2),
                new WhitePawn('e', 2),
                new WhitePawn('f', 2),
                new WhitePawn('g', 2),
                new WhitePawn('h', 2),

                new BlackPawn('a', 7),
                new BlackPawn('b', 7),
                new BlackPawn('c', 7),
                new BlackPawn('d', 7),
                new BlackPawn('e', 7),
                new BlackPawn('f', 7),
                new BlackPawn('g', 7),
                new BlackPawn('h', 7),

                new BlackTower('a', 8),
                new BlackKnight('b', 8),
                new BlackBishop('c', 8),
                new BlackQueen('d', 8),
                new BlackKing('e', 8),
                new BlackBishop('f', 8),
                new BlackKnight('g', 8),
                new BlackTower('h', 8)));
    }
}
