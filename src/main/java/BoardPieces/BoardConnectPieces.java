package BoardPieces;

import Board.WindowBoard;
import Game.Game;
import Game.SetupPieces;
import Pieces.*;
import Rules.ThreatRules;
import Rules.TurnRules;
import Rules.WinRules;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class that connects Board with PiecesOld
 * Draws the PiecesOld into the Board
 */
public class BoardConnectPieces {
    private static BoardConnectPieces instance;
    private final WindowBoard windowBoard;
    private final HashSet<Piece> pieces = new HashSet<>();
    private final HashSet<JButton> jMovesButtons = new HashSet<>();

    private BoardConnectPieces() {
        this.windowBoard = WindowBoard.getInstance();
        SetupPieces.setupPieces(pieces);
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

    public WindowBoard getWindowBoard() {
        return windowBoard;
    }

    public HashSet<Piece> getPieces() {
        return pieces;
    }

    public void drawPiecesOnBoard() {
        TurnRules.getTurn(getPieces());
        for (Piece p : pieces) {
            placePieceOnPanel(p, windowBoard.getMatrixPanels()[p.getPosition().getY() - 1][p.getPosition().getX() - 'a']);
        }
    }

    public void resetBoard() {
        for (int y = 8; y >= 1; y--) {
            for (char x = 'a'; x <= 'h'; x++) {
                cleanPanel(windowBoard.getMatrixPanels()[y - 1][x - 'a']);
            }
        }
        updatePossibleMoves();
        drawPiecesOnBoard();
        colorCheck();
    }

    private void updatePossibleMoves() {
        for (Piece piece : pieces) {
            piece.getPossibleMoves().setPossibleMovesOnBoard(piece, pieces);
        }
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

    private void pieceClicked(Piece piece) {
        windowBoard.colorTiles();
        resetBoard();
        showPossibleMoves(piece);
    }


    private void showPossibleMoves(Piece piece) {
        ArrayList<Position> positions = piece.getPossibleMoves().getPositions();

        for (Position position : positions) {
            placeMoveOnPanel(piece, position.getX(), position.getY());
        }
    }

    private void moveClicked(Piece piece, char x, int y) {
        windowBoard.colorTiles();
        removeMoveToCoordinatesPanels(windowBoard);
        moveToCoordinates(piece, x, y);

        TurnRules.switchTurn();

        resetBoard();

        WinRules.win(getPieces());
    }

    private void colorCheck() {
        if (ThreatRules.isCheck(pieces)) {
            Piece king = getKingWhoseTurnIsIt();
            assert king != null;
            char x = king.getPosition().getX();
            int y = king.getPosition().getY();

            windowBoard.colorThisTileRed(x, y);
        }
    }

    private Piece getKingWhoseTurnIsIt() {
        if (Game.whitePlayer.isTurn()) {
            return ThreatRules.getKing(PieceColor.WHITE, pieces);
        }
        if (Game.blackPlayer.isTurn()) {
            return ThreatRules.getKing(PieceColor.BLACK, pieces);
        }

        assert false;
        return null;
    }


    private void moveToCoordinates(Piece piece, char x, int y) {
        Position position = new Position(x, y);
        piece.move(position, windowBoard.getBoardSize(), getPieces());

        placePieceOnPanel(piece, windowBoard.getMatrixPanels()[piece.getPosition().getY() - 1][piece.getPosition().getX() - 'a']);
        removeMoveToCoordinatesPanels(windowBoard);

        if (piece.getPieceType() == PieceType.Pawn && (piece.getPosition().getY() == 1 || piece.getPosition().getY() == 8)) {
            upgradePawn(piece);
        }
    }

    private void upgradePawn(Piece piece) {
        PieceType pieceType = chooseUpgradePiece();
        pieces.remove(piece);
        piece = new PieceImpl(pieceType, piece.getPieceColor(), piece.getPosition());
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
}

/*
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
*/

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
/*
    private void castle(Piece king, char x) {
        if (king.getPieceType() == PieceType.King && ((KingOld) king).canCastle()) {
            if (x == 'g') {
                for (Piece rook : pieces) {
                    if (rook.getPieceType() == PieceType.Tower && rook.getPieceColor() == king.getPieceColor() && rook.getPosX() == 'h') {
                        rook.setPosX('f');
                        king.setPosX('g');
                        ((KingOld) king).setCastle(false);
                        ((TowerOld) rook).setAllowCastle(false);
                        break;
                    }
                }
            }
            if (x == 'c') {
                for (Piece rook : pieces) {
                    if (rook.getPieceType() == PieceType.Tower && rook.getPieceColor() == king.getPieceColor() && rook.getPosX() == 'a') {
                        rook.setPosX('d');
                        king.setPosX('c');
                        ((KingOld) king).setCastle(false);
                        ((TowerOld) rook).setAllowCastle(false);
                        break;
                    }
                }
            }
        }
    }
*/
/*
    private void showPossibleMoves(Piece piece) {
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
    }     */

/*
    private void showCastleMove(Piece king) {
        if (king.getPieceType() == PieceType.King) {
            for (Piece rook : pieces) {
                if (rook.getPieceType() == PieceType.Tower && rook.getPosY() == king.getPosY()) {
                    if (((TowerOld) rook).allowsCastling() && rook.getPosX() == 'h' && ComplexRules.canThisKingCastleRight(king, getPieces())) {
                        placeCaslteMoveOnPanel(king, rook);
                    }
                    if (((TowerOld) rook).allowsCastling() && rook.getPosX() == 'a' && ComplexRules.canThisKingCastleLeft(king, getPieces())) {
                        placeCaslteMoveOnPanel(king, rook);
                    }
                }
            }
        }
    }
*/