package nevt.controllers;

import nevt.dto.review.ReviewDTO;
import nevt.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

  @Autowired
  private ReviewService reviewService;

  @GetMapping("{productNumber}/{email}")
  public ResponseEntity<?> getRating(@PathVariable String productNumber, @PathVariable String email) {
    ReviewDTO reviewDTO = reviewService.findByProductNumberAndEmail(productNumber, email);
    return new ResponseEntity<ReviewDTO> (reviewDTO, HttpStatus.OK);
  }

  @GetMapping("{productNumber}")
  public ResponseEntity<?> getReviewsByProductNumber(@PathVariable String productNumber) {
    List<ReviewDTO> reviewDTOs = reviewService.findByProductNumber(productNumber);
    return new ResponseEntity<List<ReviewDTO>> (reviewDTOs, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('USER')")
  @PostMapping("")
  public ResponseEntity<?> createRating(@RequestBody ReviewDTO reviewDTO) {
    ReviewDTO createdCarDTO = reviewService.add(reviewDTO);
    return new ResponseEntity<ReviewDTO> (createdCarDTO, HttpStatus.OK);
  }
}
