package nevt.repositories.review;

import nevt.models.review.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    public List<Review> findByProductNumber(String productNumber);

    public Review findByProductNumberAndEmail(String productNumber, String email);

}
