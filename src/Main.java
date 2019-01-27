public class Main {

    public static void main(String[] args) {
        RImage image = new RImage("me.jpg");
        image.convertToMultiColor(2);
        image.display();
    }
}
