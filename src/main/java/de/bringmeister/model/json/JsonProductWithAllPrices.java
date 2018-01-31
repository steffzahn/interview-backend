package de.bringmeister.model.json;

import de.bringmeister.model.Product;

import java.util.ArrayList;

public class JsonProductWithAllPrices {
    public JsonProductWithAllPrices(String name, String sku, String description) {
        this.name = name;
        this.sku = sku;
        this.description = description;
    }
    public JsonProductWithAllPrices(Product p) {
        this.name = p.getName();
        this.sku = p.getSku();
        this.description = p.getDescription();
    }
    public JsonProductWithAllPrices() {
    }

    private String name;
    private String sku;
    private String description;
    private ArrayList<JsonProductPrice> priceList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<JsonProductPrice> getPriceList() {
        return priceList;
    }

    public void setPriceList(ArrayList<JsonProductPrice> priceList) {
        this.priceList = priceList;
    }

    @Override
    public String toString() {
        return "JsonProductWithAllPrices{" +
                "name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", description='" + description + '\'' +
                ", priceList=" + priceList +
                '}';
    }
}
