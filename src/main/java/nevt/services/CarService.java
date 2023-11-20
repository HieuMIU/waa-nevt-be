package nevt.services;

import nevt.common.extensions.GuidGenerator;
import nevt.dto.car.CarDTO;
import nevt.models.car.Car;
import nevt.repositories.car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public CarDTO add(CarDTO carDTO){
        carDTO.setProductNumber(GuidGenerator.generateGuid());
        carRepository.save(CarAdapter.getCar(carDTO));
        return carDTO;
    }

    public CarDTO update(CarDTO carDTO){
        carRepository.save(CarAdapter.getCar(carDTO));
        return carDTO;
    }

    public CarDTO findByProductNumber(String productNumber){
        Optional<Car> carOptional = carRepository.findById(productNumber);
        if (carOptional.isPresent()){
            return CarAdapter.getCarDTO(carOptional.get());
        }
        else{
            return null;
        }
    }

    public List<CarDTO> getTop6CarsOrderedByStockQuantity() {
        List<Car> cars = carRepository.findTop6ByOrderByStockQuantityDesc();
        return CarAdapter.getCarDTOList(cars);
    }

    public List<CarDTO> getTop6ByOrderByBasePriceAsc() {
        List<Car> cars = carRepository.findTop6ByOrderByBasePriceAsc();
        return CarAdapter.getCarDTOList(cars);
    }

    public List<CarDTO> getTop6ByOrderByYearDesc() {
        List<Car> cars = carRepository.findTop6ByOrderByYearDesc();
        return CarAdapter.getCarDTOList(cars);
    }
}
