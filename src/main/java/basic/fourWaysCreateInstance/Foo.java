package basic.fourWaysCreateInstance;

import java.io.Serializable;

public class Foo implements Serializable, Cloneable {
    public Foo() {
        super();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
