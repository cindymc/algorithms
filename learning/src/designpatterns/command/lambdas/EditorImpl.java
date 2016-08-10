package designpatterns.command.lambdas;

/**
 * Created by cindymc on 8/9/16.
 */
public class EditorImpl implements Editor
{
    public void save() {System.out.println("Saving");}
    public void open() {System.out.println("Opening");}
    public void close() {System.out.println("Closing");}
}
