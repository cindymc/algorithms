package designpatterns.strategy;

/**
 * Created by cindy on 7/27/16.
 */
public class Quack implements SoundBehavior
{
    public void makeSound(){System.err.println("Quack!");}
}
