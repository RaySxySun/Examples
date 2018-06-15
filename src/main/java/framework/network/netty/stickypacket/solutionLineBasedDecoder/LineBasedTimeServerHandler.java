package framework.network.netty.stickypacket.solutionLineBasedDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by ray on 18-5-20.
 */
public class LineBasedTimeServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());
//        System.out.println("Time Server得到了数据:" + body + ";counter是:" + ++counter);
//        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString() : "BAD ORDER";
//        currentTime = currentTime + System.getProperty("line.separator");
//        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
//        ctx.writeAndFlush(resp);

        String body = (String) msg;
        System.out.println("Time Server得到了数据:" + body+";counter是:"+ ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString():"BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf  resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public static void main(String[] args) {
        System.out.println("1" + System.getProperty("line.separator") + "1");
    }
}
