package nevt.repositories.order;

import nevt.models.car.Car;
import nevt.models.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    public List<Order> findByEmail(String email);

    public Order findByOrderId(String orderId);
}
