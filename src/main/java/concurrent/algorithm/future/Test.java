package concurrent.algorithm.future;

/**
 * Created by ray on 17-12-30.
 */
public class Test {
    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("request has been sent");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("data = " + data.getResult());
    }
}
