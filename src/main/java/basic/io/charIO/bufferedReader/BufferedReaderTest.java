package basic.io.charIO.bufferedReader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ray on 18-6-10.
 */
public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("key in a string: ");
        String text = bufferedReader.readLine();
        System.out.println("output:" + text);

    }
}
