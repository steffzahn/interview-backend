package de.bringmeister.model.json;

public class JsonPrice extends JsonBase {

    private String id;
    private JsonPriceDetail price;
    private String unit;

    public JsonPriceDetail getPrice() {
        return price;
    }

    @SuppressWarnings("unused")
    public void setPrice(JsonPriceDetail price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    @SuppressWarnings("unused")
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "JsonPrice{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                '}';
    }
}
