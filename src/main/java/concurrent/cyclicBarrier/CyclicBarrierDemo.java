package concurrent.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by ray on 17-12-26.
 */
public class CyclicBarrierDemo {
    public static class Soldier implements Runnable {
        private String soldier;
        private final CyclicBarrier cyclic;

        public Soldier(CyclicBarrier cyclic, String soldier) {
            this.soldier = soldier;
            this.cyclic = cyclic;
        }

        @Override
        public void run() {
         try {
             cyclic.await();
             doWork();
             cyclic.await();
         } catch (InterruptedException e) {
             e.printStackTrace();
         } catch (BrokenBarrierException e) {
             e.printStackTrace();
         }
        }

        void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + ": mission is completed");
        }
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("commander: [ " + N + " soldiers' missions are completed!]");
            }else {
                System.out.println("commander: [ " + N + " soldiers have gathered!]");
                flag = true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int N = 10;
        Thread[] allSoldiers = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("assembled");
        for (int i = 0; i < N; i++) {
            System.out.println("soldier " + i + "reported");
            allSoldiers[i] = new Thread(new Soldier(cyclic, "soldier" + i));
            allSoldiers[i].start();
            /* check Exceptions: InterruptedException & BrokenBarrierException
             * Enabling the blow line will cause 1 InterruptedException and 9 BrokenBarrierException
             */
            //  if (i ==5 ) allSoldiers[i].interrupt();
        }
    }
}
