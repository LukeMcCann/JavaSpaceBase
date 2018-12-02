import net.jini.core.discovery.LookupLocator;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceTemplate;
import net.jini.core.transaction.server.TransactionManager;
import net.jini.space.JavaSpace;

/*
 * @Author - Luke McCann - The University of Huddersfield
 *
 * This is a breakdown of an object oriented implementation of the SpaceAccessor class
 * showcasing how each part of the setup class works and using more robust try and catch
 * statements which indicate issues more specifically.
 *
 * This file was created to gain a deeper understanding into how JavaSpaces work, SpaceUtils
 * utilises less code than this class.
 */
public class SpaceAccessor
{
    /*
     * getSpace - find JavaSpace on network from location hostname
     * @param hostname - JavaSpace host
     * @return JavaSpace - a reference to the space
     */
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

    public static JavaSpace getSpace() { return getSpace("localhost"); }


    /*
     * getManager - set and return TransactionManager
     * @param hostname
     * @return TransactionManager
     */
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

    public static TransactionManager getManager() { return getManager("localhost"); }

    /*
     * setSecurity - check for and set SecurityManager
     * @param manager - the security manager to set
     */
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

    /*
     * unicastServiceLookup - use if host location is known
     * @param hostname
     * @return ServiceRegistrar - the ServiceRegistrar for the host
     */
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
