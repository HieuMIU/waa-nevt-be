package nevt.controllers;

import nevt.common.CustomErrorType;
import nevt.dto.car.CarDTO;
import nevt.dto.rating.RatingDTO;
import nevt.services.CarService;
import nevt.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ratings")
@CrossOrigin(origins = "*")
public class RatingController {

  @Autowired
  private RatingService ratingService;

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/{productNumber}/{email}")
  public ResponseEntity<?> getRating(@PathVariable String productNumber, @PathVariable String email) {
    RatingDTO ratingDTO = ratingService.findByProductNumberAndEmail(productNumber, email);
    return new ResponseEntity<RatingDTO> (ratingDTO, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('USER')")
  @PostMapping("")
  public ResponseEntity<?> createRating(@RequestBody RatingDTO ratingDTO) {
    RatingDTO createdCarDTO = ratingService.add(ratingDTO);
    return new ResponseEntity<RatingDTO> (createdCarDTO, HttpStatus.OK);
  }
}
