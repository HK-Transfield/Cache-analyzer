import java.util.Arrays;
import java.util.Random;

public class LLCEvaluator {
    final static int KB = 1024;
    final static int MB = 1024 * KB;
    static byte[] arr;
    static int[] sizes = { 1 * KB, 4 * KB, 8 * KB, 16 * KB, 32 * KB, 64 * KB, 128 * KB, 256 * KB, 512 * KB, 1 * MB,
            2 * MB, 3 * MB, 4 * MB, 5 * MB, 6 * MB, 7 * MB, 8 * MB };

    public static void main(String[] args) {
        int steps = 64 * 1024 * 1024;
        int lengthMod;
        long startTime, endTime;
        Random rnd = new Random();

        for (int i = 0; i < sizes.length; i++) {
            arr = new byte[sizes[i]];

            startTime = System.nanoTime();
            Arrays.fill(arr, (byte) 1);

            lengthMod = arr.length - 1;

            for (int s = 0; s < steps; s++) {
                arr[(rnd.nextInt(s + 1) * 64) & lengthMod]++;
            }
            endTime = System.nanoTime();

            System.out.println((endTime - startTime));
        }
    }
}