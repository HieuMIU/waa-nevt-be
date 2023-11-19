package nevt.services;

import nevt.dto.order.AddressDTO;
import nevt.dto.order.CardDTO;
import nevt.dto.order.OrderDTO;
import nevt.dto.order.OrderItemDTO;
import nevt.models.order.Address;
import nevt.models.order.Card;
import nevt.models.order.Order;
import nevt.models.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAdapter {

    @Autowired
    CarAdapter carAdapter;

    public static OrderDTO getOrderDTO(Order order){
        if (order == null) {
            return null;
        }
        return new OrderDTO(
                order.getOrderId(),
                order.getItems().stream().map(OrderAdapter::getOrderItemDTO).toList(),
                order.getEmail(),
                order.getOrderStatus(),
                order.getDate(),
                order.getTotal(),
                getCardDTO(order.getCard()),
                getAddressDTO(order.getAddress())
                );
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

    private static CardDTO getCardDTO(Card card) {
        if (card == null) {
            return null;
        }

        return new CardDTO(
                card.getType(),
                card.getNumber(),
                card.getValidDate(),
                card.getValidCode());
    }

    private static AddressDTO getAddressDTO(Address address) {
        if (address == null) {
            return null;
        }

        return new AddressDTO(
                address.getName(),
                address.getEmail(),
                address.getPhone(),
                address.getStreet(),
                address.getCity(),
                address.getZip()
                );
    }

    public static Order getOrder(OrderDTO dto){
        if (dto == null) {
            return null;
        }
        return new Order(
                dto.getItems().stream().map(OrderAdapter::getOrderItem).toList(),
                dto.getOrderId(),
                dto.getEmail(),
                dto.getOrderStatus(),
                dto.getTotal(),
                dto.getDate(),
                getCard(dto.getCard()),
                getAddress(dto.getAddress()));
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

    private static Card getCard(CardDTO cardDTO) {
        if (cardDTO == null) {
            return null;
        }

        return new Card(
                cardDTO.getType(),
                cardDTO.getNumber(),
                cardDTO.getValidDate(),
                cardDTO.getValidCode());
    }

    private static Address getAddress(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }

        return new Address(
                addressDTO.getName(),
                addressDTO.getEmail(),
                addressDTO.getPhone(),
                addressDTO.getStreet(),
                addressDTO.getCity(),
                addressDTO.getZip()
        );
    }
}
