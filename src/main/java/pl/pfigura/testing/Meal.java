package pl.pfigura.testing;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public
class Meal {
    private int price;
    private String name;
    private int quantity;

    Meal() {
    }

    Meal(final int price) {
        this.price = price;
    }

    Meal(final int price, final String name, final int quantity) {
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public Meal(final int price, final String name) {
        this.price = price;
        this.name = name;
    }

    public int getDiscountPrice(int discount) {
        if (discount > this.price) {
            throw new IllegalArgumentException("discount can't be higher than price");
        }
        return this.price - discount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Meal meal = (Meal) o;
        return price == meal.price &&
                Objects.equals(name, meal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    int sumPrice() {
        return getPrice() * getQuantity();
    }
}
