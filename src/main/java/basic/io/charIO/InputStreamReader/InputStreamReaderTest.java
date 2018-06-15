package basic.io.charIO.InputStreamReader;

import scala.xml.Null;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by ray on 18-6-10.
 */
public class InputStreamReaderTest {
    public static void transReadNoBuf() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("F\\test.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        char[] cha = new char[1024];
        int len = inputStreamReader.read(cha);
        System.out.println(new String(cha, 0 , len));
        inputStreamReader.close();


    }

    public static void transReadByBuf() throws Exception {
        FileInputStream fis = new FileInputStream("F:\\test.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);

        }
        isr.close();


    }
}
