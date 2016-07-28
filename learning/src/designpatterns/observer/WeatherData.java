package designpatterns.observer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cindy on 7/27/16.
 */
public class WeatherData implements Subject
{
    // Thread-safe for read/write.  Blocks on both.
    Set<Observer> observers = Collections.synchronizedSet(new HashSet<Observer>());

    private float temp;
    private float humidity;
    private float pressure;

    public void setMeasurements(float temp, float humidity, float pressure)
    {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;

        // NOTIFY!
        measurementsChanged();
    }

    public void measurementsChanged()
    {
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers()
    {
        for (Observer o: observers)
        {
            o.update(temp, humidity, pressure);
        }
    }
}
