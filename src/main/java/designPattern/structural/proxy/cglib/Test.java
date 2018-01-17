package designPattern.structural.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

public class Test {
    public static void main(String[] args) {
        MyInterceptor interceptor = new MyInterceptor();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(interceptor);

        UserService userService = (UserService) enhancer.create();
        userService.getName(1);
        userService.getAge(1);
    }
}
