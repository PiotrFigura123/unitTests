package pl.pfigura.testing.order;

import lombok.Getter;
import pl.pfigura.testing.Meal;

import java.util.ArrayList;
import java.util.List;

@Getter
public
class Order {


    private OrderStatus orderStatus;
    private List<Meal> meals = new ArrayList<>();

    public void addMealToOrder(Meal meal) {
        this.meals.add(meal);
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void removeMealFromOrder(Meal meal) {
        this.meals.remove(meal);
    }

    void cancel() {
        this.meals.clear();
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
