package nevt.services;


import nevt.dto.rating.RatingDTO;
import nevt.models.rating.Rating;
import org.springframework.beans.BeanUtils;


public class RatingAdapter {
    
    public static RatingDTO getRatingDTO(Rating rating){
        RatingDTO ratingDTO = new RatingDTO();
        BeanUtils.copyProperties(rating, ratingDTO);
        return ratingDTO;
    }

    public static Rating getRating(RatingDTO ratingDTO){
        Rating rating = new Rating();
        BeanUtils.copyProperties(ratingDTO, rating);

        return rating;
    }
}
