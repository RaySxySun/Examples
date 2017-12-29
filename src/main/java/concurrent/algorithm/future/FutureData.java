package concurrent.algorithm.future;

/**
 * Created by ray on 17-12-30.
 */
public class FutureData implements Data {
    protected RealData realdata = null;
    protected boolean isReady = false;

    public synchronized void setRealdata(RealData realdata) {
        if (isReady) {
            return;
        }
        this.realdata = realdata;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realdata.result;
    }
}
