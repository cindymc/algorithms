package concurrency.java6;

import java.util.concurrent.BlockingQueue;

/**
 * @author Copyright (c) 2013 by Oracle, Inc. All Rights Reserved.
 */
public class EventProducer
{
    BlockingQueue<Integer> queue;

    public EventProducer(BlockingQueue<Integer> queue)
    {
        this.queue = queue;
    }

    public void addEvent(Integer i)
    {
        // todo: what happens when queue is full?
        System.err.println("Adding " + i);
        queue.add(i);
    }
}
