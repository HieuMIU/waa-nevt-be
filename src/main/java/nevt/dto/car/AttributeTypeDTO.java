package nevt.dto.car;

import java.util.ArrayList;
import java.util.Collection;

public class AttributeTypeDTO {
    private String type;

    private Collection<AttributeItemDTO> items = new ArrayList<>();

    public AttributeTypeDTO(String type, Collection<AttributeItemDTO> items) {
        this.type = type;
        this.items = items;
    }

    public AttributeTypeDTO() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<AttributeItemDTO> getItems() {
        return items;
    }

    public void setItems(Collection<AttributeItemDTO> items) {
        this.items = items;
    }
}
