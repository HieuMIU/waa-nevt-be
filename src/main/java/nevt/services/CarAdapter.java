package nevt.services;

import nevt.dto.business.CarDTO;
import nevt.models.business.Car;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter {
    public static CarDTO getCarDTO(Car car){
        CarDTO carDTO = new CarDTO();
        BeanUtils.copyProperties(car, carDTO);
        return carDTO;
    }

    public static Car getCar(CarDTO carDTO){
        Car car = new Car();
        BeanUtils.copyProperties(carDTO, car);
        return car;
    }

    public static List<CarDTO> getCarDTOList(List<Car> cars){
        List<CarDTO> carDTOList = new ArrayList<CarDTO>();
        if (cars != null){
            for (Car car : cars){
                carDTOList.add(getCarDTO(car));
            }
        }
        return carDTOList;
    }
}
