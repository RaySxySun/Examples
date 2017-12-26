package concurrent.lockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport supports interrupt
 * it can be checked by using the method Thread.interrupted();
 */
public class LockSupportInterruptDemo {
    public static Object u = new Object();
    public static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    public static ChangeObjectThread t2 = new ChangeObjectThread("t2");


    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in " + getName());
                LockSupport.park();
                if(Thread.interrupted()) {
                    System.out.println(getName() + " is interrupted!!!");
                    return;
                }
                System.out.println(getName() + " is completed");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.interrupt();
        LockSupport.unpark(t2);
    }
}
