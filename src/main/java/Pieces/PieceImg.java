package Pieces;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Get Pieces images from Resources
 */
public class PieceImg {
    public static BufferedImage getWhitePawn() {
        try {
            return ImageIO.read(new File("resources/Imgs/WP.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getWhiteKnight() {
        try {
            return ImageIO.read(new File("resources/Imgs/WKn.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getWhiteBishop() {
        try {
            return ImageIO.read(new File("resources/Imgs/WB.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getWhiteTower() {
        try {
            return ImageIO.read(new File("resources/Imgs/WT.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getWhiteQueen() {
        try {
            return ImageIO.read(new File("resources/Imgs/WQ.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getWhiteKing() {
        try {
            return ImageIO.read(new File("resources/Imgs/WKi.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getBlackPawn() {
        try {
            return ImageIO.read(new File("resources/Imgs/BP.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getBlackKnight() {
        try {
            return ImageIO.read(new File("resources/Imgs/BKn.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getBlackBishop() {
        try {
            return ImageIO.read(new File("resources/Imgs/BB.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getBlackTower() {
        try {
            return ImageIO.read(new File("resources/Imgs/BT.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getBlackQueen() {
        try {
            return ImageIO.read(new File("resources/Imgs/BQ.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getBlackKing() {
        try {
            return ImageIO.read(new File("resources/Imgs/BKi.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
