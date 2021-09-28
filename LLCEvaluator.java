import java.util.Arrays;
import java.util.Random;

public class LLCEvaluator {
    final int KB = 1024;
    final int MB = 1024 * KB;
    // static byte[] arr;

    /* hardcoded array sizes ranging from 1kb - 11mb */
    final int[] sizes = { 1 * KB, 4 * KB, 8 * KB, 16 * KB, 32 * KB, 64 * KB, 128 * KB, 192 * KB, 256 * KB, 512 * KB,
            1 * MB, 2 * MB, 3 * MB, 4 * MB, 8 * MB, 16 * MB, 32 * MB, 64 * MB, 128 * MB };

    public void testLLC() {
        int steps = 64 * 1024 * 1024; // arbitrary large number of steps
        byte[] arr = new byte[256 * 1024 * 1024];
        int lengthMod;
        long startTime, endTime;
        Random rnd = new Random();

        Arrays.fill(arr, (byte) 1);

        // the program creates a new array for eeach size defined
        for (int i = 0; i < sizes.length; i++) {
            lengthMod = sizes[i] - 1;

            /*
             * Begin recording access times for each array size we are evaluating. It will
             * the array an arbitrary number of steps.
             */
            startTime = System.nanoTime();

            // increment a random 64th byte to modify the cache line.
            // if it passes the last value if will loop back to the
            // beginning
            for (int s = 0; s < steps; s++) {
                arr[(s * 64) & lengthMod]++;
                // arr[(rnd.nextInt() * 64) & lengthMod]++;
            }
            endTime = System.nanoTime();

            System.out.println(((endTime - startTime) / 1000000));
        }
    }
}