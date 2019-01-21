public class Main {

    public static void main(String[] args) {

        MutableImage mi = new MutableImage("openFieldTest.jpg");
        mi.setImage(mi.getBWPixelGrid());
        mi.display();

    }
}
