package service.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import service.demo.Hello;
import service.demo.HelloImpl;

/**
 * Created by ray on 18-7-13.
 */
public class HelloServer {
    public static void main(String[] args) {
        try {
            TServerSocket tServerSocket = new TServerSocket(7911);
            TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();
            TProcessor processor = new Hello.Processor(new HelloImpl());
            TServer server = new TSimpleServer(new TServer.Args(tServerSocket).processor(processor));
            System.out.println("Start server on port 7911...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
