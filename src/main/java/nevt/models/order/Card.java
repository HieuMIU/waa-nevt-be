package nevt.models.order;

public class Card {
    private String type;
    private String number;
    private String validDate;
    private String validCode;

    public Card(String type, String number, String validDate, String validCode) {
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
