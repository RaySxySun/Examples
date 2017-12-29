package concurrent.java8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock内部实现使用类似CAS操作的死循环反复尝试的策略。　它是使用Unsafe.park()挂起，直接返回。
 * 在其逻辑中没有处理中断的逻辑。　因此par()上的线程被中断后会再次进入死循环。疯狂占用CPU。
 */
public class Bug_StampedLockCPU {
    static Thread[] holdCpuThread = new Thread[3];
    static final StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException{
       new Thread() {
           public void run() {
               long readLong = lock.writeLock();
               LockSupport.parkNanos(600000000000L);
               lock.unlockWrite(readLong);
           }
       }.start();
       Thread.sleep(100);
        for (int i = 0; i < 3; i++) {
            holdCpuThread[i] = new Thread(new HoldCPUReadThread());
            holdCpuThread[i].start();
        }
        Thread.sleep(1000);
        /*
         * "Thread-3" #13 prio=5 os_prio=0 tid=0x00007ff93c38e000 nid=0x1a82 runnable [0x00007ff91d1f8000]
         * java.lang.Thread.State: RUNNABLE
	     * at sun.misc.Unsafe.park(Native Method)
         */
        for (int i = 0; i < 3; i++) {
            holdCpuThread[i].interrupt();
        }
    }

    private static class HoldCPUReadThread implements Runnable {

        @Override
        public void run() {
            long lockr = lock.readLock();
            System.out.println(Thread.currentThread().getName() + " gains read lock");
            lock.unlockRead(lockr);
        }
    }

}
