package concurrent.lockSupport;

/**
 * Created by ray on 17-12-26.
 */
public class BadSuspend {
    public static Object u = new Object();
    public static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    public static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        public void run() {
            synchronized (u){
                System.out.println("in " + getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
       t1.start();
       Thread.sleep(100);
       t2.start();
       t1.resume();
       // the program won't end because t2.resume will happen before t2.suspend (due to the syn lock on Object u)
       t2.resume();
       t1.join();
       t2.join();
    }
}
