package designPattern.creational.factory.simplefactory.problemWithStaticMethod;

public class Test {
    public static void main(String[] args) {
        Child child = new Child();
        System.out.println(child.normalStr);
        System.out.println(Child.staticStr);
        child.normalMethod();
        Child.staticMethod();

        System.out.println("-------------------------------------------------");

        Parent child1 = new Child();
        System.out.println(child1.normalStr);
        System.out.println(Parent.staticStr);
        child1.normalMethod();
        Parent.staticMethod();
    }
}
