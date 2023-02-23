package Rules;

import Game.Game;
import Player.Player;

public class TurnRules {
    public static void switchTurn() {
        Player whitePlayer = Game.whitePlayer;
        Player blackPlayer = Game.blackPlayer;

        whitePlayer.setTurn(!whitePlayer.isTurn());
        blackPlayer.setTurn(!whitePlayer.isTurn());
    }
}
