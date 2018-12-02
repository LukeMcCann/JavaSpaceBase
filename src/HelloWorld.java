import net.jini.core.entry.Entry;

public class HelloWorld implements Entry
{
    public String msg;

    // no arg constructor
    public HelloWorld() {}

    public HelloWorld(String msg )
    {
        this.msg = msg;
    }
}
