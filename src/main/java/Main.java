import Board.WindowBoard;
import BoardPieces.BoardConnectPieces;

// TODO: Template Method for placing pieces -> Changes posX and posY of piece, makes panel where the piece used to stand invisible, draws piece in new position, takes piece if it stands there, check the move is a legal move
// TODO: Factory method for pieces (Piece, BlackKing, ...)
public class Main {
    public static void main(String[] args) {
        // Board
        WindowBoard windowBoard = new WindowBoard(600, 600);

        // Pieces in board
        BoardConnectPieces boardPieces = new BoardConnectPieces(windowBoard);

        boardPieces.drawPiecesOnBoard();

        // Matrix board - Board v2? IDK what it is for, maybe testing, maybe I should work on this board, and what is displayed here will go in Board (aka graphfic)
//        PieceStartingPosition pieceStartingPosition = new PieceStartingPosition();
//        pieceStartingPosition.startingMatrixBoard();

        // Utils for tests
//        MatrixUtils.readMatrixBoard(pieceStartingPosition.getMatrixPieces());
//        MatrixUtils.readMatrixBoard(pieceStartingPosition.getMatrixPieces(), 'a', 1);


//        MatrixUtils.readMatrixBoard(windowBoard.getMatrixPanels());
    }
}
