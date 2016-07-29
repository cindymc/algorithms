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

        // Here's a way to do this (without a derived Command class for each action) with lambdas.  This will only work if the
        // Command interface has a single method (execute()) that takes no args and returns no values.
        // WOW.  Cool.
        //remote.setCommand(0, () -> {light.on();} , () -> {light.off();});

        // Even simpler: use method references
        //remote.setCommand(0, light::on, light::off);

        // What if you want to take several actions for a single command?
        Command dimStrobeLight = () -> { light.on(); light.dim(); light.strobe();};
        remote.setCommand(0, dimStrobeLight, light::off);

        // Push the on/off button
        remote.onButtonPushed(0);
        remote.offButtonPushed(0);
    }
}
