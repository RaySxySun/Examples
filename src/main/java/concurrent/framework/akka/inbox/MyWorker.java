package concurrent.framework.akka.inbox;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by ray on 17-12-31.
 */
public class MyWorker extends UntypedActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public static enum Msg {
        WORKING, DONE, CLOSE;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg == Msg.WORKING) {
            System.out.println("I am working now");
        }
        if (msg == Msg.DONE) {
            System.out.println("Stop working, DONE");
        }
        if (msg == Msg.CLOSE) {
            log.info("I am shutdown");
            getSender().tell(Msg.CLOSE, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(msg);
        }
    }
}
