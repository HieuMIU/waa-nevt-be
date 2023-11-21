package nevt.services;

import nevt.common.extensions.GuidGenerator;
import nevt.dto.review.ReviewDTO;
import nevt.models.review.Review;
import nevt.repositories.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewDTO add(ReviewDTO reviewDTO){
        reviewDTO.setId(GuidGenerator.generateGuid());
        reviewRepository.save(ReviewAdapter.getRating(reviewDTO));
        return reviewDTO;
    }

    public List<ReviewDTO> findByProductNumber(String productNumber){
        List<ReviewDTO> result = reviewRepository.findByProductNumber(productNumber)
                .stream()
                .map(ReviewAdapter::getRatingDTO).toList();
        return result;
    }

    public ReviewDTO findByProductNumberAndEmail(String productNumber, String email){
        Review review = reviewRepository.findByProductNumberAndEmail(productNumber,email);
        if(review == null)
            review = new Review("", email, productNumber, "");
        return ReviewAdapter.getRatingDTO(review);
    }

}
