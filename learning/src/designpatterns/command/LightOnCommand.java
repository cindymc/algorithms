package designpatterns.command;

/**
 * Created by cindymc on 7/29/16.
 */
public class LightOnCommand implements Command
{
    Light light;

    public LightOnCommand(Light light)
    {
        this.light = light;
    }

    public void execute()
    {
        // We can do more than just one action.  We can also do (if implemented) three actions:
        // light.on();
        // light.dim();
        // light.strobe();

        light.on();
    }

    // We could also keep track of previous states so we can undo them, but a light just has off/on
    public void undo(){light.off();}
}
