package nevt.repositories.business;

import nevt.models.business.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {
    List<Car> findTop6ByOrderByStockQuantityDesc();

    List<Car> findTop6ByOrderByYearDesc();

    List<Car> findTop6ByOrderByBasePriceAsc();
}
