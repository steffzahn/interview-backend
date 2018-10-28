package de.bringmeister.model;

public class Product {
    public Product(String name, String sku, String description) throws ModelException
    {
        if( (name == null) || (name.length()==0)
            || (sku==null) || (sku.length()==0) )
        {
            throw new ModelException("Product.Product(): name and sku must be non-empty strings");
        }
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

    @SuppressWarnings("unused")
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
