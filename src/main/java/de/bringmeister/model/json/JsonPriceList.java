package de.bringmeister.model.json;

import java.util.ArrayList;

public class JsonPriceList extends JsonBase{
    private ArrayList<JsonPrice> list;

    public ArrayList<JsonPrice> getList() {
        return list;
    }

    public void setList(ArrayList<JsonPrice> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "JsonPriceList{" +
                "list=" + list +
                '}';
    }
}
