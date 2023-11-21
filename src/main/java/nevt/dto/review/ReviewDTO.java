package nevt.dto.review;

public class ReviewDTO {
    private String id;

    private String email;

    private String productNumber;

    private String comment;

    public ReviewDTO() {}

    public ReviewDTO(String id, String email, String productNumber, String comment) {
        this.id = id;
        this.email = email;
        this.productNumber = productNumber;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}