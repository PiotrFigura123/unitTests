package pl.pfigura.testing.cart;

import pl.pfigura.testing.order.OrderStatus;

class CartService {

    private CartHandler cartHandler;

    CartService(final CartHandler cartHandler) {
        this.cartHandler = cartHandler;
    }

    Cart processCart(Cart cart) {
        if (cartHandler.canHandleCart(cart)) {
            cartHandler.sendToPrepare(cart);
            cart.getOrders().forEach(order -> {
                order.changeOrderStatus(OrderStatus.PREPARING);
            });
            return cart;

        } else {
            cart.getOrders().forEach(order -> {
                order.changeOrderStatus(OrderStatus.REJECTED);
            });
            return cart;
        }
    }
}
