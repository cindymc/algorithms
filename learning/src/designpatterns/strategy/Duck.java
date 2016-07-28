package designpatterns.strategy;

/**
 * Created by cindy on 7/27/16.
 */
public abstract class Duck
{
    FlyBehavior flyBehavior;
    SoundBehavior soundBehavior;

    public Duck(){}

    public void display()
    {
        doFly();
        makeSound();
    }

    public void setFlyBehavior(FlyBehavior fb){flyBehavior = fb;}
    public void setSoundBehavior(SoundBehavior sb){soundBehavior = sb;}

    public void doFly()
    {
        flyBehavior.fly();
    }
    public void makeSound()
    {
        soundBehavior.makeSound();
    }
}
