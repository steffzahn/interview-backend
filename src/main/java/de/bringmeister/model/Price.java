package de.bringmeister.model;

public class Price {
    public Price(String sku, int price, Currency currency, Unit unit) {
        this.sku = sku;
        this.price = price;
        this.currency = currency;
        this.unit = unit;
    }

    private String sku;
    private int price;
    private Currency currency;
    private Unit unit;

    public String getSku() {
        return sku;
    }

    public int getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Unit getUnit() {
        return unit;
    }

}
