package basic.fourWaysCreateInstance;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        // 1. normal way
        Foo foo1 = new Foo();
        System.out.println("foo1:" + foo1);

        // 2. reflect
            // 2.1
        try {
            Class fooClass21 = Foo.class;
            Foo foo21 = (Foo) fooClass21.newInstance();
            System.out.println("foo21:" + foo21);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
            // 2.2
        try {
            Class fooClass22 = Class.forName("basic.fourWaysCreateInstance.Foo");
            Foo foo22 = (Foo) fooClass22.newInstance();
            System.out.println("foo22:" + foo22);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 3. serialize
        Foo fooSource = new Foo();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("serialtest.txt"));
            objectOutputStream.writeObject(fooSource);

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("serialtest.txt"));
            Foo foo3 = (Foo) objectInputStream.readObject();
            System.out.println("foo3:" + foo3.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 4. Clone
            // must implement Cloneable interface
        try {
            Foo foo4source = new Foo();
            Foo foo4 = (Foo) foo4source.clone();
            System.out.println("foo4:" + foo4.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
