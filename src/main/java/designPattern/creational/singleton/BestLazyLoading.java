package designPattern.creational.singleton;

/**
 * Created by ray on 18-2-8.
 */
public class BestLazyLoading {
    private BestLazyLoading (){
    }

    private static class SingletonHoler{
        private final static BestLazyLoading instance = new BestLazyLoading();
    }

    public static BestLazyLoading getInstance() {
        return SingletonHoler.instance;
    }
}
