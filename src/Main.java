public class Main {

    public static String path = "test.jpg";;

    public static void main(String[] args) {
        RImage image = new RImage(path);
        RImage borderedImage = new RImage(image.addWhiteBorder(10, image.getBWEmbossed(3, 255/2)));
        borderedImage.writeToFile(path);
    }
}
