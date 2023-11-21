package nevt.services;


import nevt.dto.review.ReviewDTO;
import nevt.models.review.Review;
import org.springframework.beans.BeanUtils;


public class ReviewAdapter {
    
    public static ReviewDTO getRatingDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        BeanUtils.copyProperties(review, reviewDTO);
        return reviewDTO;
    }

    public static Review getRating(ReviewDTO reviewDTO){
        Review review = new Review();
        BeanUtils.copyProperties(reviewDTO, review);
        return review;
    }
}
