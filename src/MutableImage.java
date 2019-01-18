import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MutableImage {

    BufferedImage image;

    public MutableImage(String filepath) {
        try {
            image = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            System.out.println("IOException encountered while attempting to extract image from " + filepath);
        }
    }

    public int[][] getRGBPixelsGrid() {
        int[][] pixels = new int[image.getHeight()][image.getWidth()];
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                pixels[r][c] = image.getRGB(c, r);
            }
        }
        return pixels;
    }

    public int[][] getBWPixelGrid() {
        int[][] bwPixelGrid = new int[image.getHeight()][image.getWidth()];
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                int rgb = image.getRGB(c, r);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb & 0xFF);
                int greyLevel = (red + green + blue) / 3;
                int grey = (greyLevel << 16) + (greyLevel << 8) + greyLevel;
                bwPixelGrid[r][c] = grey;
            }
        }
        return bwPixelGrid;
    }

    public void setImage(int[][] pixelGrid) {
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                image.setRGB(c, r, pixelGrid[r][c]);
            }
        }
    }

    public void display() {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setVisible(true);
    }
}