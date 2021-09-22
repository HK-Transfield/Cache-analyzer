public class LLCEvaluator {
    final static int KB = 1024;
    final static int MB = 1024 * KB;
    static byte[] arr;

    /**
     * Arbitrarily allocates the large array to ensure that memory is backed
     */
    static void initArray() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (byte) 1;
        }
    }

    static void measureLLC() {
        int steps = 64 * 1024 * 1024;
        int lengthMod = arr.length - 1;

        for (int i = 0; i < steps; i++) {
            arr[(i * 64) & lengthMod]++;
        }
    }

    public static void main(String[] args) {
        long startTime, endTime;
        int[] sizes = { 1 * KB, 4 * KB, 8 * KB, 16 * KB, 32 * KB, 64 * KB, 128 * KB, 256 * KB, 512 * KB, 1 * MB, 2 * MB,
                3 * MB, 4 * MB, 5 * MB, 6 * MB, };

        for (int i = 0; i < sizes.length; i++) {
            arr = new byte[sizes[i]];

            startTime = System.nanoTime();
            initArray();
            measureLLC();
            endTime = System.nanoTime();

            System.out.println((endTime - startTime));
        }
    }
}