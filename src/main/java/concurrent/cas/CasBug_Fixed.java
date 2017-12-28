package concurrent.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *　使用AtomicStampedReference引入时间戳，　修复原子操作状态信息丢失的缺陷。
 *  getReference() & getStamp()
 */
public class CasBug_Fixed {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19,0);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            final int timestamp = money.getStamp();
            new Thread() {
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = money.getReference();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20, timestamp, timestamp + 1)) {
                                    System.out.println("balance was less than 20 dollars. recharge successfully. balance:" + money.getReference() + " dollars");
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }.start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        while (true) {
                            int timestamp = money.getStamp();
                            Integer m = money.getReference();
                            if (m > 10) {
                                System.out.println("Greater than 10 dollars");
                                if (money.compareAndSet(m, m - 10, timestamp, timestamp + 1)) {
                                    System.out.println("paid 10 dollars, balance:" + money.getReference());
                                    break;
                                }
                            } else {
                                System.out.println("Your balance is not enough!");
                                break;
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }
    }
}
