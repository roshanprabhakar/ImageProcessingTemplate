public class Main {

    public static String path = "test.jpg";;

    public static void main(String[] args) {
        RImage image = new RImage(path);

        image.setBWPixels(image.getBWEmbossed(3, 255/2));

        image.display();
    }
}
