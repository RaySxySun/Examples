package framework.network.netty.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * http://netty.io/wiki/user-guide-for-4.x.html
 */
public class DiscardServer {
    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
    /* The first one, often called 'boss', accepts an incoming connection.
     * The second one, often called 'worker', handles the traffic of the accepted connection once the boss accepts the connection and registers
     * the accepted connection to the worker.
     */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // ServerBootstrap is a helper class that sets up a server.
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    //we specify to use the NioServerSocketChannel class to instantiate a new Channel
                    .channel(NioServerSocketChannel.class)
                    //The handler specified here will always be evaluated by a newly accepted Channel
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //  Here, we bind to the port 8080 of all NICs (network interface cards) in the machine.
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    // need to test it if it really works. The easiest way is to
    // telnet localhost 8080
    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
    }
}
