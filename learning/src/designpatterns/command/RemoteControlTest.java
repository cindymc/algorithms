package designpatterns.command;

/**
 * Created by cindymc on 7/29/16.
 */
public class RemoteControlTest
{
    public static void main(String [] args)
    {
        //SimpleRemoteControl remote = new SimpleRemoteControl();

        RemoteControl remote = new RemoteControl();


        Light light = new Light();
        //LightOnCommand on = new LightOnCommand(light);
        //LightOffCommand off = new LightOffCommand(light);

        // Put the light control in slot 0, with on/off actions
        //remote.setCommand(0, on, off);

        // Here's a way to do this much cleaner, with lambdas.  This will only work if the
        // Command interface has a single method (execute()) that takes no args and returns no values.
        // WOW.  Cool.
        remote.setCommand(0, () -> {light.on();} , () -> {light.off();});

        // Push the on/off button
        remote.onButtonPushed(0);
        remote.offButtonPushed(0);
    }
}
