public class Main {

    public static void main(String[] args) {

        RImage image = new RImage("openFieldTest.jpg");
        image.loadKernel(new short[][]{
                {-1, -1, -1},
                {-1, 8, -1},
                {-1, -1, -1}
        });
        image.convolve(false);
        image.display();
    }
}
