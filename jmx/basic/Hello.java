package basic;

/**
 * Created by cindymc on 8/5/16.
 */
public class Hello implements HelloMBean
{
    String name;
    int cacheSize;

    public Hello(String name) {this.name = name;}

    public void sayHello() {
        System.out.println("hello, world");
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
    public synchronized void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }
}
