package Rules;

import BoardPieces.BoardConnectPieces;
import Pieces.Piece;
import Player.Game;
import Player.Player;

import java.awt.*;

public class TurnRules {
    public static void getTurn(BoardConnectPieces boardConnectPieces) {
        Player whitePlayer = Game.white;
        Player blackPlayer = Game.black;

        if (whitePlayer.isTurn()) {
            for (Piece piece : boardConnectPieces.getPieces()) {
                piece.setTurn(piece.getPieceColor() == Color.WHITE);
            }
        } else if (blackPlayer.isTurn()) {
            for (Piece piece : boardConnectPieces.getPieces()) {
                piece.setTurn(piece.getPieceColor() == Color.BLACK);
            }
        }
    }

    public static void switchTurn() {
        Player whitePlayer = Game.white;
        Player blackPlayer = Game.black;

        whitePlayer.setTurn(!whitePlayer.isTurn());
        blackPlayer.setTurn(!whitePlayer.isTurn());
    }
}
