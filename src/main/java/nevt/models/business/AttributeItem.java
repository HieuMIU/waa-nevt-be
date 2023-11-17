package nevt.models.business;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AttributeItem {

    private String value;

    private double addtionalPrice;

    public AttributeItem(String value, double addtionalPrice) {
        this.value = value;
        this.addtionalPrice = addtionalPrice;
    }

    public AttributeItem() {}

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
