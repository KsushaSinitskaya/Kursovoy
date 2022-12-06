package client;

import java.util.Objects;

public class Order {

    private int orderNumber;
    private int price;
    private int quantity;
    private String model;
    private String make;
    private String year;
    private String name;
    private String surname;
    private String color;
    private String insurance;
    private String country;
    private String supplier;
    private boolean orderAccepted;
    private boolean orderCompleted;

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isOrderAccepted() {
        return orderAccepted;
    }

    public void setOrderAccepted(boolean orderAccepted) {
        this.orderAccepted = orderAccepted;
    }

    public boolean isOrderCompleted() {
        return orderCompleted;
    }

    public void setOrderCompleted(boolean orderCompleted) {
        this.orderCompleted = orderCompleted;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                ", insurance='" + insurance + '\'' +
                ", country='" + country + '\'' +
                ", orderAccepted=" + orderAccepted +
                ", orderCompleted=" + orderCompleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderNumber == order.orderNumber &&
                price == order.price &&
                quantity == order.quantity &&
                orderAccepted == order.orderAccepted &&
                orderCompleted == order.orderCompleted &&
                Objects.equals(model, order.model) &&
                Objects.equals(make, order.make) &&
                Objects.equals(year, order.year) &&
                Objects.equals(name, order.name) &&
                Objects.equals(surname, order.surname) &&
                Objects.equals(color, order.color) &&
                Objects.equals(insurance, order.insurance) &&
                Objects.equals(country, order.country) &&
                Objects.equals(supplier, order.supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, price, quantity, model, make, year, name, surname, color, insurance, country, supplier, orderAccepted, orderCompleted);
    }
}