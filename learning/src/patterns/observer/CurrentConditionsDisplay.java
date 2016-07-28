package patterns.observer;

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
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData)
    {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override  // Observer
    public void update(float temp, float humidity, float pressure)
    {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override  // DisplayElement
    public void display()
    {
        System.err.println("Temp: " + String.format("%.2f",temp) + "\tHumidity: " + String.format("%.2f", humidity));    // temp, etc
    }
}
