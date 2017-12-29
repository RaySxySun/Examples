package concurrent.framework.akka.hello;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Created by ray on 17-12-31.
 */
public class Greeter extends UntypedActor {
    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg == Msg.GREET) {
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(msg);
        }
    }
}
