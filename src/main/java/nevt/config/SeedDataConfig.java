package nevt.config;

import nevt.common.extensions.GuidGenerator;
import nevt.dto.business.AttributeItemDTO;
import nevt.dto.business.AttributeTypeDTO;
import nevt.dto.business.CarDTO;
import nevt.models.business.AttributeItem;
import nevt.models.business.AttributeType;
import nevt.models.business.Car;
import nevt.repositories.business.CarRepository;
import nevt.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SeedDataConfig implements CommandLineRunner {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarService carService;

    @Override
    public void run(String... args) throws Exception {

        Collection<AttributeTypeDTO> types = new ArrayList<>();
        List<AttributeItemDTO> items = new ArrayList<>();
        items.add(new AttributeItemDTO("red", 0));
        items.add(new AttributeItemDTO("purple", 550));
        List<AttributeItemDTO> items2 = new ArrayList<>();
        items2.add(new AttributeItemDTO("19 wheels", 0));
        items2.add(new AttributeItemDTO("21 wheels", 645));
        types.add(new AttributeTypeDTO("Paint",items));
        types.add(new AttributeTypeDTO("Wheels", items2));

        Collection<String> images = new ArrayList<>();
        images.add("616940_1.jpg");
        images.add("616940_2.jpg");
        images.add("616940_3.jpg");
        images.add("616940_4.jpg");
        images.add("616940_5.jpg");

        if (carRepository.count() == 0) {
            CarDTO carDTO = new CarDTO(GuidGenerator.generateGuid(),
                    "Toyota Prius 2006",
                    21000D,
                    "Clean hybrid efficent vehicle. 50 mpg. you save a lot of money and the renting price is cheapest here in Fairfield",
                    2006,
                    "Prius",
                    "Toyota",
                    4,
                    types,
                    images);
            carService.add(carDTO);

            images = Arrays.asList(
                    "616944_1.jpg",
                    "616944_2.jpg",
                    "616944_3.jpg",
                    "616944_4.jpg");

            carDTO = new CarDTO(GuidGenerator.generateGuid(),
                    "Toyota Camry 2012",
                    23000D,
                    "Sleek and stylish, the 2012 Toyota Camry is a standout choice for your travel needs. With a modern design and reliable performance, this Camry offers a comfortable ride and excellent fuel efficiency. Affordable and available for rent in Fairfield, it's the perfect option for those seeking a combination of style and practicality.",
                    2012,
                    "Camry",
                    "Toyota",
                    7,
                    types,
                    images);


            carService.add(carDTO);

            images = Arrays.asList(
                    "616920_1.jpg",
                    "616920_2.jpg",
                    "616920_3.jpg",
                    "616920_4.jpg"
            );
            carDTO = new CarDTO(GuidGenerator.generateGuid(),
                    "Honda Accord 2023",
                    40000D,
                    "Experience luxury and performance with the 2023 Honda Accord. This latest model boasts cutting-edge features, a stunning design, and powerful performance on the road. Available for rent in Ottumwa, the 2023 Accord is perfect for those who appreciate the finer things in life and want to make a statement on the road.",
                    2023,
                    "Accord",
                    "Honda",
                    3,
                    types,
                    images);
            carService.add(carDTO);

            images = Arrays.asList(
                    "616949_1.jpg",
                    "616949_2.jpg",
                    "616949_3.jpg",
                    "616949_4.jpg",
                    "616949_5.jpg"
            );
            carDTO = new CarDTO(GuidGenerator.generateGuid(),
                    "Toyota Corolla 2023",
                    40000D,
                    "Enjoy a smooth and comfortable ride with the 2018 Toyota Corolla. This well-maintained vehicle is both reliable and affordable, making it an excellent choice for your travels in Fairfield. With a sleek design and efficient fuel economy, the Corolla is a practical and stylish option for those looking for a hassle-free rental experience.",
                    2023,
                    "Corolla",
                    "Toyota",
                    3,
                    types,
                    images);
            carService.add(carDTO);

            images = Arrays.asList(
                    "616943_1.jpg",
                    "616943_2.jpg",
                    "616943_3.jpg",
                    "616943_4.jpg"
            );
            carDTO = new CarDTO(GuidGenerator.generateGuid(),
                    "Toyota RAV4 2021",
                    40000D,
                    "Embark on your journey with the 2021 Toyota RAV4, a versatile and reliable SUV. With its modern design and advanced features, the RAV4 is perfect for both city driving and off-road adventures. Available for rent in Ottumwa, this SUV offers a comfortable and spacious interior, making it an ideal choice for families and adventurers alike.",
                    2023,
                    "RAV1",
                    "Toyota",
                    3,
                    types,
                    images);
            carService.add(carDTO);

            carDTO = new CarDTO(GuidGenerator.generateGuid(),
                    "Toyota Prius 2022",
                    45000D,
                    "Clean hybrid efficent vehicle. 50 mpg. you save a lot of money and the renting price is cheapest here in Fairfield",
                    2022,
                    "Prius",
                    "Toyota",
                    2,
                    types,
                    images);
            carService.add(carDTO);

            carDTO = new CarDTO(GuidGenerator.generateGuid(),
                    "Toyota Camry 2013",
                    23000D,
                    "Sleek and stylish, the 2012 Toyota Camry is a standout choice for your travel needs. With a modern design and reliable performance, this Camry offers a comfortable ride and excellent fuel efficiency. Affordable and available for rent in Fairfield, it's the perfect option for those seeking a combination of style and practicality.",
                    2013,
                    "Camry",
                    "Toyota",
                    7,
                    types,
                    images);
            carService.add(carDTO);

            carDTO = new CarDTO(GuidGenerator.generateGuid(),
                    "Honda Accord 2013",
                    23000D,
                    "Experience luxury and performance with the 2023 Honda Accord. This latest model boasts cutting-edge features, a stunning design, and powerful performance on the road. Available for rent in Ottumwa, the 2023 Accord is perfect for those who appreciate the finer things in life and want to make a statement on the road.",
                    2013,
                    "Accord",
                    "Honda",
                    8,
                    types,
                    images);
            carService.add(carDTO);

            carDTO = new CarDTO(GuidGenerator.generateGuid(),
                    "Toyota Corolla 2020",
                    43000D,
                    "Enjoy a smooth and comfortable ride with the 2018 Toyota Corolla. This well-maintained vehicle is both reliable and affordable, making it an excellent choice for your travels in Fairfield. With a sleek design and efficient fuel economy, the Corolla is a practical and stylish option for those looking for a hassle-free rental experience.",
                    2020,
                    "Corolla",
                    "Toyota",
                    2,
                    types,
                    images);
            carService.add(carDTO);

            carDTO = new CarDTO(GuidGenerator.generateGuid(),
                    "Toyota RAV3 2022",
                    49000D,
                    "Embark on your journey with the 2021 Toyota RAV4, a versatile and reliable SUV. With its modern design and advanced features, the RAV4 is perfect for both city driving and off-road adventures. Available for rent in Ottumwa, this SUV offers a comfortable and spacious interior, making it an ideal choice for families and adventurers alike.",
                    2023,
                    "RAV3",
                    "Toyota",
                    2,
                    types,
                    images);
            carService.add(carDTO);
        }
    }

}

