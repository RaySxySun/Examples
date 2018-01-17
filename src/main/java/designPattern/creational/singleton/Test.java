package designPattern.creational.singleton;

/**
 * Created by ray on 18-2-8.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(BestLazyLoading.getInstance());
        System.out.println(BestLazyLoading.getInstance());
    }
}
