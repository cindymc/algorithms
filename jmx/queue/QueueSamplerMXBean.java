package queue;

/**
 * An MXBean is an MBean which is constrained to open types (primitives, String, and compositions thereof).  Due to these
 * constraints, MXBeans are thus inoperable with any remote clients without the client needing to know about the class.
 * Created by cindymc on 8/5/16.
 */
public interface QueueSamplerMXBean
{
    public QueueSample getQueueSample();
    public void clearQueue();
}

