package nevt.services;

import nevt.common.extensions.GuidGenerator;
import nevt.dto.car.CarDTO;
import nevt.dto.order.OrderDTO;
import nevt.models.car.Car;
import nevt.repositories.car.CarRepository;
import nevt.repositories.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO add(OrderDTO orderDTO){
        orderRepository.save(OrderAdapter.getOrder(orderDTO));
        return orderDTO;
    }

    public OrderDTO findByOrderId(String orderId){
        return OrderAdapter.getOrderDTO(orderRepository.findByOrderId(orderId));
    }

    public List<OrderDTO> findByEmail(String email) {
        return orderRepository.findByEmail(email)
                .stream()
                .map(OrderAdapter::getOrderDTO).toList();
    }
}
