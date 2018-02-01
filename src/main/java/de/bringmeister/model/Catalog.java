package de.bringmeister.model;

import de.bringmeister.model.json.JsonProductList;
import de.bringmeister.model.json.JsonProductPrice;
import de.bringmeister.model.json.JsonProductWithAllPrices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Catalog {

    private static final Logger LOG = LoggerFactory.getLogger(Catalog.class);

    private Map<CatalogKey, Price> skuPriceMap;
    private Map<String, Product> skuProductMap;

    public Catalog() {
        skuPriceMap = new HashMap<CatalogKey, Price>();
        skuProductMap = new HashMap<String, Product>();
    }

    public synchronized void clear() {
        skuPriceMap.clear();
        skuProductMap.clear();
    }

    public synchronized void addProducts(List<Product> list, boolean merge) throws ModelException {
        if (list == null)
            throw new ModelException("Catalog.addProducts(): missing mandatory parameter list");
        for (Product p : list) {
            if (p == null)
                throw new ModelException("Catalog.addProducts(): null pointer in list");
            String sku = p.getSku();
            if (!merge && skuProductMap.containsKey(sku)) {
                throw new ModelException("Catalog.addProducts(): duplicate sku value");
            }
            skuProductMap.put(sku, p);
        }
    }

    public synchronized void setPrices(List<Price> list) throws ModelException {
        if (list == null)
            throw new ModelException("Catalog.setPrices(): missing mandatory parameter list");
        for (Price p : list) {
            if (p == null)
                throw new ModelException("Catalog.setPrices(): null pointer in list");
            String sku = p.getSku();
            CatalogKey key = new CatalogKey(sku, p.getUnit());
            if (!skuProductMap.containsKey(sku)) {
                LOG.warn("Catalog.setPrices(): skipping unknown sku " + sku);
            }
            skuPriceMap.put(key, p);
        }
    }

    public synchronized JsonProductList listAll() {
        return new JsonProductList(
                skuProductMap.values().stream()
                        .map(p -> new JsonProductWithAllPrices(p))
                        .collect(ArrayList<JsonProductWithAllPrices>::new,
                                ArrayList<JsonProductWithAllPrices>::add,
                                ArrayList<JsonProductWithAllPrices>::addAll));
    }

    public synchronized JsonProductWithAllPrices showProduct(String sku) {
        Product p = skuProductMap.get(sku);
        if (p != null) {
            JsonProductWithAllPrices result = new JsonProductWithAllPrices(p);
            ArrayList<JsonProductPrice> priceList = new ArrayList<>();
            for (Unit unit : Unit.values()) {
                CatalogKey key = new CatalogKey(sku, unit);
                Price price = skuPriceMap.get(key);
                if (price != null) {
                    priceList.add(new JsonProductPrice(price, unit));
                }
            }
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
                return new JsonProductPrice(price, unitStr);
            }
        }
        return null;
    }

}
