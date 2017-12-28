package concurrent.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 线程判断被修改的对象是否可以正确写入的条件是对象的当前值和期望值是否一直。
 * 这个逻辑一般是正确的。
 * 但是一个有一个例外， 如果对象的值连续被修改两次， 并且恢复为原值， 当前线程无法判断。
 * 这就是原子操作的逻辑缺陷。
 * -----------------------------------------------------------------------
 * 这里的例子： 用户少于20元， 商家就赠送20元。 在曾与的同时， 用户消费行为导致余额小于20，
 *            改回原值导致重复赠送。
 * 问题： 能修改对象的条件 =>　１.比较对象的值(yes)　2.是否存在变化(unknown)
 * 解决： 使用时间戳的 AtomicStampedReference
 */
public class CasBug {
    static AtomicReference<Integer> money = new AtomicReference<Integer>();

    public static void main(String[] args) throws InterruptedException {
        money.set(19);
        for (int i = 0; i < 3; i++) {
            new Thread(){
              public void run(){
                  while (true) {
                      while (true) {
                          Integer m = money.get();
                          if (m < 20) {
                              if (money.compareAndSet(m, m + 20)) {
                                  System.out.println("balance was less than 20 dollars. recharge successfully. balance:" + money.get() + " dollars");
                                  break;
                              }
                          } else {
                              //System.out.println("balance is greater than 20 dollars. no action");
                              break;
                          }
                      }
                  }
              }
            }.start();
        }

        new Thread(){
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        Integer m = money.get();
                        if (m > 10) {
                            System.out.println("Greater than 10 dollars");
                            if (money.compareAndSet(m, m - 10)) {
                                System.out.println("paid 10 dollars, balance:" + money.get());
                                break;
                            }
                        } else {
                            System.out.println("Your balance is not enough!");
                            break;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        }.start();
    }
}
