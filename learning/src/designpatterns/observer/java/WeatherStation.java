package designpatterns.observer.java;


/**
 * This is our test driver.
 *
 * Created by cindy on 7/27/16.
 */
public class WeatherStation
{
    public static void main(String [] args)
    {
        // Observable
        WeatherData weatherData = new WeatherData();

        // Observer
        CurrentConditionsDisplay display = new CurrentConditionsDisplay(weatherData);

        // Hook -- important!
        weatherData.addObserver(display);

        // Change the weather, will invoke display() on observer
        weatherData.setMeasurements(104.0f, 97.0f, 30.4f);

    }
}
