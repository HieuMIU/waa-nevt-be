package nevt.dto.car;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Collection;

public class CarDTO {

    private String productNumber;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Min(value = 0)
    private Double basePrice;

    @NotEmpty
    @Size(min = 2)
    private String description;

    @NotNull
    @Min(value = 1900)
    private Integer year;

    @NotEmpty
    @Size(min = 2)
    private String model;

    @NotEmpty
    @Size(min = 2)
    private String make;

    @NotNull
    @Min(value = 0)
    private Integer stockQuantity;


    private Collection<AttributeTypeDTO> attributeTypes = new ArrayList<>();

    private Collection<String> images = new ArrayList<>();

    public CarDTO(){}

    public CarDTO(String productNumber, String name, Double basePrice, String description, Integer year, String model, String make, Integer stockQuantity, Collection<AttributeTypeDTO> attributeTypes, Collection<String> images) {
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
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

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Collection<AttributeTypeDTO> getAttributeTypes() {
        return attributeTypes;
    }

    public void setAttributeTypes(Collection<AttributeTypeDTO> attributeTypes) {
        this.attributeTypes = attributeTypes;
    }

    public Collection<String> getImages() {
        return images;
    }

    public void setImages(Collection<String> images) {
        this.images = images;
    }
}
