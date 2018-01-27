package de.bringmeister.model;

public class Product {
    public Product(String name, String sku, String description) {
        this.name = name;
        this.sku = sku;
        this.description = description;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String sku;

    public String getSku() {
        return sku;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
