package org.oinkeep.backend.boot.config;

import com.sun.tools.javac.Main;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

@Configuration
@PropertySource(
        value = { "classpath:application.yaml" },
        factory = YamlPropertySourceFactory.class
)
@NoArgsConstructor(force = true)
public class PropertyConfig {

    private final String configFile;

    public PropertyConfig(final String configFile) {
        this.configFile = configFile;
    }

    public Map<String, Object> getProperties() {
        try (final InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(configFile)) {
            final Yaml yaml = new Yaml();
            return yaml.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

