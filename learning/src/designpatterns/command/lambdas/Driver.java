package designpatterns.command.lambdas;

/**
 * Created by cindymc on 8/9/16.
 */
public class Driver
{
    public static void main(String[] args)
    {
        Editor editor = new EditorImpl();

        Macro macro = new Macro();

        // The magic of lambdas is that we can pass in a function call that takes no args and returns nothing, because
        // the record() method of Macro has no args and returns void.  (Is this really how it works?)
        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);
    }
}
