package de.dentrassi.pm.system.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.HttpServletRequest;

import de.dentrassi.osgi.web.Controller;
import de.dentrassi.osgi.web.LinkTarget;
import de.dentrassi.osgi.web.ModelAndView;
import de.dentrassi.osgi.web.RequestMapping;
import de.dentrassi.osgi.web.ViewResolver;
import de.dentrassi.osgi.web.controller.ControllerInterceptor;
import de.dentrassi.pm.common.web.InterfaceExtender;
import de.dentrassi.pm.common.web.Modifier;
import de.dentrassi.pm.common.web.menu.MenuEntry;
import de.dentrassi.pm.sec.web.controller.HttpContraintControllerInterceptor;
import de.dentrassi.pm.sec.web.controller.Secured;
import de.dentrassi.pm.sec.web.controller.SecuredControllerInterceptor;

@Controller
@ViewResolver ( "/WEB-INF/views/info/%s.jsp" )
@RequestMapping ( "/system/info" )
@Secured
@ControllerInterceptor ( SecuredControllerInterceptor.class )
@HttpConstraint ( rolesAllowed = "ADMIN" )
@ControllerInterceptor ( HttpContraintControllerInterceptor.class )
public class InformationController implements InterfaceExtender
{
    @RequestMapping
    public ModelAndView view ()
    {
        final Map<String, Object> model = new HashMap<> ();

        final Runtime r = Runtime.getRuntime ();

        // runtime

        model.put ( "freeMemory", r.freeMemory () );
        model.put ( "maxMemory", r.maxMemory () );
        model.put ( "totalMemory", r.totalMemory () );
        model.put ( "usedMemory", r.totalMemory () - r.freeMemory () );

        // system

        model.put ( "availableProcessors", r.availableProcessors () );

        // java

        model.put ( "java", makeJavaInformation () );

        return new ModelAndView ( "index", model );
    }

    private Map<String, String> makeJavaInformation ()
    {
        final Map<String, String> result = new LinkedHashMap<> ();

        result.put ( "Version", System.getProperty ( "java.version" ) );
        result.put ( "Vendor", System.getProperty ( "java.vendor" ) );
        result.put ( "Temp Dir", System.getProperty ( "java.io.tmpdir" ) );
        result.put ( "Home", System.getProperty ( "java.home" ) );

        return result;
    }

    @RequestMapping ( "/gc" )
    public String gc ()
    {
        System.gc ();
        return "referer:/system/info";
    }

    @Override
    public List<MenuEntry> getMainMenuEntries ( final HttpServletRequest request )
    {
        final List<MenuEntry> result = new LinkedList<> ();

        if ( request.isUserInRole ( "ADMIN" ) )
        {
            result.add ( new MenuEntry ( "System", Integer.MAX_VALUE, "Information", 1_000, LinkTarget.createFromController ( InformationController.class, "view" ), Modifier.DEFAULT, "cog" ) );
        }

        return result;
    }
}
