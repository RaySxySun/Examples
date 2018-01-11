package framework.network.netty.snoop;

import framework.network.netty.http.snoop.HttpSnoopServerHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;

public class SnoopServerInitializer extends ChannelInitializer<SocketChannel>{

    private final SslContext sslContext;

    public SnoopServerInitializer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        if (sslContext != null) {
            p.addLast(sslContext.newHandler(ch.alloc()));
        }
        p.addLast(new HttpRequestDecoder());
        p.addLast(new HttpResponseEncoder());
        p.addLast(new SnoopServerHandler());

    }
}
