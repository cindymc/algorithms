package notifications;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * Created by cindymc on 8/5/16.
 */
public class CacheResize extends NotificationBroadcasterSupport implements CacheResizeMBean
{
    private final String name = "Reginald";
    private int cacheSize = DEFAULT_CACHE_SIZE;
    private static final int DEFAULT_CACHE_SIZE = 200;

    private long sequenceNumber = 1;

    public CacheResize(){}

    public void sayHello() {
        System.err.println("hello, world");
    }

    public int add(int x, int y) {
        return x + y;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int getCacheSize() {
        return cacheSize;
    }

    @Override
    public synchronized void setCacheSize(int cacheSize)
    {
        System.err.println("Changing cache size to " + cacheSize);

        // Save off old value
        int oldSize = this.cacheSize;
        this.cacheSize = cacheSize;

        // Create Notification
        Notification n = new AttributeChangeNotification(this, /* source */
                sequenceNumber++, /* sequence number */
                System.currentTimeMillis(),  /* timestamp */
                "CacheSize changed", /* msg */
                "CacheSize", /* attribute */
                "int", /* attribute type */
                oldSize, /* old value */
                cacheSize /* new value */
                );

        // Send it (impl in base class)
        sendNotification(n);
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo()
    {
        String[] types = new String[] {
                AttributeChangeNotification.ATTRIBUTE_CHANGE
        };
        String name = AttributeChangeNotification.class.getName();
        String description = "An attribute of this MBean has changed";
        MBeanNotificationInfo info =
                new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[] {info};
    }

}
