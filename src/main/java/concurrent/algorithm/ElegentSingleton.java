package concurrent.algorithm;

/**
 * Created by ray on 17-12-29.
 */
public class ElegentSingleton {
    private ElegentSingleton(){
        System.out.println("StaticSingleton is created");
    }

    private static class SingletonHolder {
        private static ElegentSingleton instance = new ElegentSingleton();
    }

    public static ElegentSingleton getInstance(){
        return SingletonHolder.instance;
    }
}
