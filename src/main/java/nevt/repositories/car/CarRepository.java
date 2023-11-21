package nevt.repositories.car;

import nevt.models.car.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {
    List<Car> findTop6ByOrderByStockQuantityDesc();

    List<Car> findTop6ByOrderByYearDesc();

    List<Car> findTop6ByOrderByBasePriceAsc();

    List<Car> findCarsByNameContainsIgnoreCase(String searchKey);
}
