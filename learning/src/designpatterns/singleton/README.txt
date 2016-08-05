Example uses:
    - cache
    - thread pools
    - device drivers
    - loggers
    - monitors
    - preferences
    - registry settings

Note that it's possible (but rare) for an application to have two classloaders, each of which could instantiate the
Singleton.  The only way to avoid this is to use the Enum pattern.

Never let your Singleton be cloned; don't implement Object.clone().  And if you object implements Cloneable (or some other
interface that does), override the clone() method to throw an exception.