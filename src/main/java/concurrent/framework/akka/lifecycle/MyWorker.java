package concurrent.framework.akka.lifecycle;

import akka.actor.UntypedActor;

/**
 * Created by ray on 17-12-31.
 */
public class MyWorker extends UntypedActor {
    public static enum Msg {
        WORKING, DONE, CLOSE;
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("MyWorker is starting");
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("MyWorker is stopping");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg == Msg.WORKING) {
            System.out.println("I am working");
        }
        if (msg == Msg.DONE) {
            System.out.println("stop working");
        }
        if (msg == Msg.CLOSE) {
            System.out.println("I will shutdown.");
            getSender().tell(Msg.CLOSE, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(msg);
        }

    }
}
