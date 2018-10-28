package de.bringmeister.model;

import java.util.Objects;

public class CatalogKey {
    CatalogKey(String sku, Unit unit) {
        this.sku = sku;
        this.unit = unit;
    }

    private String sku;
    private Unit unit;

    @SuppressWarnings("unused")
    public String getSku() {
        return sku;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogKey that = (CatalogKey) o;
        return Objects.equals(sku, that.sku) &&
                unit == that.unit;
    }

    @Override
    public int hashCode() {

        return Objects.hash(sku, unit);
    }

    @Override
    public String toString() {
        return "CatalogKey{" +
                "sku='" + sku + '\'' +
                ", unit=" + unit +
                '}';
    }
}
