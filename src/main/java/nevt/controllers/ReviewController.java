package nevt.controllers;

import nevt.common.exceptionhandler.CustomErrorType;
import nevt.dto.review.ReviewDTO;
import nevt.services.OrderService;
import nevt.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

  @Autowired
  private ReviewService reviewService;

  @Autowired
  private OrderService orderService;

  @GetMapping("{productNumber}/{email}")
  public ResponseEntity<?> getReview(@PathVariable String productNumber, @PathVariable String email) {
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
  public ResponseEntity<?> createReview(@RequestBody ReviewDTO reviewDTO) {

    if(!orderService.hasOrderCar(reviewDTO.getEmail(), reviewDTO.getProductNumber())) {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType("User have not ordered this car yet/ Your order does not delivery."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    ReviewDTO createdCarDTO = reviewService.add(reviewDTO);
    return new ResponseEntity<ReviewDTO> (createdCarDTO, HttpStatus.OK);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
    System.out.println("******************************************");
    System.out.println(ex.getBindingResult().getFieldErrors());
    Map<String, Object> fieldError = new HashMap<>();
    List<FieldError> fieldErrors= ex.getBindingResult().getFieldErrors();
    for (FieldError error : fieldErrors) {
      fieldError.put(error.getField(), error.getDefaultMessage());
    }

    Map<String, Object> map = new HashMap<>();
    map.put("isSuccess", false);
    map.put("data", null);
    map.put("status", HttpStatus.BAD_REQUEST);
    map.put("fieldError", fieldError);
    return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
  }
}
