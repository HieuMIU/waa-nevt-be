package nevt.models.business;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Document
public class AttributeType {

    private String type;

    private Collection<AttributeItem> items = new ArrayList<>();

    public AttributeType(String type, List<AttributeItem> items) {
        this.type = type;
        this.items = items;
    }

    public AttributeType() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<AttributeItem> getItems() {
        return items;
    }

    public void setItems(Collection<AttributeItem> items) {
        this.items = items;
    }
}
