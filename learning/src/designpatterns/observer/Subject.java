package designpatterns.observer;


/**
 * Created by cindy on 7/27/16.
 */
public interface Subject 
{
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
