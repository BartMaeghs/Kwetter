package rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import yolo.sjwek.kwetter.rest.KwetteRest;

/**
 *
 * @author edwinlambregts
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addClasses(resources);
        return resources;
    }
    
    private void addClasses(Set<Class<?>> resources) {
       // Resources
       resources.add(KwetteRest.class);
    }
    
}
