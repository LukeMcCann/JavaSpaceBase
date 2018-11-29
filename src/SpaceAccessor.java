import net.jini.core.discovery.LookupLocator;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceTemplate;
import net.jini.core.transaction.server.TransactionManager;
import net.jini.space.JavaSpace;

public class SpaceAccessor
{
    public static JavaSpace getSpace(String hostname)
    {
        setSecurity(new SecurityManager());
        JavaSpace space = null;

        try
        {
            ServiceRegistrar sr = unicastServiceLookup(hostname);

            Class c = Class.forName("net.jini.space.JavaSpace");
            Class[] classTemplate = {c};

            space = (JavaSpace) sr.lookup(new ServiceTemplate(
                    null,
                    classTemplate,
                    null));


        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return space;
    }

    public static JavaSpace getSpace() {return getSpace("waterloo");}


    public static TransactionManager getManager(String hostname)
    {
        setSecurity(new SecurityManager());

        TransactionManager tm = null;
        try
        {
            ServiceRegistrar sr = unicastServiceLookup(hostname);

            Class c = Class.forName("net.jini.core.transaction.server.TransactionManager");
            Class[] classTemplate = {c};

            tm = (TransactionManager) sr.lookup(new ServiceTemplate(
                    null,
                    classTemplate,
                    null));
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return tm;
    }

    public static TransactionManager getManager() {return getManager("waterloo");}


    public static void setSecurity(SecurityManager manager)
    {
        try
        {
            if(System.getSecurityManager() == null)
            {
                System.setSecurityManager(manager);
            }
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
            System.err.println("Problem setting security manager: " + manager);
        }
    }

    public static ServiceRegistrar unicastServiceLookup(String hostname)
    {
        ServiceRegistrar sr = null;
        try
        {
            LookupLocator ll = new LookupLocator("jini://" + hostname);
            sr = ll.getRegistrar();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
            System.err.println("Issue retrieving ServiceRegistrar");
        }
        return  sr;
    }
}
