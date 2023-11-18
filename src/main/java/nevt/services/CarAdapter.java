package nevt.services;

import nevt.config.AppConfig;
import nevt.dto.car.AttributeItemDTO;
import nevt.dto.car.AttributeTypeDTO;
import nevt.dto.car.CarDTO;
import nevt.models.car.AttributeItem;
import nevt.models.car.AttributeType;
import nevt.models.car.Car;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CarAdapter {

    @Autowired
    private static AppConfig appConfig;

    @Autowired
    public CarAdapter(AppConfig appConfig) {
        CarAdapter.appConfig = appConfig;
    }

    public static CarDTO getCarDTO(Car car){
        CarDTO carDTO = new CarDTO();
        BeanUtils.copyProperties(car, carDTO);

        //copy attribute
        Collection<AttributeTypeDTO> types = new ArrayList<>();
        car.getAttributeTypes().forEach(o ->{
            AttributeTypeDTO attributeTypeDTO = new AttributeTypeDTO();
            BeanUtils.copyProperties(o, attributeTypeDTO);
            Collection<AttributeItemDTO> items = new ArrayList<>();
            o.getItems().forEach(i -> {
                AttributeItemDTO attributeItemDTO = new AttributeItemDTO();
                BeanUtils.copyProperties(i, attributeItemDTO);
                items.add(attributeItemDTO);
            });
            attributeTypeDTO.setItems(items);
            types.add(attributeTypeDTO);
        });
        carDTO.setAttributeTypes(types);

        //copy images
        Collection<String> images = new ArrayList<>(car.getImages());
        carDTO.setImages(images);
        return carDTO;
    }

    public static Car getCar(CarDTO carDTO){
        Car car = new Car();
        BeanUtils.copyProperties(carDTO, car);

        //copy attribute
        Collection<AttributeType> types = new ArrayList<>();
        carDTO.getAttributeTypes().forEach(o ->{
            AttributeType attributeType = new AttributeType();
            BeanUtils.copyProperties(o, attributeType);
            Collection<AttributeItem> items = new ArrayList<>();
            o.getItems().forEach(i -> {
                AttributeItem attributeItem = new AttributeItem();
                BeanUtils.copyProperties(i, attributeItem);
                items.add(attributeItem);
            });
            attributeType.setItems(items);
            types.add(attributeType);
        });
        car.setAttributeTypes(types);
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
