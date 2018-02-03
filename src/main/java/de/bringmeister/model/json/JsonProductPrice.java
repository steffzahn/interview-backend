package de.bringmeister.model.json;

import de.bringmeister.model.Currency;
import de.bringmeister.model.Price;
import de.bringmeister.model.Unit;

public class JsonProductPrice {
    public JsonProductPrice(String value, String currency, String unitStr) {
        this.value = value;
        this.currency = currency;
        this.unit = unitStr;
    }
    public JsonProductPrice() {
    }
    public JsonProductPrice( Price p ) {
        int priceValue = p.getPrice();
        value = String.format( "%d.%02d", priceValue / 100, priceValue % 100);
        currency = p.getCurrency().name();
        this.unit = p.getUnit().name();
    }

    private String value;
    private String currency;
    private String unit;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "JsonProductPrice{" +
                "value='" + value + '\'' +
                ", currency='" + currency + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
