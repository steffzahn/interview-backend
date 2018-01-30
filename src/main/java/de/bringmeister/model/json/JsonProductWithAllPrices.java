package de.bringmeister.model.json;

import de.bringmeister.model.Product;

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

    @Override
    public String toString() {
        return "JsonProductWithAllPrices{" +
                "name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
