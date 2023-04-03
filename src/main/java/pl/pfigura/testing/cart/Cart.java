package pl.pfigura.testing.cart;

import lombok.Getter;
import pl.pfigura.testing.Meal;
import pl.pfigura.testing.order.Order;

import java.util.ArrayList;
import java.util.List;

@Getter
public
class Cart {
    List<Order> orders = new ArrayList<>();

    void addOrderToCart(Order order){
        this.orders.add(order);
    }

    void clearCart(){
        this.orders.clear();
    }


    void simulateLargeOrer(){
        for(int i=0;i<1000;i++){
            Meal meal = new Meal(i%10, "hamburger "+i);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCart(order);
        }
        System.out.println("cart size "+ orders.size());
        clearCart();
    }
}
