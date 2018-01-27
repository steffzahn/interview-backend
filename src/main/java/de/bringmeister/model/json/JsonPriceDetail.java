package de.bringmeister.model.json;

public class JsonPriceDetail extends JsonBase {

    private float value;
    private String currency;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
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
        return "JsonPriceDetail{" +
                "value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}
