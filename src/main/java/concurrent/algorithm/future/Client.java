package concurrent.algorithm.future;

/**
 * Created by ray on 17-12-30.
 */
public class Client {
    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        new Thread(){
            public void run() {
                RealData realData = new RealData(queryStr);
                future.setRealdata(realData);
            }
        }.start();
        return future;
    }
}
