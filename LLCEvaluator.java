import java.util.Arrays;

public class LLCEvaluator {
    final static int KB = 1024;
    final static int MB = 1024 * KB;
    static byte[] arr;

    /**
     * Arbitrarily allocates the large array to ensure that memory is backed
     */

    static void measureLLC() {
        int steps = 64 * 1024 * 1024;
        int mask = arr.length - 1;

        for (int i = 0; i < steps; i++) {
            arr[(i * 64) & mask]++;
        }
    }

    public static void main(String[] args) {
        long startTime, endTime;
        int[] sizes = { 1 * KB, 4 * KB, 8 * KB, 16 * KB, 32 * KB, 64 * KB, 128 * KB, 192 * KB, 256 * KB, 512 * KB, 1 * MB, 2 * MB,
                3 * MB, 4 * MB, 5 * MB, 6 * MB, 7 * MB, 8 * MB, 9 * MB, 10 * MB, 11 * MB };

        for (int i = 0; i < sizes.length; i++) {
            arr = new byte[sizes[i]];
            Arrays.fill(arr, (byte)1);
            
            startTime = System.nanoTime();
            measureLLC();
            endTime = System.nanoTime();

            System.out.println((endTime - startTime));
        }
    }
}