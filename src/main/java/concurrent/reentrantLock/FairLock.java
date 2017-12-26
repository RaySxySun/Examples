package concurrent.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ray on 17-12-26.
 */
public class FairLock implements Runnable {
    public static ReentrantLock fairLock = new ReentrantLock(true);
    //public static ReentrantLock unfairLock = new ReentrantLock(false);

    @Override
    public void run() {
       while(true) {
           try {
               fairLock.lock();
               System.out.println(Thread.currentThread().getName() + " gains the lock");
           }finally {
               fairLock.unlock();
           }
       }
    }

    public static void main(String[] args) throws InterruptedException {
        FairLock fl = new FairLock();
        Thread t1 = new Thread(fl, "Thread t1");
        Thread t2 = new Thread(fl, "Thread t2");
        t1.start();t2.start();
    }
}
