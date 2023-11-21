package nevt.services;

import nevt.common.extensions.GuidGenerator;
import nevt.dto.rating.RatingDTO;
import nevt.models.rating.Rating;
import nevt.repositories.rating.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public RatingDTO add(RatingDTO ratingDTO){
        ratingDTO.setId(GuidGenerator.generateGuid());
        ratingRepository.save(RatingAdapter.getRating(ratingDTO));
        return ratingDTO;
    }

    public List<RatingDTO> findByProductNumber(String productNumber){
        List<RatingDTO> result = ratingRepository.findByProductNumber(productNumber)
                .stream()
                .map(RatingAdapter::getRatingDTO).toList();
        return result;
    }

    public List<RatingDTO> findByEmail(String email){
        List<RatingDTO> result = ratingRepository.findByEmail(email)
                .stream()
                .map(RatingAdapter::getRatingDTO).toList();
        return result;
    }

    public RatingDTO findByProductNumberAndEmail(String productNumber, String email){
        Rating rating = ratingRepository.findByProductNumberAndEmail(productNumber,email);
        if(rating == null)
            rating = new Rating(GuidGenerator.generateGuid(), email, productNumber, 0, "");
        return RatingAdapter.getRatingDTO(rating);
    }

}
