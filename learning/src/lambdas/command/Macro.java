package lambdas.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cindymc on 8/9/16.
 */
public class Macro
{
    private final List<Command> commands;
    public Macro(){
        commands = new ArrayList<>();}

    // Note that we can call this where 'action' is actually a lambda function
    public void record(Command action){
        commands.add(action);}


    public void run(){
        commands.forEach(Command::execute);
    }
}
