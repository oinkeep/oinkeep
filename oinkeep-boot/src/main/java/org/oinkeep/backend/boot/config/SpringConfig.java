package org.oinkeep.backend.boot.config;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Map;

public class SpringConfig {

    private final Map<String, Object> properties;

    public SpringConfig(final Map<String, Object> properties) {
        this.properties = properties;
    }

    public ApplicationContext getApplicationContext() {
        // Crea el contexto de Spring
        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocations( "org.oinkeep.backend.util",
                                    "org.oinkeep.backend.core",
                                    "org.oinkeep.backend.rest",
                                    "org.oinkeep.backend.plugin",
                                    "org.oinkeep.backend.boot");
        context.refresh();
        return context;
    }
}
