import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RImage {

    private BufferedImage image;
    private int[][] colors;
    private short[][] RED;
    private short[][] GREEN;
    private short[][] BLUE;

    private final double CENTER = (double) 255 / 2;

    public RImage(String path) {
        try {
            this.image = ImageIO.read(new File(path));
            colors = new int[image.getHeight()][image.getWidth()];
            RED = new short[image.getHeight()][image.getWidth()];
            BLUE = new short[image.getHeight()][image.getWidth()];
            GREEN = new short[image.getHeight()][image.getWidth()];

            for (int r = 0; r < image.getHeight(); r++) {
                for (int c = 0; c < image.getWidth(); c++) {
                    Color color = new Color(image.getRGB(c, r));
                    RED[r][c] = (short) color.getRed();
                    BLUE[r][c] = (short) color.getBlue();
                    GREEN[r][c] = (short) color.getGreen();
                    colors[r][c] = image.getRGB(c, r);
                }
            }
        } catch (IOException e) {
            System.err.println("unknown IOException --traceback constructor");
        }
    }

    public short[][] getBWPixelGrid() {
        short[][] greyScale = new short[image.getHeight()][image.getWidth()];
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                greyScale[r][c] = (short) ((RED[r][c] + GREEN[r][c] + BLUE[r][c]) / 3);
            }
        }
        return greyScale;
    }

    public void setBWPixels(short[][] greyScale) {
        for (int r = 0; r < greyScale.length; r++) {
            for (int c = 0; c < greyScale[r].length; c++) {
                image.setRGB(c, r, new Color(greyScale[r][c], greyScale[r][c], greyScale[r][c]).getRGB());
            }
        }
    }

    public int[][] getColorPixelGrid() {
        int[][] colorPixelGrid = new int[image.getHeight()][image.getWidth()];
        for (int r = 0; r < colorPixelGrid.length; r++) {
            for (int c = 0; c < colorPixelGrid[r].length; c++) {
                colorPixelGrid[r][c] = image.getRGB(c, r);
            }
        }
        return colorPixelGrid;
    }

    public void setColorPixelGrid(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                image.setRGB(c, r, grid[r][c]);
            }
        }
    }

    public short[][] getPolychrome(int shades) { //returns polychrome greyscale short[][]
        ArrayList<Integer> ranges = new ArrayList<>();
        for (int i = 0; i <= 255; i += (255 / shades)) {
            ranges.add(i);
        }
        ranges.add(255);
        short[][] bwpixels = getBWPixelGrid();
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                for (int i = 0; i < ranges.size() - 1; i++) {
                    if (bwpixels[r][c] >= ranges.get(i) && bwpixels[r][c] < ranges.get(i + 1)) {
                        bwpixels[r][c] = (short) (int) ranges.get(i);
                    }
                }
            }
        }
        return bwpixels;
    }

    public int[][] getMulticolored(int colors) {
        int[][] multicolored = new int[image.getHeight()][image.getWidth()];
        ArrayList<Integer> ranges = new ArrayList<>();
        for (int i = 0; i <= 255; i += (255 / colors)) {
            ranges.add(i);
        }
        ranges.add(255);
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {

                short red = 0;
                short blue = 0;
                short green = 0;

                for (int i = 0; i < ranges.size() - 1; i++) {
                    if (RED[r][c] > ranges.get(i) && RED[r][c] < ranges.get(i + 1)) {
                        red = (short) (int) ranges.get(i);
                    }
                }

                for (int i = 0; i < ranges.size() - 1; i++) {
                    if (BLUE[r][c] > ranges.get(i) && BLUE[r][c] < ranges.get(i + 1)) {
                        blue = (short) (int) ranges.get(i);
                    }
                }

                for (int i = 0; i < ranges.size() - 1; i++) {
                    if (GREEN[r][c] > ranges.get(i) && GREEN[r][c] < ranges.get(i + 1)) {
                        green = (short) (int) ranges.get(i);
                    }
                }

                multicolored[r][c] = new Color(red, green, blue).getRGB();
            }
        }
        return multicolored;
    }

    public short[][] getNegativePixelGrid() {
        short[][] bwpixels = getBWPixelGrid();
        for (int r = 0; r < bwpixels.length; r++) {
            for (int c = 0; c < bwpixels[r].length; c++) {
                bwpixels[r][c] = (short) (255 - bwpixels[r][c]);
            }
        }
        return bwpixels;
    }

    public int[][] getInvertedPixelGrid() {
        int[][] pixels = getColorPixelGrid();
        for (int r = 0; r < pixels.length; r++) {
            for (int c = 0; c < pixels[r].length; c++) {
                Color color = new Color(image.getRGB(c, r));
                int red = 255 - color.getRed();
                int blue = 255 - color.getBlue();
                int green = 255 - color.getGreen();
                pixels[r][c] = new Color(red, blue, green).getRGB();
            }
        }
        return pixels;
    }

    public int[][] getBGRGrid() {
        int[][] switched = new int[image.getHeight()][image.getWidth()];
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                Color color = new Color(image.getRGB(c, r));
                Color newColor = new Color(color.getBlue(), color.getGreen(), color.getRed());
                switched[r][c] = newColor.getRGB();
            }
        }
        return switched;
    }

    public int[][] getEmbossed(double increase) {
        double difference;
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                difference = RED[r][c] - CENTER;
                difference *= increase;
                RED[r][c] = (short) (CENTER + difference);
                difference = BLUE[r][c] - CENTER;
                difference *= increase;
                BLUE[r][c] = (short) (CENTER + difference);
                difference = GREEN[r][c] - CENTER;
                difference *= increase;
                GREEN[r][c] = (short) (CENTER + difference);
            }
        }
    }

    private int[][] recombine

    public void display() {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setVisible(true);
    }
}
