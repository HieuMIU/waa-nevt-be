package nevt.models.car;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Document
public class Car {

    @Id
    private String productNumber;

    private String name;

    private Double basePrice;

    private String description;

    private int year;

    private String model;

    private String make;

    private int stockQuantity;

    private Collection<AttributeType> attributeTypes = new ArrayList<AttributeType>();

    private Collection<String> images = new ArrayList<>();

    public Car() {

    }

    public Car(String productNumber, String name, Double basePrice, String description, int year, String model, String make, int stockQuantity, Collection<AttributeType> attributeTypes, Collection<String> images) {
        this.productNumber = productNumber;
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
        this.year = year;
        this.model = model;
        this.make = make;
        this.stockQuantity = stockQuantity;
        this.attributeTypes = attributeTypes;
        this.images = images;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Collection<AttributeType> getAttributeTypes() {
        return attributeTypes;
    }

    public void setAttributeTypes(Collection<AttributeType> attributeTypes) {
        this.attributeTypes = attributeTypes;
    }

    public Collection<String> getImages() {
        return images;
    }

    public void setImages(Collection<String> images) {
        this.images = images;
    }
}
