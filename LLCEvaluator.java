import java.util.Arrays;
import java.util.Random;

public class LLCEvaluator {
    final int KB = 1024;
    final int MB = 1024 * KB;
    //static byte[] arr;

    /* hardcoded array sizes ranging from 1kb - 11mb */
    final int[] sizes = { 1 * KB, 4 * KB, 8 * KB, 16 * KB, 32 * KB, 64 * KB, 128 * KB, 192 * KB, 256 * KB, 512 * KB, 
        1 * MB, 2 * MB, 3 * MB, 4 * MB, 5 * MB, 6 * MB, 7 * MB, 8 * MB, 9 * MB, 10 * MB, 11 * MB, 12 * MB, 13 * MB,
        14 * MB, 15 * MB};
    
    public void testLLC() {
        int steps = 64 * 1024 * 1024; // arbitrary large number of steps
        int lengthMod;
        long startTime, endTime;
        Random rnd = new Random();

        for (int i = 0; i < sizes.length; i++) {
            byte[] arr = new byte[sizes[i]];

            startTime = System.nanoTime();
            Arrays.fill(arr, (byte) 1);

            lengthMod = arr.length - 1;
            
            // increment a random 64th byte to modify the cache line. 
            // if it passes the last value if will loop back to the
            // beginning
            for (int s = 0; s < steps; s++) {
                arr[(rnd.nextInt() * 64) & lengthMod]++;
            }
            endTime = System.nanoTime();

            System.out.println((endTime - startTime));
        }
    }
}