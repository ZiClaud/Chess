package Pieces;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Get Pieces images from Resources
 */
public class PieceImg {
    public static BufferedImage getVoid() {
        return new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
    }

    public static BufferedImage getWhitePawn() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/WP.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getWhiteKnight() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/WN.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getWhiteBishop() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/WB.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getWhiteTower() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/WT.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getWhiteQueen() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/WQ.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getWhiteKing() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/WK.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getBlackPawn() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/BP.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getBlackKnight() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/BN.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getBlackBishop() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/BB.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getBlackTower() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/BT.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getBlackQueen() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/BQ.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getBlackKing() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/BK.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
