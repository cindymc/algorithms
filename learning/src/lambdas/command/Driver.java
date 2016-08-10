package lambdas.command;

/**
 * Created by cindymc on 8/9/16.
 */
public class Driver
{
    public static void main(String[] args)
    {
        Editor editor = new EditorImpl();

        Macro macro = new Macro();

        // The magic of lambdas is that we can pass in a function call that takes no args and returns nothing, so it
        // matches the signature of the Command interface declared in Macro's interface.
        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);
    }
}
