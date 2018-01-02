package concurrent.debug;

import concurrent.cas.AtomicIntegerDemo;

import java.util.ArrayList;

/**
 * Created by ray on 17-12-31.
 */
public class UnsafeArrayList {
    static ArrayList al = new ArrayList(10);

    static class AddTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 1000000; i++) {
                al.add(new Object());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new AddTask());
        Thread t2 = new Thread(new AddTask());
        t1.start();
        t2.start();
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t3");
        t3.start();
    }
}
