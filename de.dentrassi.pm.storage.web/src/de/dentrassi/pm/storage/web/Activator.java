package de.dentrassi.pm.storage.web;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.dentrassi.pm.meta.ChannelAspectProcessor;
import de.dentrassi.pm.storage.web.services.ServiceTracker;

public class Activator implements BundleActivator
{

    private static Activator INSTANCE;

    private ServiceTracker tracker;

    private ChannelAspectProcessor aspects;

    /*
     * (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start ( final BundleContext bundleContext ) throws Exception
    {
        Activator.INSTANCE = this;
        this.tracker = new ServiceTracker ( bundleContext );
        this.tracker.open ();
        this.aspects = new ChannelAspectProcessor ( bundleContext );
    }

    /*
     * (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop ( final BundleContext bundleContext ) throws Exception
    {
        this.tracker.close ();
        this.aspects.close ();
        Activator.INSTANCE = null;
    }

    public static ServiceTracker getTracker ()
    {
        return INSTANCE.tracker;
    }

    public static ChannelAspectProcessor getAspects ()
    {
        return INSTANCE.aspects;
    }
}