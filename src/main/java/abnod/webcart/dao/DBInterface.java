package abnod.webcart.dao;

import abnod.webcart.model.Cart;
import abnod.webcart.model.Product;

import java.util.List;

public interface DBInterface {

    List<Product> getProducts();

    Product getProduct(int id);

    boolean deleteProduct(int id);

    boolean createProduct(Product product);

    boolean updateProduct(Product product);

    void fillTestTable();

    int createOrder(Cart cart, String name, String surname, String phone);
}
