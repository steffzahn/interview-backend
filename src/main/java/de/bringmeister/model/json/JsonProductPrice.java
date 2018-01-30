package de.bringmeister.model.json;

import de.bringmeister.model.Currency;
import de.bringmeister.model.Price;

public class JsonProductPrice {
    public JsonProductPrice(String value, String currency) {
        this.value = value;
        this.currency = currency;
    }
    public JsonProductPrice() {
    }
    public JsonProductPrice(Price p ) {
        int priceValue = p.getPrice();
        value = String.format( "%d.%02d", priceValue / 100, priceValue % 100);
        currency = p.getCurrency().name();
    }

    private String value;
    private String currency;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "JsonProductPrice{" +
                "value='" + value + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
