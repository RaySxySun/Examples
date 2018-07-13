package service.demo;

import org.apache.thrift.TException;

/**
 * Created by ray on 18-7-13.
 */
public class HelloImpl implements Hello.Iface{
    @Override
    public String helloString(String para) throws TException {
        return null;
    }

    @Override
    public int helloInt(int para) throws TException {
        return 0;
    }

    @Override
    public boolean helloBoolean(boolean para) throws TException {
        return false;
    }

    @Override
    public void helloVoid() throws TException {
        System.out.println("Hello World!");

    }

    @Override
    public String helloNull() throws TException {
        return null;
    }
}
