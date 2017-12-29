package concurrent.framework.akka.inbox;

import akka.actor.*;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by ray on 17-12-31.
 */
public class Test {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("inboxdemo", ConfigFactory.load("samplehello.conf"));
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");
        final Inbox inbox = Inbox.create(system);
        inbox.watch(worker);
        inbox.send(worker, MyWorker.Msg.WORKING);
        inbox.send(worker, MyWorker.Msg.DONE);
        inbox.send(worker, MyWorker.Msg.CLOSE);

        while (true) {
            Object msg = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
            if (msg == MyWorker.Msg.CLOSE) {
                System.out.println("My worker is closing");
            } else if (msg instanceof Terminated) {
                System.out.println("My worker is dead");
                system.shutdown();
                break;
            } else {
                System.out.println(msg);
            }
        }
    }
}
