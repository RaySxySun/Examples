package concurrent.algorithm.producerConsumerHighPerformance;

/**
 * Created by ray on 17-12-29.
 */
public class PCData {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    public long get() {
        return this.value;
    }
}
