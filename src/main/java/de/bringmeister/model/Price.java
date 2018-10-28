package de.bringmeister.model;

import de.bringmeister.model.json.JsonPrice;
import de.bringmeister.model.json.JsonPriceDetail;

public class Price {
    @SuppressWarnings("unused")
    public Price(String sku, int price, Currency currency, Unit unit) {
        this.sku = sku;
        this.price = price;
        this.currency = currency;
        this.unit = unit;
    }
    // use RuntimeException in case of error to allow usage in Stream<>.map
    public Price(JsonPrice jp)
    {
        sku = jp.getId();
        if( (sku==null) || (sku.length()==0) )
            throw new ModelException("Price.Price(): sku missing or empty");
        JsonPriceDetail jpd = jp.getPrice();
        if( jpd==null )
            throw new ModelException("Price.Price(): price missing");
        float priceFloat = jpd.getValue();
        if( priceFloat < 0.0  )
            throw new ModelException("Price.Price(): price is negative");
        price = (int)( priceFloat * 100);
        String currency = jpd.getCurrency();
        try {
            if (currency != null)
                this.currency = Currency.valueOf(currency);
        }  catch( Exception e )
        {
            this.currency = null;
        }
        if( this.currency == null ) {
            throw new ModelException("Price.Price(): currency is null or empty, or an unknown currency");
        }
        String unit = jp.getUnit();
        try {
            if (unit != null)
                this.unit = Unit.valueOf(unit.toUpperCase());
        }  catch( Exception e )
        {
            this.unit = null;
        }
        if( this.unit == null ) {
            throw new ModelException("Price.Price(): unit is null or empty, or an unknown unit");
        }
    }

    private String sku;
    private int price;
    private Currency currency;
    private Unit unit;

    String getSku() {
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

    @Override
    public String toString() {
        return "Price{" +
                "sku='" + sku + '\'' +
                ", price=" + price +
                ", currency=" + currency +
                ", unit=" + unit +
                '}';
    }
}
