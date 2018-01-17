package designPattern.structural.proxy.cglib;

public class UserServiceImpl implements UserService {

    @Override
    public String getName(int id) {
        System.out.println("----get user name----");
        return "Jessie";
    }

    @Override
    public String getAge(int id) {
        System.out.println("----get user age----");
        return "20";
    }
}
