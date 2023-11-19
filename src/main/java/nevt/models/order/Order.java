package nevt.models.order;

import nevt.common.constants.OrderStatus;
import nevt.dto.order.OrderItemDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Collection;

@Document
public class Order {
    private Collection<OrderItem> items;

    @Id
    private String orderId;
    private String email;
    private OrderStatus orderStatus;

    private double total;

    private Card card;
    private Address address;

    public Order(Collection<OrderItem> items, String orderId, String email, OrderStatus orderStatus, double total, Card card, Address address) {
        this.items = items;
        this.orderId = orderId;
        this.email = email;
        this.orderStatus = orderStatus;
        this.total = total;
        this.card = card;
        this.address = address;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Collection<OrderItem> getItems() {
        return items;
    }

    public void setItems(Collection<OrderItem> items) {
        this.items = items;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", orderId='" + orderId + '\'' +
                ", email='" + email + '\'' +
                ", orderStatus=" + orderStatus +
                ", total=" + total +
                ", card=" + card +
                ", address=" + address +
                '}';
    }
}
