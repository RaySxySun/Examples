package concurrent.algorithm.producerComsumer;

/**
 * Created by ray on 17-12-29.
 */
public class PCData {
    private final int intData;

    public PCData(String i) {
        this.intData = Integer.valueOf(i);
    }

    public PCData(int intData) {
        this.intData = intData;
    }

    public int getData() {
        return intData;
    }

    @Override
    public String toString() {
        return  "data:" + intData;
    }
}
