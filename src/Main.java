public class Main {

    public static void main(String[] args) {

        MutableImage mi = new MutableImage("testImage.jpg");
        mi.setImage(mi.getBWPixelGrid());
        mi.display();

    }
}
