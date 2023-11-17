package nevt.services;

import nevt.dto.business.AttributeItemDTO;
import nevt.dto.business.AttributeTypeDTO;
import nevt.dto.business.CarDTO;
import nevt.models.business.AttributeItem;
import nevt.models.business.AttributeType;
import nevt.models.business.Car;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CarAdapter {

    private static final String UPLOAD_DIR = "\\uploads";

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
        Collection<String> images = new ArrayList<>();
        String path = "";
        try{
            ClassPathResource resource = new ClassPathResource("");
            Path fullPath = resource.getFile().toPath().toAbsolutePath().getParent().getParent();
            path = fullPath.toString();
        } catch(Exception e){
        }
        String finalPath = path  + UPLOAD_DIR;
        car.getImages().forEach(o -> {
            images.add(finalPath + "\\" + o);
        });
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
