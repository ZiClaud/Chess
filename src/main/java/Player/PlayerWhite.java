package Player;

import Pieces.PieceColor;

public class PlayerWhite extends Player {
    protected static Player instance;

    private PlayerWhite() {
        super.turn = true;
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new PlayerWhite();
        }
        return instance;
    }

    @Override
    public PieceColor getColor() {
        return PieceColor.WHITE;
    }
}
