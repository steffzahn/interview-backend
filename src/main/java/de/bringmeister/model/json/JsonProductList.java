package de.bringmeister.model.json;

import java.util.ArrayList;

public class JsonProductList extends JsonBase {
    public JsonProductList(ArrayList<JsonProductWithAllPrices> list) {
        this.list = list;
    }
    @SuppressWarnings("unused")
    public JsonProductList() {
    }

    private ArrayList<JsonProductWithAllPrices> list;

    public ArrayList<JsonProductWithAllPrices> getList() {
        return list;
    }

    public void setList(ArrayList<JsonProductWithAllPrices> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "JsonProductList{" +
                "list=" + list +
                '}';
    }
}
