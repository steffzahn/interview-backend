package de.bringmeister;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(value = "de.bringmeister")
public class ApplicationProperties {

    @SuppressWarnings("unused")
    public int getHttpPort() {
        return httpPort;
    }

    @SuppressWarnings("unused")
    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    private int httpPort;

    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "httpPort=" + httpPort +
                '}';
    }
}
