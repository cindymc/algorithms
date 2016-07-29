package designpatterns.command;

/**
 * Created by cindymc on 7/29/16.
 */
public class LightOffCommand implements Command
{
    Light light;

    public LightOffCommand(Light light)
    {
        this.light = light;
    }

    public void execute()
    {
        light.off();
    }
    public void undo(){light.on();}
}
