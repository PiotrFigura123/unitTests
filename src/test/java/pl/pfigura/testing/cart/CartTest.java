package pl.pfigura.testing.cart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.pfigura.testing.order.Order;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("Test cases for cart")
class CartTest {

    // @Disabled //-> umożliwia ignorowanie testu
    @Test
    void simulateLargeOrder() {
        //given
        Cart cart = new Cart();

        //when + then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrer);
    }

    @Test
    void cartChouldNotBeEmptyAfterAdingOrderToCart() {
        //given
        Order order = new Order();
        Cart cart = new Cart();

        //when
        cart.addOrderToCart(order);

        //than
        assertThat(cart.getOrders(), anyOf(
                notNullValue(),
                hasSize(2),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));
        assertThat(cart.getOrders(), allOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));


    }
}