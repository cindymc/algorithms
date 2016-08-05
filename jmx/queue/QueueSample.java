package queue;

import java.beans.ConstructorProperties;
import java.util.Date;

/**
 * In the QueueSample class, the MXBean framework calls all the getters in QueueSample to convert the given instance
 * into a CompositeData instance and uses the @ConstructorProperties annotation to reconstruct a QueueSample instance
 * from a CompositeData instance.
 *
 * Created by cindymc on 8/5/16.
 */
public class QueueSample
{
    private final Date date;
    private final int size;
    private final String head;

    // JMX beans need this annotation
    @ConstructorProperties({"date", "size", "head"})
    public QueueSample(Date date, int size, String head) {
        this.date = date;
        this.size = size;
        this.head = head;
    }

    public Date getDate() {
        return date;
    }

    public int getSize() {
        return size;
    }

    public String getHead() {
        return head;
    }
}
