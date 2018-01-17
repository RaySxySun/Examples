package basic.serializable.deserial;

import basic.serializable.pojo.Serial;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

// have a glimpse at the serial object content
// <AC><ED>^@^Esr^@^Ybasic.serializable.Serial^@^@^@^@^@^@^@^A^B^@^CI^@^BidL^@^Dnamet^@^RLjava/lang/String;L^@^Dtestq^@~^@^Axp^@^@^@^At^@^Dsongt^@^Btt
public class DeserialTest {
    public static void main(String[] args) {
        Serial serial;
        try {
            FileInputStream fis = new FileInputStream("serialtest.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            serial = (Serial) ois.readObject();
            ois.close();
            System.out.println("object is: " + serial);
            serial.add();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
