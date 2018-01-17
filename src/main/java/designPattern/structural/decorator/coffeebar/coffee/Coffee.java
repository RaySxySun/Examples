package designPattern.structural.decorator.coffeebar.coffee;


import designPattern.structural.decorator.coffeebar.Drink;

public  class Coffee extends Drink {

	@Override
	public float cost() {
		// TODO Auto-generated method stub
		return super.getPrice();
	}

	
}
