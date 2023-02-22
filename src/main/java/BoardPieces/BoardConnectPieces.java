package BoardPieces;

import Board.WindowBoard;
import Pieces.*;

import java.util.HashSet;

/**
 * Class that connects Board with PiecesOld
 * Draws the PiecesOld into the Board
 */
public class BoardConnectPieces {
    private static BoardConnectPieces instance;
    private final HashSet<Piece> pieces = new HashSet<>();

    private BoardConnectPieces() {
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

    public HashSet<Piece> getPieces() {
        return pieces;
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
