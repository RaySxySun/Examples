package basic.classLoadSeq;

public class Child extends Parent
{
    {
        System.out.println("[Child] Non Static Block");
    }
    static
    {
        System.out.println("[Child] Static Block");
    }
    public Child()
    {
        System.out.println("[Child] Constructor");
    }
    public static int childStaticMethod()
    {
        System.out.println("[Child] Static Method");
        return 1000;
    }
    @Override
    protected void finalize() throws Throwable
    {
        // TODO Auto-generated method stub
        super.finalize();
        System.out.println("[Child] Destroy Child instance");
    }
}
