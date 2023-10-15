package org.oinkeep.backend.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(
        value = { "classpath:application.yaml" },
        factory = YamlPropertySourceFactory.class
)
public class ApplicationConfig {}

