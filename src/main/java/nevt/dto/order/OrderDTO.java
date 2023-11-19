package nevt.dto.order;

import nevt.common.constants.OrderStatus;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class OrderDTO {
    private Collection<OrderItemDTO> items;
    private String email;
    private OrderStatus orderStatus;

    private double total;

    private CardDTO card;

    private AddressDTO address;

    public OrderDTO(Collection<OrderItemDTO> items, String email, OrderStatus orderStatus, double total, CardDTO card, AddressDTO address) {
        this.items = items;
        this.email = email;
        this.orderStatus = orderStatus;
        this.total = total;
        this.card = card;
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(Collection<OrderItemDTO> items) {
        this.items = items;
    }

    public CardDTO getCard() {
        return card;
    }

    public void setCard(CardDTO card) {
        this.card = card;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "items=" + items +
                ", email='" + email + '\'' +
                ", orderStatus=" + orderStatus +
                ", total=" + total +
                ", card=" + card +
                ", address=" + address +
                '}';
    }
}
