package BoardPieces;

import Board.WindowBoard;
import Game.Game;
import Pieces.*;
import Rules.ThreatRules;
import Rules.TurnRules;
import Rules.WinRules;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.*;

/**
 * Class that connects Board with PiecesOld
 * Draws the PiecesOld into the Board
 */
public class BoardConnectPieces {
    private final WindowBoard windowBoard;
    private final HashSet<Piece> pieces = new HashSet<>();
    private final HashSet<JButton> jMovesButtons = new HashSet<>();

    public BoardConnectPieces(WindowBoard windowBoard) {
        this.windowBoard = windowBoard;
        setupPieces();
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
    private void pieceClicked(Piece piece) {
        windowBoard.colorTiles();
        resetBoard();
        showPossibleMoves(piece);
    }

    public void updatePossibleMoves() {
        for (Piece piece : pieces) {
            piece.getPossibleMoves().setPossibleMovesOnBoard(piece, this);
        }
    }

    private void showPossibleMoves(Piece piece) {
//        piece.getPossibleMoves().setPossibleMovesOnBoard(piece, this);
        ArrayList<Position> positions = piece.getPossibleMoves().getPositions();
        //...// ArrayList<Position> positions = piece.getPossibleMoves().getPositions();

        //System.out.println(piece);
        //System.out.println(positions);

        for (Position position : positions) {
            placeMoveOnPanel(piece, position.getX(), position.getY());
        }
    }

    private void moveClicked(Piece piece, char x, int y) {
        /*
        for (Piece enemyP : pieces) {
            if (enemyP.getPosX() == x && enemyP.getPosY() == y) {
                pieces.remove(enemyP);
                break;
            }
        }
        */

        windowBoard.colorTiles();
        removeMoveToCoordinatesPanels(windowBoard);
        moveToCoordinates(piece, x, y);

        TurnRules.switchTurn();

        resetBoard();

        WinRules.win(getPieces());
    }

    private void colorCheck() {
        System.out.println("ThreatRules.isCheck(pieces): " + ThreatRules.isCheck(pieces));
        if (ThreatRules.isCheck(pieces)) {
            System.out.println("ok");
            Piece king = getKingWhoseTurnIsIt();
            assert king != null;
            char x = king.getPosition().getX();
            int y = king.getPosition().getY();

            windowBoard.colorThisTileRed(x, y);
        }
    }

    private Piece getKingWhoseTurnIsIt() {
        System.out.println("Game.whitePlayer.isTurn(): " + Game.whitePlayer.isTurn());
        if (Game.whitePlayer.isTurn()) {
            return ThreatRules.getKing(PieceColor.WHITE, pieces);
        }
        if (Game.blackPlayer.isTurn()) {
            return ThreatRules.getKing(PieceColor.BLACK, pieces);
        }

        assert false;
        return null;
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

    private void setupPieces() {
        addPawns();
        ArrayList<PieceType> pieceOrder = new ArrayList<>(List.of(PieceType.Tower, PieceType.Knight, PieceType.Bishop, PieceType.Queen, PieceType.King, PieceType.Bishop, PieceType.Knight, PieceType.Tower));

        if (!Game.gameMode.equals("Normal")) {
            do {
                Collections.shuffle(pieceOrder);
            } while (!isKingBetweenRooks(pieceOrder) || areBishopsOnSameColor(pieceOrder));
        }

        pieces.addAll(Set.of(
                new PieceImpl(pieceOrder.get(0), PieceColor.WHITE, new Position('a', 1)),
                new PieceImpl(pieceOrder.get(1), PieceColor.WHITE, new Position('b', 1)),
                new PieceImpl(pieceOrder.get(2), PieceColor.WHITE, new Position('c', 1)),
                new PieceImpl(pieceOrder.get(3), PieceColor.WHITE, new Position('d', 1)),
                new PieceImpl(pieceOrder.get(4), PieceColor.WHITE, new Position('e', 1)),
                new PieceImpl(pieceOrder.get(5), PieceColor.WHITE, new Position('f', 1)),
                new PieceImpl(pieceOrder.get(6), PieceColor.WHITE, new Position('g', 1)),
                new PieceImpl(pieceOrder.get(7), PieceColor.WHITE, new Position('h', 1)),

                new PieceImpl(pieceOrder.get(0), PieceColor.BLACK, new Position('a', 8)),
                new PieceImpl(pieceOrder.get(1), PieceColor.BLACK, new Position('b', 8)),
                new PieceImpl(pieceOrder.get(2), PieceColor.BLACK, new Position('c', 8)),
                new PieceImpl(pieceOrder.get(3), PieceColor.BLACK, new Position('d', 8)),
                new PieceImpl(pieceOrder.get(4), PieceColor.BLACK, new Position('e', 8)),
                new PieceImpl(pieceOrder.get(5), PieceColor.BLACK, new Position('f', 8)),
                new PieceImpl(pieceOrder.get(6), PieceColor.BLACK, new Position('g', 8)),
                new PieceImpl(pieceOrder.get(7), PieceColor.BLACK, new Position('h', 8))
        ));
    }

    private void addPawns() {
        pieces.addAll(Set.of(
                new PieceImpl(PieceType.Pawn, PieceColor.WHITE, new Position('a', 2)),
                new PieceImpl(PieceType.Pawn, PieceColor.WHITE, new Position('b', 2)),
                new PieceImpl(PieceType.Pawn, PieceColor.WHITE, new Position('c', 2)),
                new PieceImpl(PieceType.Pawn, PieceColor.WHITE, new Position('d', 2)),
                new PieceImpl(PieceType.Pawn, PieceColor.WHITE, new Position('e', 2)),
                new PieceImpl(PieceType.Pawn, PieceColor.WHITE, new Position('f', 2)),
                new PieceImpl(PieceType.Pawn, PieceColor.WHITE, new Position('g', 2)),
                new PieceImpl(PieceType.Pawn, PieceColor.WHITE, new Position('h', 2)),

                new PieceImpl(PieceType.Pawn, PieceColor.BLACK, new Position('a', 7)),
                new PieceImpl(PieceType.Pawn, PieceColor.BLACK, new Position('b', 7)),
                new PieceImpl(PieceType.Pawn, PieceColor.BLACK, new Position('c', 7)),
                new PieceImpl(PieceType.Pawn, PieceColor.BLACK, new Position('d', 7)),
                new PieceImpl(PieceType.Pawn, PieceColor.BLACK, new Position('e', 7)),
                new PieceImpl(PieceType.Pawn, PieceColor.BLACK, new Position('f', 7)),
                new PieceImpl(PieceType.Pawn, PieceColor.BLACK, new Position('g', 7)),
                new PieceImpl(PieceType.Pawn, PieceColor.BLACK, new Position('h', 7))
        ));
    }

    private boolean isKingBetweenRooks(ArrayList<PieceType> pieces) {

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

    private boolean areBishopsOnSameColor(ArrayList<PieceType> pieces) {
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
