package org.oinkeep.backend.boot.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.util.Objects;
import java.util.Properties;

public class YamlPropertySourceFactory extends DefaultPropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(final String name, final EncodedResource resource) {
        PropertySource<?> result = null;
        final YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(resource.getResource());
        final Properties properties = factoryBean.getObject();

        if( properties != null ){
            result = new PropertiesPropertySource(Objects.requireNonNull(resource.getResource().getFilename()), properties );
        }

        return result;
    }
}