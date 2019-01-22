import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;


public class RImage {

    private ConvolutionFilter k;
    private BufferedImage image;
    private String path;

    public RImage(String filepath) {
        loadImage(filepath);
    }

    public RImage(String filepath, short[][] kernel) {
        loadImage(filepath);
        this.k = new ConvolutionFilter(kernel);
    }

    public int[][] convolve(boolean willReturn) {
        try {
            int[][] pixels = k.convolve(getBWPixelGrid());
            if (willReturn) {
                return pixels;
            } else {
                this.setImage(pixels);
                return null;
            }
        } catch (NullPointerException e) {
            System.err.println("please load a convolution filter");
            return null;
        }
    }

    public void loadKernel(short[][] kernel) {
        this.k = new ConvolutionFilter(kernel);
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

        BufferedImage copy;
        int[][] bwpixelgrid = new int[image.getHeight()][image.getWidth()];

        try {
            copy = ImageIO.read(new File(path));

            for (int r = 0; r < copy.getHeight(); r++) {
                for (int c = 0; c < copy.getWidth(); c++) {

                    Color color = new Color(image.getRGB(c, r));
                    int red = (int) (color.getRed() * 0.299);
                    int green = (int) (color.getGreen() * 0.587);
                    int blue = (int) (color.getBlue() * 0.114);
                    Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
                    copy.setRGB(c, r, newColor.getRGB());
                }
            }

            for (int r = 0; r < copy.getHeight(); r++) {
                for (int c = 0; c < copy.getWidth(); c++) {
                    bwpixelgrid[r][c] = copy.getRGB(c, r);
                }
            }

            return bwpixelgrid;

        } catch (Exception e) {
            System.out.println("could not find image from path! --traceback getBWPixelGrid");
            return null;
        }
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

    //HELPERS
    private void loadImage(String filepath) {
        try {
            image = ImageIO.read(new File(filepath));
            this.path = filepath;
        } catch (IOException e) {
            System.out.println("IOException encountered while attempting to extract image from " + filepath);
        }
    }


}