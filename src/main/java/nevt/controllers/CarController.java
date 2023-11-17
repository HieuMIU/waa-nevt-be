package nevt.controllers;

import nevt.common.CustomErrorType;
import nevt.dto.business.CarDTO;
import nevt.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cars")
public class CarController {

  @Autowired
  private CarService carService;

  @GetMapping("/{productNumber}")
  public ResponseEntity<?> getCar(@PathVariable String productNumber) {
    CarDTO carDTO = carService.findByProductNumber(productNumber);
    if (carDTO == null) {
      return new ResponseEntity<CustomErrorType>(new CustomErrorType("Car with product Number = " + productNumber + " is not available"), HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<CarDTO> (carDTO, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('USER')")
  @PostMapping("")
  public ResponseEntity<?> handlePost(@RequestBody CarDTO carDTO) {
    CarDTO createdCarDTO = carService.add(carDTO);
    return new ResponseEntity<CarDTO> (createdCarDTO, HttpStatus.OK);
  }
}