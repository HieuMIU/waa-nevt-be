package nevt.dto.business;

public class AttributeItemDTO {
    private String value;

    private double additionalPrice;

    public AttributeItemDTO(String value, double addtionalPrice) {
        this.value = value;
        this.additionalPrice = addtionalPrice;
    }

    public AttributeItemDTO() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(double addtionalPrice) {
        this.additionalPrice = addtionalPrice;
    }
}
