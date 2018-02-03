package de.bringmeister.model;

import de.bringmeister.model.json.JsonProductList;
import de.bringmeister.model.json.JsonProductPrice;
import de.bringmeister.model.json.JsonProductWithAllPrices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Catalog {

    private static final Logger LOG = LoggerFactory.getLogger(Catalog.class);

    private Map<CatalogKey, Price> skuPriceMap;
    private Map<String, Product> skuProductMap;

    public Catalog() {
        skuPriceMap = Collections.synchronizedMap( new HashMap<CatalogKey, Price>() );
        skuProductMap = Collections.synchronizedMap( new HashMap<String, Product>() );
    }

    public synchronized void clear() {
        skuPriceMap.clear();
        skuProductMap.clear();
    }

    public synchronized void addProducts(List<Product> list, boolean merge) throws ModelException {
        if (list == null)
            throw new ModelException("Catalog.addProducts(): missing mandatory parameter list");
        list.stream()
                .map( Objects::requireNonNull )
                .filter( p ->
                {
                    if (!merge && skuProductMap.containsKey(p.getSku())) {
                        throw new ModelException("Catalog.addProducts(): duplicate sku value");
                    }
                    return true;
                })
                .forEach( p -> { skuProductMap.put(p.getSku(), p); } );
    }

    public synchronized void setPrices(List<Price> list) throws ModelException {
        if (list == null)
            throw new ModelException("Catalog.setPrices(): missing mandatory parameter list");
        list.stream()
                .map( Objects::requireNonNull )
                .forEach( p ->
                {
                    String sku = p.getSku();
                    CatalogKey key = new CatalogKey(sku, p.getUnit());
                    if (!skuProductMap.containsKey(sku))
                    {
                        LOG.warn("Catalog.setPrices(): skipping unknown sku " + sku);
                    } else {
                        skuPriceMap.put(key, p);
                    }
                } );
    }

    public synchronized JsonProductList listAll() {
        return new JsonProductList(
                skuProductMap.values().stream()
                        .map(JsonProductWithAllPrices::new)
                        .collect(ArrayList<JsonProductWithAllPrices>::new,
                                ArrayList<JsonProductWithAllPrices>::add,
                                ArrayList<JsonProductWithAllPrices>::addAll));
    }

    public synchronized JsonProductWithAllPrices showProduct(String sku) {
        Product p = skuProductMap.get(sku);
        if (p != null) {
            JsonProductWithAllPrices result = new JsonProductWithAllPrices(p);
            ArrayList<JsonProductPrice> priceList =
                    Arrays.stream(Unit.values())
                            .map( u -> new CatalogKey(sku, u) )
                            .map( k -> skuPriceMap.get(k))
                            .filter( Objects::nonNull )
                            .map( JsonProductPrice::new )
                            .collect(
                                    ArrayList<JsonProductPrice>::new,
                                    ArrayList<JsonProductPrice>::add,
                                    ArrayList<JsonProductPrice>::addAll
                            );
            if (priceList.size() > 0) {
                result.setPriceList(priceList);
            }
            return result;
        }
        return null;
    }

    public synchronized JsonProductPrice showPrice(String sku, String unitStr) {
        Unit unit = null;
        try {
            unit = Unit.valueOf(unitStr.toUpperCase());
        } catch (Exception ignore) {
        }
        if (unit != null) {
            CatalogKey key = new CatalogKey(sku, unit);
            Price price = skuPriceMap.get(key);
            if (price != null) {
                return new JsonProductPrice(price);
            }
        }
        return null;
    }

}
