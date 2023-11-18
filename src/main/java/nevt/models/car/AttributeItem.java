package nevt.models.car;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AttributeItem {

    private String value;

    private double additionalPrice;

    public AttributeItem(String value, double addtionalPrice) {
        this.value = value;
        this.additionalPrice = addtionalPrice;
    }

    public AttributeItem() {}

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
