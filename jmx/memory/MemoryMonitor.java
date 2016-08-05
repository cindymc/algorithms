package memory;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryNotificationInfo;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;
import java.util.logging.Logger;

/**
 * Better examples are here:
 * https://docs.oracle.com/javase/8/docs/api/java/lang/management/MemoryPoolMXBean.html
 *
 * Created by cindymc on 8/5/16.
 */
public class MemoryMonitor
{
    public static void main(String[] args)
    {
        new MemoryMonitor().monitorMemory();
    }

    void monitorMemory()
    {
        // Start to monitor memory usage
        MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
        NotificationEmitter emitter = (NotificationEmitter) mbean;

        // Register our listener
        LowMemoryListener listener = new LowMemoryListener();
        emitter.addNotificationListener(listener, null, null);

        // Get all the memory pool mbeans
        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean pool : pools)
        {
            // Unfortunately, for this app, it is not
            if(pool.isUsageThresholdSupported())
            {
                // JVM Heap memory threshold in bytes (1.00 Gb = 1000000000), 0 to disable
               // pool.setUsageThreshold(1000000000);

                // Set a really small threshold so we can watch it work
                pool.setUsageThreshold(100);
            }
        }
    }

    class LowMemoryListener implements NotificationListener
    {
        @Override
        public void handleNotification(Notification notification, Object handback)
        {
            // Look at Notification javadocs; there are lots of things you can ask about this Notification
            String notifType = notification.getType();
            if (notifType.equals(MemoryNotificationInfo.MEMORY_THRESHOLD_EXCEEDED))
            {
                System.err.println("Got notification message: " + notification.getMessage());

                // potential low memory, log a warning
                // log.warning("Memory usage threshold reached");
                System.err.println("Memory usage threshold reached");
            }
        }

    }
}


