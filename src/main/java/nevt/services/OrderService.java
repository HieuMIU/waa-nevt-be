package nevt.services;

import nevt.common.constants.OrderStatus;
import nevt.common.extensions.GuidGenerator;
import nevt.dto.order.OrderDTO;
import nevt.models.order.Order;
import nevt.repositories.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO add(OrderDTO orderDTO){
        orderDTO.setOrderId(GuidGenerator.generateGuid());
        orderDTO.setDate(LocalDate.now());
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

    public List<OrderDTO> findByOrderStatusOrderByDateDesc(OrderStatus orderStatus) {
        if(orderStatus == null)
            return orderRepository.findAllByOrderByDateDesc()
                    .stream()
                    .map(OrderAdapter::getOrderDTO).toList();

        return orderRepository.findAllByOrderStatusOrderByDateDesc(orderStatus)
                .stream()
                .map(OrderAdapter::getOrderDTO).toList();
    }

    public OrderDTO shipOrder(String orderId) {
        //Todo: handle business error
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(orderOptional.isPresent())
        {
            Order order = orderOptional.get();
            if(order.getOrderStatus().equals(OrderStatus.PLACED)){
                order.setOrderStatus(OrderStatus.SHIPPED);
                orderRepository.save(order);
                return OrderAdapter.getOrderDTO(order);
            }
        }
        return null;
    }

    public OrderDTO deliverOrder(String orderId) {
        //Todo: handle business error
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(orderOptional.isPresent())
        {
            Order order = orderOptional.get();
            if(order.getOrderStatus().equals(OrderStatus.SHIPPED)){
                order.setOrderStatus(OrderStatus.DELIVERED);
                orderRepository.save(order);
                return OrderAdapter.getOrderDTO(order);
            }
        }
        return null;
    }
}
