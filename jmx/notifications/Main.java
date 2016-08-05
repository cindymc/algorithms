package notifications;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by cindymc on 8/5/16.
 */
public class Main
{
    public static void main(String [] args) throws Exception
    {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName objectName = new ObjectName("jmx.notifications:type=CacheResize");

        CacheResize cacheResize = new CacheResize();
        mbs.registerMBean(cacheResize, objectName);

        // Wait forever
        System.err.println("Waiting forever");
        Thread.sleep(Long.MAX_VALUE);
    }
}
