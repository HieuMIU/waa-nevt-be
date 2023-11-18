package nevt.models.order;

import nevt.models.car.Car;

public class OrderItem {
    private Car car;
    private int number;
    private double totalPrice;

    public OrderItem(Car car, int number, double totalPrice) {
        this.car = car;
        this.number = number;
        this.totalPrice = totalPrice;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
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
