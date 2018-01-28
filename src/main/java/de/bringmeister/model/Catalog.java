package de.bringmeister.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Catalog {

    private static final Logger LOG = LoggerFactory.getLogger(Catalog.class);

    private Map<CatalogKey,Price> skuPriceMap;
    private Map<String,Product> skuProductMap;

    public Catalog()
    {
        skuPriceMap = new HashMap<CatalogKey,Price>();
        skuProductMap = new HashMap<String,Product>();
    }

    public synchronized void clear()
    {
        skuPriceMap.clear();
        skuProductMap.clear();
    }

    public synchronized void addProducts( List<Product> list, boolean merge ) throws ModelException
    {
        if( list==null )
            throw new ModelException("Catalog.addProducts(): missing mandatory parameter list");
        for( Product p : list )
        {
            if( p == null )
                throw new ModelException("Catalog.addProducts(): null pointer in list");
            String sku = p.getSku();
            if( !merge && skuProductMap.containsKey( sku ) )
            {
                throw new ModelException("Catalog.addProducts(): duplicate sku value");
            }
            skuProductMap.put( sku, p );
        }
    }

    public synchronized void setPrices( List<Price> list ) throws ModelException
    {
        if( list==null )
            throw new ModelException("Catalog.setPrices(): missing mandatory parameter list");
        for( Price p : list )
        {
            if( p == null )
                throw new ModelException("Catalog.setPrices(): null pointer in list");
            String sku = p.getSku();
            CatalogKey key = new CatalogKey( sku, p.getUnit() );
            if( !skuProductMap.containsKey( sku ) )
            {
                LOG.warn("Catalog.setPrices(): skipping unknown sku "+sku);
            }
            skuPriceMap.put( key, p );
        }
    }
}
