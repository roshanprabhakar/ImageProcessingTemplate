public class Main {

    public static String path = "chewy.jpg";;

    public static void main(String[] args) {
        RImage image = new RImage(path);
        int[][] clockwise = image.getMulticolored(10);
        image.setColorPixelGrid(clockwise);
        image.display();
    }
}
