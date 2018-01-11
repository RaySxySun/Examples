package basic.nio.filechannel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFileChannel {
    public static void main(String[] args) throws Exception{
        RandomAccessFile f = new RandomAccessFile("pom.xml", "rw");
        FileChannel fch = f.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(500);

        int bytesRead = fch.read(buf);
        while (bytesRead != -1) {
//            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char)buf.get());
            }
            buf.clear();
            bytesRead = fch.read(buf);
        }
        System.out.println(fch.size());
        f.close();
    }
}
