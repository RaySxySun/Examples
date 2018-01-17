package designPattern.behavioral.strategy.duck;


import designPattern.behavioral.strategy.flybehavior.GoodFlyBehavior;
import designPattern.behavioral.strategy.quackbehavior.GaGaQuackBehavior;

public class GreenHeadDuck extends Duck {

	public GreenHeadDuck() {
		mFlyBehavior = new GoodFlyBehavior();
		mQuackBehavior = new GaGaQuackBehavior();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("**GreenHead**");
	}

}