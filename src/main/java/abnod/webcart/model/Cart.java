package abnod.webcart.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> productCart = new HashMap<>();

    public void removeFromCart(Product product) {
        productCart.remove(product);
    }

    public void addToCart(Product product) {
        productCart.put(product, productCart.getOrDefault(product, 0) + 1);
    }

    public Map<Product, Integer> getCartItems() {
        return productCart;
    }

    public boolean isHasProducts() {
        return productCart.isEmpty();
    }
}
