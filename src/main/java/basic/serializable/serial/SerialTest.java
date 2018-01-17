package basic.serializable.serial;

import basic.serializable.pojo.Serial;

import java.io.*;

// have a glimpse at the serial object content
// <AC><ED>^@^Esr^@^Ybasic.serializable.Serial^@^@^@^@^@^@^@^A^B^@^CI^@^BidL^@^Dnamet^@^RLjava/lang/String;L^@^Dtestq^@~^@^Axp^@^@^@^At^@^Dsongt^@^Btt
public class SerialTest {
    public static void main(String[] args) {
        Serial serial = new Serial(1, "song", "tt");
        System.out.println(serial);
        try {
            FileOutputStream fos = new FileOutputStream("serialtest.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(serial);
            oos.flush();
            oos.close();
            System.out.println(check(serial));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean check(Serial serial) {
        try {
            FileInputStream fis = new FileInputStream("serialtest.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Serial serial1 = (Serial) ois.readObject();
            return serial == serial1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
