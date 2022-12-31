package BoardPieces;

import Board.WindowBoard;
import Pieces.*;
import Rules.ComplexRules;
import Rules.PiecesRules;
import Rules.ThreatRules;
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
        TurnRules.getTurn(getPieces());
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

        // TODO: Clean this code -> It makes picLabel button do the same thing of moveHereBT
        if (windowBoard.getMatrixPanels()[y - 1][x - 'a'].getComponentCount() > 1) {
            ((JButton) windowBoard.getMatrixPanels()[y - 1][x - 'a'].getComponents()[0]).addActionListener(actionEvent -> {
                moveClicked(piece, x, y);
            });
        }
    }

    private void placeCaslteMoveOnPanel(Piece king, Piece rook) {   // TODO: It's pretty much equal to the other one
        JButton moveHereBT = new JButton();
        moveHereBT.setOpaque(true);
        moveHereBT.setContentAreaFilled(false);
        moveHereBT.setBorderPainted(false);

        char x = 0;
        int y = king.getPosY();

        if (rook.getPosX() == 'h') {
            x = 'g';
        } else if (rook.getPosX() == 'a') {
            x = 'c';
        }
        char finalX = x;

        assert (x != 0);

        moveHereBT.addActionListener(actionEvent -> {
            moveClicked(king, finalX, y);
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

            if (elPassant(piece, enemyP, x, y)) {
                break;
            }
        }
        castle(piece, x);

        windowBoard.colorTiles();
        removeMoveToCoordinatesPanels(windowBoard);
        moveToCoordinates(piece, x, y);

        TurnRules.switchTurn();

        resetBoard();
    }

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

    private void castle(Piece king, char x) {
        if (king.getPieceType() == PieceType.King && ((King) king).canCastle()) {
            if (x == 'g') {
                for (Piece rook : pieces) {
                    if (rook.getPieceType() == PieceType.Tower && rook.getPieceColor() == king.getPieceColor() && rook.getPosX() == 'h') {
                        rook.setPosX('f');
                        king.setPosX('g');
                        ((King) king).setCastle(false);
                        ((Tower) rook).setAllowCastle(false);
                        break;
                    }
                }
            }
            if (x == 'c') {
                for (Piece rook : pieces) {
                    if (rook.getPieceType() == PieceType.Tower && rook.getPieceColor() == king.getPieceColor() && rook.getPosX() == 'a') {
                        rook.setPosX('d');
                        king.setPosX('c');
                        ((King) king).setCastle(false);
                        ((Tower) rook).setAllowCastle(false);
                        break;
                    }
                }
            }
        }
    }

    private void showPossibleMoves(Piece piece) {
        System.out.println(PiecesRules.getPossibleMoves(piece, getPieces()));
        if (ThreatRules.isCheckBlackK(getPieces())) {
            System.out.println("CheckBlackKing");
        }
// TODO: Change with PiecesRules.getPossibleMoves(piece, getPieces()); ?
        for (int y = 8; y >= 1; y--) {
            for (char x = 'a'; x <= 'h'; x++) {
                if (PiecesRules.isThisAPossibleMove(piece, getPieces(), x, y)) {
                    if (ThreatRules.isCheckWhiteK(getPieces()) || ThreatRules.isCheckBlackK(getPieces())) {
                        if (ThreatRules.doesStopCheck(getPieces(), piece, x, y)) {
                            placeMoveOnPanel(piece, x, y);
                        }
                    } else {
                        placeMoveOnPanel(piece, x, y);
                    }
                }
            }
        }

        if (piece.getPieceType() == PieceType.King) {
            showCastleMove(piece);
        }
    }

    private void showCastleMove(Piece king) {
        if (king.getPieceType() == PieceType.King) {
            for (Piece rook : pieces) {
                if (rook.getPieceType() == PieceType.Tower) {
                    if (((Tower) rook).allowsCastling() && rook.getPosX() == 'h' && ComplexRules.canThisKingCastleRight(king, getPieces())) {
                        placeCaslteMoveOnPanel(king, rook);
                    }
                    if (((Tower) rook).allowsCastling() && rook.getPosX() == 'a' && ComplexRules.canThisKingCastleLeft(king, getPieces())) {
                        placeCaslteMoveOnPanel(king, rook);
                    }
                }
            }
        }
    }

    private void moveToCoordinates(Piece piece, char x, int y) {
        if (piece.getPieceType() == PieceType.Pawn) {
            ((Pawn) piece).updatePreviousPos();
        }
        if (piece.getPieceType() == PieceType.King) {
            ((King) piece).setCastle(false);
        }
        if (piece.getPieceType() == PieceType.Tower) {
            ((Tower) piece).setAllowCastle(false);
        }

        piece.setPosX(x);
        piece.setPosY(y);

        placePieceOnPanel(piece, windowBoard.getMatrixPanels()[piece.getPosY() - 1][piece.getPosX() - 'a']);
        removeMoveToCoordinatesPanels(windowBoard);

        if (piece.getPieceType() == PieceType.Pawn && (piece.getPosY() == 1 || piece.getPosY() == 8)) {
            upgradePawn(piece, x, y);
        }
    }

    private void upgradePawn(Piece piece, char x, int y) {
        PieceType pieceType = chooseUpgradePiece();
        pieces.remove(piece);
        piece = PieceFactory.newPiece(piece.getPieceColor(), pieceType, x, y);
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
