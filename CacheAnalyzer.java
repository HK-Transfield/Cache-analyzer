import java.util.Arrays;
import java.util.Random;

/**
 * Simple algorithm used to infer the size of the last level cache. Inspired by
 * the algorithm described by Igor Otrosky
 * 
 * @author HK Transfield
 */
class CacheTimer {
    final int AVERAGE = 50;
    final int KB = 1024;
    final int MB = 1024 * KB;

    // exponential sizes from 1kB to 256MB
    final int[] sizes = { 1 * KB, 4 * KB, 8 * KB, 16 * KB, 32 * KB, 64 * KB, 128 * KB, 256 * KB, 512 * KB, 1 * MB,
            2 * MB, 4 * MB, 8 * MB, 16 * MB, 32 * MB, 64 * MB, 128 * MB, 256 * MB };

    // ALTERNATIVE CONFIGURATION: linear array sizes from 1MB to 20MB
    // final int[] sizes = { 1 * MB, 2 * MB, 3 * MB, 4 * MB, 5 * MB, 6 * MB, 7 * MB,
    // 8 * MB, 9 * MB, 10 * MB, 11 * MB,
    // 12 * MB, 13 * MB, 14 * MB, 15 * MB, 16 * MB, 17 * MB, 18 * MB, 19 * MB, 20 *
    // MB };

    /**
     * Measures the access time of Arrays in order to identify what the LLC is.
     */
    public void measureCache() {
        int steps = 64 * 1024 * 1024; // arbitrary large number of steps
        int lengthMod;
        long startTime, endTime, averageTime;
        // Random r = new Random(); // ALTERNATIVE CONFIGURATION: random array access

        System.out.println("Results (ns):");

        // the program creates a new array for each size defined
        for (int i = 0; i < sizes.length; i++) {
            byte[] arr = new byte[sizes[i]]; // create a brand new array for every size
            Arrays.fill(arr, (byte) 1);
            averageTime = 0;

            // run the loop 50 times for each size, since the cache can get noisy
            for (int j = 0; j <= AVERAGE; j++) {
                startTime = System.nanoTime();

                lengthMod = arr.length - 1;
                for (int s = 0; s < steps; s++) {
                    arr[(s * 64) & lengthMod]++; // tests random array access
                    // arr[(r.nextInt(steps) * 64) & lengthMod]++; // Random Access config
                }
                endTime = System.nanoTime();
                averageTime += endTime - startTime;
            }

            System.out.println((sizes[i] / KB) + "kB, " + (averageTime / AVERAGE));
        }
    }
}

public class CacheAnalyzer {
    public static void main(String[] args) {
        System.out.println("LLC Cache Analyzer, written by Harmon Transfield");
        System.out.println("------------------------------------------------");

        CacheTimer ct = new CacheTimer();
        ct.measureCache();
    }
}
