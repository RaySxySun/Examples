package designPattern.behavioral.observer.jv;

import java.util.Observable;
import java.util.Observer;


public class ForcastConditions implements Observer  {
	
	private float mTemperatrue;
	private float mPressure;
	private float mHumidity;
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.mTemperatrue=((WeatherData.Data)(arg1)).mTemperatrue;
		this.mPressure=((WeatherData.Data)(arg1)).mPressure;
		this.mHumidity=((WeatherData.Data)(arg1)).mHumidity;
		display();
	}
	
	public void display()
	{
		System.out.println("***Tomorrow mTemperatrue:" +(mTemperatrue+1)+"***");
		System.out.println("***Tomorrow mPressure:" +(mPressure+1)+"***");
		System.out.println("***Tomorrow mHumidity:" +(mHumidity+1)+"***");
	}
	
	
}
