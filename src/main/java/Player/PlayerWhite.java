package Player;

import Pieces.PieceColor;

public class PlayerWhite extends Player{

    public PlayerWhite() {
        super.color = PieceColor.WHITE;
        super.turn = true;
    }
}
