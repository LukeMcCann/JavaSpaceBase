import net.jini.core.lease.Lease;
import net.jini.space.JavaSpace;
import net.jini.space.JavaSpace05;

/*
 * @Author LukeMcCann
 * @class SpaceDemo - use to determine whether the space is running successfully or not
 */
public class SpaceDemo
{
    final static int THREE_SECONDS = 1000 * 3;
    final static  int FIVE_SECONDS = 1000 * 5;
    final static int THREE_MINUTES = THREE_SECONDS * 60;
    final static int FIVE_MINURES = FIVE_SECONDS * 60;

    public static void main(String[] args)
    {

        JavaSpace space = SpaceUtils.getSpace();
        if(space == null)
        {
            System.err.println("Failed to find the JavaSpace");
            System.exit(1);
        }

        // create an object and write it to the space
        try
        {
            HelloWorld obj = new HelloWorld();
            obj.msg = "HelloWorld";
            space.write(obj, null, THREE_MINUTES);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // read the object from the space
        HelloWorld template = new HelloWorld();
        try
        {
            HelloWorld result = (HelloWorld)space.take(template, null, THREE_MINUTES);
            if(result.msg != null)
            {
                System.out.println(result.msg);
                System.out.println("Congratulations! you can now move on to creating your app!");
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // when finished, quit
        System.exit(1);

    }
}
