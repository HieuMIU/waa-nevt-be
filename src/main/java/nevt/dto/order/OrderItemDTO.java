package nevt.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import nevt.dto.car.CarDTO;

public class OrderItemDTO {

    @NotNull
    private CarDTO car;

    @NotNull
    @Min(value = 1)
    private int number;
    private double totalPrice;

    public OrderItemDTO(CarDTO car, int number, double totalPrice) {
        this.car = car;
        this.number = number;
        this.totalPrice = totalPrice;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "car=" + car +
                ", number=" + number +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
