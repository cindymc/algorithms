package designpatterns.observer.java;

import java.util.Observable;

/**
 * Created by cindymc on 7/28/16.
 */
public class WeatherData extends Observable
{
    private float temp;
    private float humidity;
    private float pressure;

    public WeatherData(){}

    public void setMeasurements(float temp, float humidity, float pressure)
    {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;

        // NOTIFY after calling setChanged()!
        measurementsChanged();
    }


    public void measurementsChanged()
    {
        setChanged();
        notifyObservers();
    }

    public float getTemp() {
        return temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }


    // addObserver
    // deleteObserver
    // notifyObservers
    // setChanged, must be called before notifyObservers

}
