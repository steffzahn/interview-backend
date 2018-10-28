package de.bringmeister;

import de.bringmeister.model.Catalog;
import de.bringmeister.model.ModelException;
import de.bringmeister.model.io.PriceReader;
import de.bringmeister.model.io.ProductReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @SuppressWarnings("unused")
    @Autowired
    private Catalog catalog;

    private void loadCatalog() throws ModelException
    {
        LOG.info("Application.loadCatalog(): started");
        catalog.addProducts(ProductReader.readFromResource("/products/products.xml"), false );
        catalog.setPrices(PriceReader.readFromResource("/products/prices.json") );
        LOG.info("Application.loadCatalog(): finished");
    }

    @PostConstruct
    public void init() throws ModelException
    {
        loadCatalog();
    }

    public static void main(final String[] args) throws ModelException
    {

        LOG.info("Application.main(): started");

        SpringApplication.run(Application.class, args);

        LOG.info("Application.main(): finished. Process expected to continue from here.");
    }
}
