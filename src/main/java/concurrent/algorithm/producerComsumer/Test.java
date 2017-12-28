package concurrent.algorithm.producerComsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by ray on 17-12-29.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCData> queue = new LinkedBlockingDeque<PCData>(10);
        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Producer p3 = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);

        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(p1);
        es.execute(p2);
        es.execute(p3);
        es.execute(c1);
        es.execute(c2);
        es.execute(c3);

        Thread.sleep(10 * 1000);
        p1.stop();
        p2.stop();
        p3.stop();
        es.shutdown();
    }
}
