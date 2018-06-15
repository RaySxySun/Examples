package basic.io.fileInputStream;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by ray on 18-6-10.
 */
public class FileInputStreamTest {
    public static final int SIZE = 1024;

    public static void main(String[] args) throws Exception {
        File file = new File("F:\\test.txt");

        if (!file.exists()) {
            throw new RuntimeException("the file is not exist");
        }

        FileInputStream fis = new FileInputStream(file);
        int len = 0;
        byte[] buf = new byte[SIZE];

        while ((len = fis.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len));
        }
        fis.close();

    }
}
