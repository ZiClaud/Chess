package Pieces;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Get PiecesOld images from Resources
 */
public class PieceImg {
    public static BufferedImage getPieceImg(Piece piece) {
        if (piece.getPieceColor() == PieceColor.WHITE) {
            if (piece.getPieceType() == PieceType.Pawn) {
                return getWhitePawn();
            } else if (piece.getPieceType() == PieceType.Knight) {
                return getWhiteKnight();
            } else if (piece.getPieceType() == PieceType.Bishop) {
                return getWhiteBishop();
            } else if (piece.getPieceType() == PieceType.Tower) {
                return getWhiteTower();
            } else if (piece.getPieceType() == PieceType.Queen) {
                return getWhiteQueen();
            } else if (piece.getPieceType() == PieceType.King) {
                return getWhiteKing();
            }
        } else if (piece.getPieceColor() == PieceColor.BLACK) {
            if (piece.getPieceType() == PieceType.Pawn) {
                return getBlackPawn();
            } else if (piece.getPieceType() == PieceType.Knight) {
                return getBlackKnight();
            } else if (piece.getPieceType() == PieceType.Bishop) {
                return getBlackBishop();
            } else if (piece.getPieceType() == PieceType.Tower) {
                return getBlackTower();
            } else if (piece.getPieceType() == PieceType.Queen) {
                return getBlackQueen();
            } else if (piece.getPieceType() == PieceType.King) {
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
