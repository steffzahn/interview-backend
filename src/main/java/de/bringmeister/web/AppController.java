package de.bringmeister.web;

import de.bringmeister.model.Catalog;
import de.bringmeister.model.json.JsonProductList;
import de.bringmeister.model.json.JsonProductPrice;
import de.bringmeister.model.json.JsonProductWithAllPrices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("unused")
@RestController
public class AppController {


    @SuppressWarnings("unused")
    @Autowired
    private Catalog catalog;

    private static final Logger LOG = LoggerFactory.getLogger(AppConfiguration.class);

    @SuppressWarnings("unused")
    public AppController() {
        LOG.info("AppController.AppController() started");
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = "/listAll", method = { RequestMethod.GET }, produces = "application/json")
    @ResponseBody
    public JsonProductList listAll(HttpServletRequest httpRequest, HttpServletResponse httpResponse )
    {
        LOG.info("AppController.listAll(): called");
        return catalog.listAll();
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = "/showProduct/{sku}", method = { RequestMethod.GET }, produces = "application/json")
    @ResponseBody
    public JsonProductWithAllPrices showProduct(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                                @PathVariable String sku )
    {
        JsonProductWithAllPrices result = null;
        LOG.info("AppController.showProduct(): called, sku="+sku);
        if( sku!=null )
            result = catalog.showProduct(sku);
        if( result==null )
        {
            try {
                httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND, "showProduct: item not found");
            } catch( IOException ignore ){} // best effort error handling
        }
        return result;
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = "/showPrice/{sku}/{unit}", method = { RequestMethod.GET }, produces = "application/json")
    @ResponseBody
    public JsonProductPrice showPrice(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                      @PathVariable String sku, @PathVariable String unit )
    {
        JsonProductPrice result = null;
        LOG.info("AppController.showPrice(): called, sku="+sku+", unit="+unit);
        if( (sku!=null) && (unit!=null) )
        {
            result = catalog.showPrice( sku, unit );
        }
        if( result==null )
        {
            try {
                httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND, "showPrice: item not found");
            } catch( IOException ignore ){} // best effort error handling
        }
        return result;
    }
}
