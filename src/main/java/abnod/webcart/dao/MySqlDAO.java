package abnod.webcart.dao;

import abnod.webcart.model.Product;
import abnod.webcart.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDAO implements DBInterface {

    static{

    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try(Connection connection = DataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM webcart.products;");
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("productId"));
                product.setTitle(resultSet.getString("title"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getFloat("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProduct(int id) {
            Product product = null;
            try (Connection connection = DataSource.getConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM webcart.products WHERE productId = ?;");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    product = new Product();
                    product.setId(resultSet.getInt("userId"));
                    product.setTitle(resultSet.getString("title"));
                    product.setDescription(resultSet.getString("description"));
                    product.setPrice(resultSet.getFloat("price"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return product;
    }

    public boolean deleteProduct(int id) {
        boolean success = false;
        try (Connection connection = DataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM webcart.products WHERE productId = ?;");
            preparedStatement.setInt(1, id);
            success = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean createProduct(Product product) {
        boolean success = false;
        try (Connection connection = DataSource.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO webcart.products (title, description, price) values (?,?,?);");
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setFloat(3, product.getPrice());
            success = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean updateProduct(Product product) {
        boolean success = false;
        try (Connection connection = DataSource.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE webcart.products SET title=?, description=?, price=? WHERE productId=?;");
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setInt(4, product.getId());
            success = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;

    }

    public void fillTestTable() {
        if (getProducts().isEmpty()){
            for (int i = 1; i < 6; i++) {
                Product product = new Product();
                product.setTitle("product" + i);
                product.setDescription("test description");
                product.setPrice(i*17);
                createProduct(product);
            }
        }
    }
}
