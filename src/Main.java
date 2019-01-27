public class Main {

    public static void main(String[] args) {

        RevisedRImage image = new RevisedRImage("me.jpg");
        image.convertToMultiColor(2);
        image.display();
    }
}
