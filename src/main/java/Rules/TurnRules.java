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
                PawnRules.updateWhitePawnsPreviousPosition(boardConnectPieces);
            }
        } else if (blackPlayer.isTurn()) {
            for (Piece piece : boardConnectPieces.getPieces()) {
                piece.setTurn(piece.getPieceColor() == PieceColor.BLACK);
                PawnRules.updateBlackPawnsPreviousPosition(boardConnectPieces);
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
