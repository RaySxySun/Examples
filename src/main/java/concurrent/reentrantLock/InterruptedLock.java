package concurrent.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ray on 17-12-26.
 */
public class InterruptedLock implements Runnable{
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public InterruptedLock(int lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                lock1.lock();
                try {
                    System.out.println("t1 is sleeping");
                    Thread.sleep(500);
                }catch (InterruptedException e){}
                lock2.lockInterruptibly();
                System.out.println("t1 is completed");
            } else {
                lock2.lockInterruptibly();
                try {
                    System.out.println("t1 is sleeping");
                    Thread.sleep(500);
                }catch (InterruptedException e){}
                lock1.lockInterruptibly();
                System.out.println("t2 is completed");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ": exit current thread " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptedLock r1 = new InterruptedLock(1);
        InterruptedLock r2 = new InterruptedLock(2);
        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");
        t1.start();t2.start();
        Thread.sleep(9000);
        t2.interrupt();
    }
}
