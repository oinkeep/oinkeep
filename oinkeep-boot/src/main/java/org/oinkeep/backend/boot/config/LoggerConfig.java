package org.oinkeep.backend.boot.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class LoggerConfig {

    private final Map<String, Object> properties;

    public LoggerConfig(final Map<String, Object> properties) {
        this.properties = properties;
    }

    public void config() {
        if (this.properties != null && this.properties.containsKey("logger") && this.properties.get("logger") instanceof Map) {
            final Map<String, Object> loggingConfig = (Map<String, Object>) this.properties.get("logger");
            if (loggingConfig.containsKey("level") && loggingConfig.get("level") instanceof Map) {
                final Map<String, Object> levelConfig = (Map<String, Object>) loggingConfig.get("level");
                final String rootLogLevel = (String) levelConfig.get("root");

                // Configurar el nivel de log para la raíz
                final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

                // Configurar el nivel de log para un paquete específico (puedes ajustar según tus necesidades)
                final ch.qos.logback.classic.Logger result = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
                result.setLevel(Level.valueOf(rootLogLevel));
            }
        }
    }
}
