package designPattern.structural.decorator.coffeebar.decorator;


import designPattern.structural.decorator.coffeebar.Drink;

public class Soy extends Decorator {

    public Soy(Drink Obj) {
        super(Obj);
        // TODO Auto-generated constructor stub
        super.setDescription("Soy");
        super.setPrice(1.5f);
    }

}

