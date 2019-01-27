public class ConvolutionFilter {

    private final short[][] kernel;
    private int weight;
    private int totalBits;

    public int getWeight() {
        return weight;
    }

    public short[][] getKernel() {
        return kernel;
    }

    public ConvolutionFilter(short[][] kernel) {
        this.kernel = kernel;
        totalBits = 0;

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

    @SuppressWarnings("Duplicates")
    public int[][] convolve(int[][] img) {
        int[][] copy = new int[img.length][img[0].length];
        for (int r = 0; r < img.length - kernel.length; r++) {
            for (int c = 0; c < img[r].length - kernel.length; c++) {
                int sum = 0;
                for (int i = 0; i < kernel.length; i++) {
                    for (int j = 0; j < kernel[i].length; j++) {
                        sum += img[r + i][c + j] * kernel[i][j];
                    }
                }
                sum /= totalBits;
                if (sum < 0) sum = 0;
                System.out.println(sum);
                copy[r][c] = sum;
            }
        }
        return copy;
    }

    @SuppressWarnings("Duplicates")
    public short[][] convolve(short[][] img) {
        short[][] copy = new short[img.length][img[0].length];
        for (int r = 0; r < img.length - kernel.length; r++) {
            for (int c = 0; c < img[r].length - kernel.length; c++) {
                int sum = 0;
                for (int i = 0; i < kernel.length; i++) {
                    for (int j = 0; j < kernel[i].length; j++) {
                        sum += img[r + i][c + j] * kernel[i][j];
                    }
                }
                sum /= totalBits;
                if (sum < 0) sum = 0;
                System.out.println(sum);
                copy[r][c] = (short) sum;
            }
        }
        return copy;
    }


    @SuppressWarnings("Duplicates")
    private short[][] deepClone(short[][] img) {
        short[][] out = img.clone();
        for (int r = 0; r < img.length; r++) {
            out[r] = img[r].clone();
        }
        return out;
    }
}