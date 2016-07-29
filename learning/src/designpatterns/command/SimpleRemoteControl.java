package designpatterns.command;

/**
 * Created by cindymc on 7/29/16.
 */
public class SimpleRemoteControl
{
    Command slot;
    public SimpleRemoteControl(){}

    public void setCommand(Command command){slot = command;}

    public void buttonWasPressed(){slot.execute();}
}
