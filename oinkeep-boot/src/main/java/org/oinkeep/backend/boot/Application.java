package org.oinkeep.backend.boot;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.oinkeep.backend.boot.config.JettyServerConfig;
import org.oinkeep.backend.boot.config.LoggerConfig;
import org.oinkeep.backend.boot.config.PropertyConfig;
import org.oinkeep.backend.boot.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.Optional;


@Slf4j
public class Application {

    public static void main( final String[] args ) throws Exception {

        // Cargar la configuración desde el archivo YAML
        final PropertyConfig propertyConfig = new PropertyConfig("application.yaml");
        final Map<String, Object> properties = propertyConfig.getProperties();

        // Configurar el nivel de log
        final LoggerConfig loggerConfig = new LoggerConfig(properties);
        loggerConfig.config();

        // Crea el contexto de Spring
        final SpringConfig springConfig = new SpringConfig(properties);
        final ApplicationContext context = springConfig.getApplicationContext();

        final JettyServerConfig jettyServerConfig = new JettyServerConfig((WebApplicationContext) context, properties);
        final Optional<Server> optionalServer = jettyServerConfig.getServer();
        final Server server = optionalServer.orElseThrow();

        // Arranca el servidor
        server.start();
        server.join();
    }
}
