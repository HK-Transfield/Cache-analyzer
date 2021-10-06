import java.util.Arrays;

class LLCEvaluator {
    final int RUN_TIMING = 50;
    final int KB = 1024;
    final int MB = 1024 * KB;

    /* hardcoded array sizes ranging from 1kb - 11mb */
    final int[] sizes = { 1 * KB, 4 * KB, 8 * KB, 16 * KB, 32 * KB, 64 * KB, 128 * KB, 256 * KB, 512 * KB, 1 * MB,
            2 * MB, 4 * MB, 8 * MB, 16 * MB, 32 * MB, 64 * MB, 128 * MB, 256 * MB };

    public void testLLC() {
        int steps = 64 * 1024 * 1024; // arbitrary large number of steps
        int lengthMod;
        long startTime, endTime, average;

        // the program creates a new array for eeach size defined
        for (int i = 0; i < sizes.length; i++) {
            byte[] arr = new byte[sizes[i]];
            Arrays.fill(arr, (byte) 1);
            average = 0;

            for (int j = 0; j <= 50; j++) {
                startTime = System.nanoTime();

                lengthMod = arr.length - 1;
                for (int s = 0; s < steps; s++) {
                    arr[(s * 64) & lengthMod]++;
                }
                endTime = System.nanoTime();

                average += endTime - startTime;
            }

            System.out.println((average / 50));
        }
    }
}

public class Measure {
    public static void main(String[] args) {
        LLCEvaluator eval = new LLCEvaluator();
        eval.testLLC();
    }
}
