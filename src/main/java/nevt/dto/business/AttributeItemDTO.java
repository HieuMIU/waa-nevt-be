package nevt.dto.business;

public class AttributeItemDTO {
    private String value;

    private double addtionalPrice;

    public AttributeItemDTO(String value, double addtionalPrice) {
        this.value = value;
        this.addtionalPrice = addtionalPrice;
    }

    public AttributeItemDTO() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getAddtionalPrice() {
        return addtionalPrice;
    }

    public void setAddtionalPrice(double addtionalPrice) {
        this.addtionalPrice = addtionalPrice;
    }
}
