package Rules;

import Game.Game;
import Pieces.Piece;
import Player.Player;

import java.util.HashSet;

public class TurnRules {
    public static void getTurn(HashSet<Piece> pieces) {
        Player whitePlayer = Game.whitePlayer;
        Player blackPlayer = Game.blackPlayer;

        if (whitePlayer.isTurn()) {
            for (Piece piece : pieces) {
//                piece.setTurn(piece.getPieceColor() == PieceColor.WHITE);
//                PawnRules.updateWhitePawnsPreviousPosition(pieces);
            }
        } else if (blackPlayer.isTurn()) {
            for (Piece piece : pieces) {
//                piece.setTurn(piece.getPieceColor() == PieceColor.BLACK);
//                PawnRules.updateBlackPawnsPreviousPosition(pieces);
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
