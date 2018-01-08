package basic.classLoadSeq;

public class Parent {
    public static int t = parentStaticMethod2();
    {
        System.out.println("[Parent] Non Static Block");
    }
    static
    {
        System.out.println("[Parent] Static Block");
    }
    public Parent()
    {
        System.out.println("[Parent] Constructor");
    }
    public static int parentStaticMethod()
    {
        System.out.println("[Parent] Static Method");
        return 10;
    }
    public static int parentStaticMethod2()
    {
        // The method will be loaded because of the static member variable "t".
        // public static int t = parentStaticMethod2();
        System.out.println("[Parent] Static Method 2");
        return 9;
    }
    public static class ParentHolder{
        public static Parent instance = new Parent();
        {
            System.out.println("[Parent-Inner Class][ParentHolder] Non Static Block");
        }
        static
        {
            System.out.println("[Parent-Inner Class][ParentHolder] Static Block");
        }
    }

    public static Parent getInstance(){
        System.out.println("[Parent] Static Method - getInstance");
        return ParentHolder.instance;
    }

    @Override
    protected void finalize() throws Throwable
    {
        // TODO Auto-generated method stub
        super.finalize();
        System.out.println("[Parent] Destroy Parent Class instance");
    }
}
