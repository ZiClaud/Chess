package Player;

import Pieces.PieceColor;

public class PlayerBlack extends Player {
    protected static Player instance;

    private PlayerBlack() {
        super.turn = false;
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new PlayerBlack();
        }
        return instance;
    }

    @Override
    public PieceColor getColor() {
        return PieceColor.BLACK;
    }
}
