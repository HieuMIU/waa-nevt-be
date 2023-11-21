package nevt.dto.car;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AttributeItemDTO {

    @NotEmpty
    private String value;

    @NotNull
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
