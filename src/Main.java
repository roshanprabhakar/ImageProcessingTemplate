public class Main {

    public static String path = "chewy.jpg";;

    public static void main(String[] args) {
        RImage image = new RImage(path);
        int[][] clockwise = image.getEmbossed(0.1);
        image.setColorPixelGrid(clockwise);
        image.display();
    }
}
