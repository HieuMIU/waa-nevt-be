package nevt.dto.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import nevt.common.constants.OrderStatus;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class OrderDTO {

    private String orderId;

    @NotEmpty
    private Collection<OrderItemDTO> items;

    @NotEmpty
    private String email;

    @NotEmpty
    private OrderStatus orderStatus;

    @NotNull

    private double total;

    private LocalDate date;

    @NotNull

    private CardDTO card;

    @NotNull
    private AddressDTO address;

    public OrderDTO(String orderId, Collection<OrderItemDTO> items, String email, OrderStatus orderStatus, LocalDate date, double total, CardDTO card, AddressDTO address) {
            this.orderId = orderId;
        this.items = items;
        this.email = email;
        this.orderStatus = orderStatus;
        this.total = total;
        this.card = card;
        this.address = address;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
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

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
