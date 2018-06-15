package basic.io.stream.bufferedInputStream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class BufferedInputStreamAvailable {
    public static void main(String[] args) throws Exception{
        InputStream inStream = null;
        BufferedInputStream bis = null;

        try{
            inStream = new FileInputStream("serialtest.txt");
            bis = new BufferedInputStream(inStream);
            while (bis.available() > 0) {
                Integer nBytes = bis.available();
                System.out.println("Available bytes: " + nBytes);
                char ch = (char) bis.read();
                System.out.println("The character read: " + ch);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                inStream.close();
            }
            if (bis != null) {
                bis.close();
            }
        }


    }
}
