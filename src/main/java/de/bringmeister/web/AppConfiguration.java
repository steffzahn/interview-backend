package de.bringmeister.web;

import de.bringmeister.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfiguration.class);

    public AppConfiguration() {
        LOG.info("AppConfiguration.AppConfiguration() started");
    }

    @SuppressWarnings("unused")
    @Autowired
    private ApplicationProperties config;
}
