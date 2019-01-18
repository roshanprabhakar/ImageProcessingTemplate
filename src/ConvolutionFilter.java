public class ConvolutionFilter {
    int[][] kernel;

    public ConvolutionFilter(int[][] kernel) {
        this.kernel = kernel;
    }

    public void convolve(int[][] image) {
        int[][] convolved = new int[image.length][image[0].length];
        for (int r = 0; r < image.length - kernel.length; r++) {
            for (int c = 0; c < image[0].length - kernel[0].length; c++) {
                int sum = 0;
                int count = 0;
                for (int i = 0; i < kernel.length; i++) {
                    for (int j = 0; j < kernel[i].length; j++) {
                        sum += kernel[i][j] * image[r][c];
                        count++;
                    }
                }
                if (sum < 0) sum = 0;
                convolved[r][c] = sum / 
             }
        }
    }
}
