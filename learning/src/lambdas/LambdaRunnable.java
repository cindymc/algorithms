package lambdas;

import java.io.BufferedReader;

/**
 * Created by cindymc on 8/9/16.
 */
public class LambdaRunnable
{
    public static void main(String [] args)
    {
        Runnable r1 = () -> System.err.println("Running now");

        // Can do either one
        process(r1);

        process(() -> System.err.println("Running another"));

        // How about some file processing?  Read Lambdas book, section 3.3
        //String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    }

    static void process(Runnable r){ r.run();}
    static void processFile(){}

}
