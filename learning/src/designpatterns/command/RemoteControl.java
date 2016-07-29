package designpatterns.command;

/**
 * Multiple slots on a remote control
 * Created by cindymc on 7/29/16.
 */
public class RemoteControl
{
    Command [] onCommands;
    Command [] offCommands;

    // Create a RemoteControl that does nothing (yet)
    public RemoteControl()
    {
        onCommands = new Command[7];
        offCommands = new Command[7];

        // All commands do nothing to start with
        Command noCommand = new NoCommand();
        for (int i=0; i<7; i++)
        {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    // Put a command into a slot.  Each appliance will have a slot (light, tv, stereo, etc)
    public void setCommand(int slot, Command onCommand, Command offCommand)
    {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    // Button ON
    public void onButtonPushed(int slot)
    {
        onCommands[slot].execute();
    }

    // Button OFF
    public void offButtonPushed(int slot) {
        offCommands[slot].execute();
    }
}
