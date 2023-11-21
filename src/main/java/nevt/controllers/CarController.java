package nevt.controllers;

import nevt.common.CustomErrorType;
import nevt.dto.car.CarDTO;
import nevt.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*")
public class CarController {

  @Autowired
  private CarService carService;

  @GetMapping("")
  public ResponseEntity<?> filterCars(@RequestParam(required = false) String searchKey) {
    List<CarDTO> carDTOs = carService.filterCarByName(searchKey);
    return new ResponseEntity<List<CarDTO>> (carDTOs, HttpStatus.OK);
  }

  @GetMapping("/todaypick")
  public ResponseEntity<?> getTodayPickCars() {
    List<CarDTO> carDTOs = carService.getTop6CarsOrderedByStockQuantity();
    return new ResponseEntity<List<CarDTO>> (carDTOs, HttpStatus.OK);
  }

  @GetMapping("/trending")
  public ResponseEntity<?> getTrendingCars() {
    List<CarDTO> carDTOs = carService.getTop6ByOrderByYearDesc();
    return new ResponseEntity<List<CarDTO>> (carDTOs, HttpStatus.OK);
  }

  @GetMapping("/affordable")
  public ResponseEntity<?> getAffordableCars() {
    List<CarDTO> carDTOs = carService.getTop6ByOrderByBasePriceAsc();
    return new ResponseEntity<List<CarDTO>> (carDTOs, HttpStatus.OK);
  }

  @GetMapping("/{productNumber}")
  public ResponseEntity<?> getCar(@PathVariable String productNumber) {
    CarDTO carDTO = carService.findByProductNumber(productNumber);
    if (carDTO == null) {
      return new ResponseEntity<CustomErrorType>(new CustomErrorType("Car with product Number = " + productNumber + " is not available"), HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<CarDTO> (carDTO, HttpStatus.OK);
  }

  @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
  @PostMapping("")
  public ResponseEntity<?> handlePost(@RequestBody CarDTO carDTO) {
    CarDTO createdCarDTO = carService.add(carDTO);
    return new ResponseEntity<CarDTO> (createdCarDTO, HttpStatus.OK);
  }
}
