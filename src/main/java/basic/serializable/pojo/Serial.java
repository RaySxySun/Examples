package basic.serializable.pojo;

import java.io.Serializable;

public class Serial implements Serializable {
    /*
     * serialVersionUID:
     *              default value: 1L
     *              serialver.exe: it can be use to generate serialVersionUID
     * If comment it out in Eclipse, there will be a warning:
     *              The serializable class User does not declare a static final serialVersionUID field of type long.
     */

    /*
     *if the id is different from serial/Serial.java, we will get an error
     *java.io.InvalidClassException: basic.serializable.Serial; local class incompatible: stream classdesc serialVersionUID = 1, local class serialVersionUID = 2
     */

    // Create an serial error:
    //      1. comment out serialVersionUID
    //      2. run SerialTest.java to generate output file
    //      3. declare serialVersionUID and give it a value, like 2L.
    //      4. run DeserialTest.java, we will get the above error
    public static final long serialVersionUID = 1L;
    int id;
    String name;
    String test;

    public Serial(int id, String name, String test) {
        this.id = id;
        this.name = name;
        this.test = test;
    }

    public String toString() {
        return "DATA: " + id + " " + name + " " + test;
    }

    public void add() {
        System.out.println("add");
    }

}
