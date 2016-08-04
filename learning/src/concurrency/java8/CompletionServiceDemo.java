package concurrency.java8;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Demonstrate diff between a loop, ExecutorService, and CompletionService
 *
 * Created by cindymc on 8/4/16.
 */
public class CompletionServiceDemo
{
    static final int waitTime = 200;
    static final int numThreads = 3;


    public static void main(String [] args)
    {
        List<String> strings = new ArrayList<>();
        Random random = new Random();

        // Here's how to loop to generate 100 random strings
        IntStream.range(0,100).forEach(i -> {strings.add(RandomString.generateRandomString(random, 10 ));});


    }

    void loop(List<String> strings)
    {
        for (String s : strings)
        {
            try
            {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ignore){}
            System.out.println(s);
        }
    }

    /**
     * Futures
     * @param strings
     * @throws Exception
     */
    void normalExecutor(List<String> strings) throws Exception
    {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        try {
            // Create a bunch of Futures
            Set<Future<String>> futures = new HashSet<>();
            for (String s : strings)
            {
                futures.add(executor.submit(new Printer(s)));
            }

            // Get results
            for (Future<String> future : futures)
            {
                System.out.println(future.get());
            }
        }
        finally
        {
            executor.shutdownNow();
        }
    }

    /**
     * Completion Service
     */
    void completionService(List<String> strings) throws Exception
    {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);

        // Submit Callable directly to CompletionService
        for (String s : strings)
        {
            // Note that submit() will return a Future, so if we needed to control the job (like cancel it), we could
            // use that.  But for this example, we don't care.
            completionService.submit(new Printer(s));
        }

        // Get results
        // poll(): Retrieves and removes the Future representing the next completed task, or null if none are present.
        // take(): Retrieves and removes the Future representing the next completed task, waiting if none are yet present.
        for (int i=0; i<strings.size(); i++)
        {
            Future<String> f = completionService.take();
            System.err.println(f.get());   // Here's how you get the result
        }
    }

    class Printer implements Callable<String>
    {
        String s;
        public Printer(String s){this.s = s;}
        public String call()
        {
            return s;
        }
    }

}
