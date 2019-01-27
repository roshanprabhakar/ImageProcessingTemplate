public class Main {

    public static void main(String[] args) {
        RImage image = new RImage("me.jpg");
        int[][] maximized = image.getMaximizedColorPixelGrid();
        image.setColorPixelGrid(maximized);
        image.display();
    }
}
