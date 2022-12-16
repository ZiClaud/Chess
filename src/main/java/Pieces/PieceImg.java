package Pieces;

import javax.imageio.ImageIO;
import java.awt.*;
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

    public static BufferedImage getPieceImg(Piece p){
        if (p.getPieceColor() == Color.WHITE){
            if (p.getPieceType() == PieceType.Pawn){
                return getWhitePawn();
            } else if (p.getPieceType() == PieceType.Knight) {
                return getWhiteKnight();
            } else if (p.getPieceType() == PieceType.Bishop) {
                return getWhiteBishop();
            } else if (p.getPieceType() == PieceType.Tower) {
                return getWhiteTower();
            } else if (p.getPieceType() == PieceType.Queen) {
                return getWhiteQueen();
            } else if (p.getPieceType() == PieceType.King) {
                return getWhiteKing();
            }
        } else {
            if (p.getPieceType() == PieceType.Pawn){
                return getBlackPawn();
            } else if (p.getPieceType() == PieceType.Knight) {
                return getBlackKnight();
            } else if (p.getPieceType() == PieceType.Bishop) {
                return getBlackBishop();
            } else if (p.getPieceType() == PieceType.Tower) {
                return getBlackTower();
            } else if (p.getPieceType() == PieceType.Queen) {
                return getBlackQueen();
            } else if (p.getPieceType() == PieceType.King) {
                return getBlackKing();
            }
        }
        System.err.println("Piece that's not a pawn, knight, bishop, tower, queen and king? What is this? Checkers?");
        assert (false);
        return null;
    }

    private static BufferedImage getWhitePawn() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/WP.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getWhiteKnight() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/WN.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getWhiteBishop() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/WB.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getWhiteTower() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/WT.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getWhiteQueen() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/WQ.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getWhiteKing() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/WK.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getBlackPawn() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/BP.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getBlackKnight() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/BN.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getBlackBishop() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/BB.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getBlackTower() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/BT.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getBlackQueen() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/BQ.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage getBlackKing() {
        try {
            return ImageIO.read(new File("src/main/resources/Imgs/BK.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
