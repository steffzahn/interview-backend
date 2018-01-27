package de.bringmeister;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args) {

        LOG.info("Application.main(): started");

        SpringApplication.run(Application.class, args);

        LOG.info("Application.main(): finished. Process expected to continue from here.");
    }
}
