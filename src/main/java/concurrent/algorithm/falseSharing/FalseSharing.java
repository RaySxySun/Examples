package concurrent.algorithm.falseSharing;

import concurrent.algorithm.ElegentSingleton;
import sun.font.FontRunIterator;

/**
 * padding for CPU cache hit rate
 */
public class FalseSharing implements Runnable {
    public final static int NUM_THREADS = 2;
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    private final static class VolatileLong {
        public volatile long value = 0L;
        // padding for better CPU cache hit rate
        // can be commented out for testing
        public long p1, p2, p3, p4, p5, p6, p7;
    }

//    // the approach in jdk 8. it can resolve false sharing, increase cpu cache hit rate
//    // requres: -XX:-RestrictContened
//    @sun.misc.Contended
//    private final static class VolatileLong {
//        public volatile long value = 0L;
//    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    public static void main(final String[] args) throws Exception {
        final long start = System.currentTimeMillis();
        runTest();
        System.out.println("Duration = " + (System.currentTimeMillis() - start));
    }
}
