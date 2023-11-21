package nevt.dto.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CardDTO {

    @NotEmpty
    @Size(min = 2, max = 20)
    private String type;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String number;

    @NotEmpty
    private String validDate;

    @NotEmpty
    private String validCode;

    public CardDTO(String type, String number, String validDate, String validCode) {
        this.type = type;
        this.number = number;
        this.validDate = validDate;
        this.validCode = validCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    @Override
    public String toString() {
        return "Card{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                ", validDate='" + validDate + '\'' +
                ", validCode='" + validCode + '\'' +
                '}';
    }
}
