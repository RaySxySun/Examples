package concurrent.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ray on 17-12-26.
 */
public class TryLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public TryLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            while(true) {
                if(lock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e){}
                        if(lock2.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getId() + ": My job is done (1)");
                                return;
                            }finally {
                                lock2.unlock();
                            }
                        }else{
                            System.out.println("t1:failed to gain lock2");
                        }
                    }finally {
                        System.out.println("t1: release lock1");
                        lock1.unlock();
                    }
                }
            }
        } else {
            while(true) {
                if(lock2.tryLock()){
                    try {
                        try {
                            Thread.sleep(10);
                        }catch (InterruptedException e) {}
                        if(lock1.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getId() + ": My job is done (2)");
                                return;
                            }finally {
                                lock1.unlock();
                            }
                        }else {
                            System.out.println("t2:failed to gain lock1");
                        }
                    }finally {
                        System.out.println("t2: release lock2");
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        long start = System.currentTimeMillis();
        TryLock lock1 = new TryLock(1);
        TryLock lock2 = new TryLock(2);
        Thread t1 = new Thread(lock1);
        Thread t2 = new Thread(lock2);
        t1.start();t2.start();
        t1.join();t2.join();
        long end = System.currentTimeMillis();
        System.out.println("exec time elapsed：" + (end - start) + "ms");
    }
}
