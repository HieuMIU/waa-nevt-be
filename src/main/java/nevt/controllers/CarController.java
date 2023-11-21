package nevt.controllers;

import jakarta.validation.Valid;
import nevt.common.exceptionhandler.CustomErrorType;
import nevt.dto.car.CarDTO;
import nevt.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
  public ResponseEntity<?> createCar(@RequestBody @Validated CarDTO carDTO) {
    CarDTO createdCarDTO = carService.add(carDTO);
    return new ResponseEntity<CarDTO> (createdCarDTO, HttpStatus.OK);
  }

  @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
  @PutMapping("/{productNumber}")
  public ResponseEntity<?> updateCar(@PathVariable(value = "productNumber") String productNumber, @RequestBody @Validated CarDTO carDTO) {
    CarDTO referenceCarDTO = carService.findByProductNumber(productNumber);
    if (referenceCarDTO == null) {
      return new ResponseEntity<CustomErrorType>(new CustomErrorType("Car with product Number = " + productNumber + " is not available"), HttpStatus.NOT_FOUND);
    }
    carDTO.setProductNumber(productNumber);
    CarDTO updatedCarDTO = carService.update(carDTO);
    return new ResponseEntity<CarDTO> (updatedCarDTO, HttpStatus.OK);
  }

  @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
  @DeleteMapping("/{productNumber}")
  public ResponseEntity<?> deleteCar(@PathVariable(value = "productNumber") String productNumber) {
    CarDTO referenceCarDTO = carService.findByProductNumber(productNumber);
    if (referenceCarDTO == null) {
      return new ResponseEntity<CustomErrorType>(new CustomErrorType("Car with product Number = " + productNumber + " is not available"), HttpStatus.NOT_FOUND);
    }
    carService.remove(productNumber);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
