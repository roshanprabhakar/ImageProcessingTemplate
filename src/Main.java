public class Main {

    public static String path = "chewy.jpg";;

    public static void main(String[] args) {
        RImage image = new RImage(path);
        int[][] imageMulticolored = image.getMulticolored(2);
        image.setColorPixelGrid(imageMulticolored);
        image.display();
    }
}
