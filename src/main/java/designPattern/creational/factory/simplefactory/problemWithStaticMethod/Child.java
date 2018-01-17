package designPattern.creational.factory.simplefactory.problemWithStaticMethod;

public class Child extends Parent{
    public String normalStr = "Normal member of child.";
    public static String staticStr = "Static member of child.";

    public void normalMethod(){
        System.out.println("Normal method of child.");
    }

    public static void staticMethod(){
        System.out.println("Static method of child.");
    }
}
