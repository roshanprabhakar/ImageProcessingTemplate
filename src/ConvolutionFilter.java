public class ConvolutionFilter {

    private short[][] kernel;
    private int weight;
    private int totalBits;

    public int getWeight() {
        return weight;
    }

    public short[][] getKernel() {
        return kernel;
    }

    public int getTotalBits() {
        return totalBits;
    }

    public ConvolutionFilter(short[][] kernel) {
        this.kernel = kernel;

        int sum = 0;

        for (int r = 0; r < kernel.length; r++) {
            for (int c = 0; c < kernel[r].length; c++) {
                sum += kernel[r][c];
                totalBits++;
            }
        }

        if (sum == 0) sum++;
        this.weight = sum;
    }

    public int[][] convolve(int[][] img) {
        int[][] copy = deepClone(img);
        for (int r = 0; r < img.length - kernel.length; r++) {
            for (int c = 0; c < img[r].length - kernel.length; c++) {
//                int sum = 0;
//                for (int i = 0; i < kernel.length; i++) {
//                    for (int j = 0; j < kernel[i].length; j++) {
//                        sum = img[r + i][c + j] * kernel[i][j];
//                    }
//                }
//
//                sum /= totalBits;
//                if (sum < 0)  sum = 0;
//                copy[r][c] = sum;
                copy[r][c] = 250;
            }
        }
        return copy;
    }

    private int[][] deepClone(int[][] img) {
        int[][] out = img.clone();
        for (int r = 0; r < img.length; r++) {
            out[r] = img[r].clone();
        }
        return out;
    }
}