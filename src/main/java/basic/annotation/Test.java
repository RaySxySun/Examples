package basic.annotation;

import javax.rmi.CORBA.Util;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void track(List<Integer> useCases, Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            UseCase annotation = m.getAnnotation(UseCase.class);
            if (annotation != null) {
                System.out.println("Found Use Case: " + annotation.id() + " " + annotation.description());
                useCases.remove(new Integer(annotation.id()));
            }
        }
        for (int i : useCases) {
            System.out.println("Warning: Missing use case-" + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 7, 8, 9, 10);
        track(useCases, PasswordUtils.class);

    }
}
