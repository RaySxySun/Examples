package designPattern.creational.factory.simplefactory.problemWithStaticMethod;

public class Parent {
    public String normalStr = "Normal member of parent";
    public static String staticStr = "Static member of parent";

    public void normalMethod(){
        System.out.println("Normal method of parent.");
    }

    public static void staticMethod(){
        System.out.println("Static method of parent.");
    }
}
