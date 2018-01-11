package framework.network.netty.snoop;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.ssl.SslContext;

public class SnoopClientInitializer extends ChannelInitializer<SocketChannel>{

    private final SslContext sslContext;

    public SnoopClientInitializer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();

        if (sslContext != null) {
            p.addLast(sslContext.newHandler(ch.alloc()));
        }

        p.addLast(new HttpClientCodec());
        p.addLast(new HttpContentDecompressor());

        p.addLast(new SnoopClientHandler());

    }
}
