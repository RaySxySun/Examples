package concurrent.algorithm.producerConsumerHighPerformance;

import com.lmax.disruptor.EventFactory;

/**
 * Created by ray on 17-12-29.
 */
public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
