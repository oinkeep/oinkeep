package org.oinkeep.backend.boot.config;

import lombok.Getter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;
import java.util.Optional;

public class JettyServerConfig {

    private final WebApplicationContext context;
    private final Map<String, Object> properties;

    private @Getter Integer port;

    public JettyServerConfig(final WebApplicationContext context, final Map<String, Object> properties) {
        this.context = context;
        this.properties = properties;
    }

    public Optional<Server> getServer(){

        Optional<Server> result = Optional.empty();
        // Crea el contexto de Jetty
        final ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.setContextPath("/");
        servletContextHandler.addServlet(new ServletHolder(new DispatcherServlet(this.context)), "/*");
        servletContextHandler.addEventListener( new ContextLoaderListener(this.context));

        // Ahora puedes trabajar con el entorno
        if (this.properties.containsKey("server") && this.properties.get("server") instanceof Map) {
            final Map<String, Object> serverConfig = (Map<String, Object>) this.properties.get("server");
            if(serverConfig.containsKey("port")) {
                this.port = (Integer) serverConfig.get("port");
                // Configura el servidor Jetty
                final Server server = new Server(this.port);
                server.setHandler(servletContextHandler);
                result = Optional.of(server);
            }
        }
        return result;
    }
}
