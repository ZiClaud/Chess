package BoardPieces;

import Board.Board;
import Pieces.Piece;
import Pieces.PieceInt;

import java.util.ArrayList;

public class BoardConnectPieces {
    private final Board board;
    private ArrayList<PieceInt> pieces;

    public BoardConnectPieces(Board board, ArrayList<PieceInt> pieces) {
        this.board = board;
        this.pieces = pieces;
    }
}
