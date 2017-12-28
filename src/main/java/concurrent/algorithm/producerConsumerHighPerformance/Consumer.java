package concurrent.algorithm.producerConsumerHighPerformance;

import com.lmax.disruptor.WorkHandler;

/**
 * Created by ray on 17-12-29.
 */
public class Consumer implements WorkHandler<PCData>{

    @Override
    public void onEvent(PCData event) throws Exception {
        System.out.println(Thread.currentThread().getId() + "Event:  -- " + event.get() * event.get() + " -- ");
    }
}
