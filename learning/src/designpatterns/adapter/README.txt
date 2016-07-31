Adapter: convert one interface into another that the client expects to work with.

Make a Turkey into a Duck:

interface Duck
{
    void fly()
}

class TurkeyAdapter implements Duck
{
    public TurkeyAdapter(Turkey turkey){...}

    public void fly() { turkey.fly()}
}