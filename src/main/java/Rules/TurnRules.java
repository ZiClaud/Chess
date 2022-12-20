package Rules;

import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Pieces.PieceColor;
import Player.Player;
import Game.Game;

public class TurnRules {
    public static void getTurn(BoardConnectPieces boardConnectPieces) {
        Player whitePlayer = Game.whitePlayer;
        Player blackPlayer = Game.blackPlayer;

        if (whitePlayer.isTurn()) {
            for (Piece piece : boardConnectPieces.getPieces()) {
                piece.setTurn(piece.getPieceColor() == PieceColor.WHITE);
            }
        } else if (blackPlayer.isTurn()) {
            for (Piece piece : boardConnectPieces.getPieces()) {
                piece.setTurn(piece.getPieceColor() == PieceColor.BLACK);
            }
        }
    }

    public static void switchTurn() {
        Player whitePlayer = Game.whitePlayer;
        Player blackPlayer = Game.blackPlayer;

        whitePlayer.setTurn(!whitePlayer.isTurn());
        blackPlayer.setTurn(!whitePlayer.isTurn());
    }
}
