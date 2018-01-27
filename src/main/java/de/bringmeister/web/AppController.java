package de.bringmeister.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfiguration.class);

    public AppController() {
        LOG.info("AppController.AppController() started");
    }

    @RequestMapping("/home")
    String home()
    {
        return "Hello world!";
    }

}
