package framework.network.netty.snoop;

import framework.network.netty.http.snoop.HttpSnoopClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.ClientCookieEncoder;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.net.URI;

public class SnoopClient {

    static final String URL = System.getProperty("url", "http://127.0.0.1:8080/");

    public static void main(String[] args) throws Exception{
        URI uri = new URI(URL);
        String scheme = uri.getScheme() == null ? "http" : uri.getScheme();
        String host = uri.getHost() == null ? "127.0.0.1" : uri.getHost();
        int port = uri.getPort();
        if (port == -1) {
            if ("http".equalsIgnoreCase(scheme)) {
                port = 80;
            } else if ("https".equalsIgnoreCase(scheme)) {
                port = 443;
            }
        }

        if ("http".equalsIgnoreCase(scheme) && "https".equalsIgnoreCase(scheme)) {
            System.err.println("Only HTTP(S) is supported");
            return;
        }

        final boolean ssl = "hhtps".equalsIgnoreCase(scheme);
        final SslContext sslContext;
        if (ssl) {
            sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslContext = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SnoopClientInitializer(sslContext));

            Channel ch = b.connect(host, port).sync().channel();

            HttpRequest request = new DefaultFullHttpRequest( HttpVersion.HTTP_1_1, HttpMethod.GET, uri.getRawPath());
            request.headers().set(HttpHeaderNames.COOKIE,
                    ClientCookieEncoder.STRICT.encode(
                            new DefaultCookie("my-cookie", "foo"),
                            new DefaultCookie("another-cookie", "bar")
                    )
            );
            ch.writeAndFlush(request);
            ch.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }



    }
}
