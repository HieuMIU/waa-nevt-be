package nevt.services;

import nevt.common.extensions.GuidGenerator;
import nevt.config.AppConfig;
import nevt.dto.car.AttributeItemDTO;
import nevt.dto.car.AttributeTypeDTO;
import nevt.dto.car.CarDTO;
import nevt.dto.order.OrderDTO;
import nevt.dto.order.OrderItemDTO;
import nevt.models.car.AttributeItem;
import nevt.models.car.AttributeType;
import nevt.models.car.Car;
import nevt.models.order.Order;
import nevt.models.order.OrderItem;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class OrderAdapter {

    @Autowired
    CarAdapter carAdapter;

    public static OrderDTO getOrderDTO(Order order){
        if (order == null) {
            return null;
        }
        return new OrderDTO(
                order.getItems().stream().map(OrderAdapter::getOrderItemDTO).toList(),
                order.getEmail(),
                order.getOrderStatus(),
                order.getTotal());
    }

    private static OrderItemDTO getOrderItemDTO(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        return new OrderItemDTO(
                CarAdapter.getCarDTO(orderItem.getCar()),
                orderItem.getNumber(),
                orderItem.getTotalPrice());
    }

    public static Order getOrder(OrderDTO dto){
        if (dto == null) {
            return null;
        }
        return new Order(
                dto.getItems().stream().map(OrderAdapter::getOrderItem).toList(),
                GuidGenerator.generateGuid(),
                dto.getEmail(),
                dto.getOrderStatus(),
                dto.getTotal());
    }

    private static OrderItem getOrderItem(OrderItemDTO dto) {
        if (dto == null) {
            return null;
        }

        return new OrderItem(
                CarAdapter.getCar(dto.getCar()),
                dto.getNumber(),
                dto.getTotalPrice());
    }
}
