package concurrent.cas;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by ray on 17-12-29.
 */
public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    public static class newThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                arr.getAndIncrement(i % arr.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread[] ts = new Thread[9];
        for (int i = 0; i < 9; i++) {
            ts[i] = new Thread(new newThread());
        }

        for (int i = 0; i < 9; i++) {
            ts[i].start();
        }

        for (int i = 0; i < 9; i++) {
            ts[i].join();
        }

        System.out.println(arr);

    }
}
