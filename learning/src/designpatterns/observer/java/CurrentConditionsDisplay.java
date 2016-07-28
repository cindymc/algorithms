package designpatterns.observer.java;

import designpatterns.observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 *  We are an observer of the Weather station
 *
 * Created by cindy on 7/27/16.
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement
{
    private float temp;
    private float humidity;
    private float pressure;
    Observable observable;

    // Observable is the WeatherData
    // Add ourselves as an observer of the WeatherData
    public CurrentConditionsDisplay(Observable observable)
    {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable obs, Object arg)
    {
        if (obs instanceof WeatherData)
        {
            WeatherData weatherData = (WeatherData)obs;
            this.temp = weatherData.getTemp();
            this.humidity = weatherData.getHumidity();
            this.pressure = weatherData.getPressure();
            display();
        }
    }

    @Override  // DisplayElement
    public void display()
    {
        System.err.println("Temp: " + String.format("%.2f",temp) + "\tHumidity: " + String.format("%.2f", humidity));    // temp, etc
    }
}
