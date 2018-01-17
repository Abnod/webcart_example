package abnod.webcart.dao;

import abnod.webcart.model.Cart;
import abnod.webcart.model.Product;
import abnod.webcart.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySqlDAO implements DBInterface {

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM webcart.products;");
            while (resultSet.next()) {
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
        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM webcart.products WHERE productId = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("productId"));
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
        try (Connection connection = DataSource.getConnection()) {
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
        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO webcart.products (title, description, price) VALUES (?,?,?);");
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
        try (Connection connection = DataSource.getConnection()) {
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
        if (getProducts().isEmpty()) {
            for (int i = 1; i < 6; i++) {
                Product product = new Product();
                product.setTitle("product" + i);
                product.setDescription("test description");
                product.setPrice(i * 17);
                createProduct(product);
            }
        }
    }

    public int createOrder(Cart cart, String name, String surname, String phone) {
        int orderId = 0;
        try (Connection connection = DataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO webcart.orders (customerName, customerSurname, customerPhoneNumber) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, phone);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                orderId = resultSet.getInt(1);
            }

            preparedStatement =
                    connection.prepareStatement("INSERT INTO webcart.ordercontent (orderId, productId, quantity) VALUES (?,?,?);");
            for (Map.Entry product : cart.getCartItems().entrySet()) {
                Product temp = (Product) product.getKey();
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, temp.getId());
                preparedStatement.setInt(3, (Integer) product.getValue());
                preparedStatement.executeUpdate();
            }

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    public static void initDB(){
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE SCHEMA IF NOT EXISTS `webcart` ;");
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS `webcart`.`products` (`productId` INT NOT NULL AUTO_INCREMENT, `title` VARCHAR(45) NOT NULL, `description` VARCHAR(200) NULL," +
                            " `price` FLOAT NOT NULL, PRIMARY KEY (`productId`), UNIQUE INDEX `id_UNIQUE` (`productId` ASC), UNIQUE INDEX `title_UNIQUE` (`title` ASC));"
            );
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS `webcart`.`orders` (`orderId` INT NOT NULL AUTO_INCREMENT, `customerName` VARCHAR(45) NOT NULL, `customerSurname` VARCHAR(45) NOT NULL," +
                            " `customerPhoneNumber` VARCHAR(45) NOT NULL, PRIMARY KEY (`orderId`), UNIQUE INDEX `id_UNIQUE` (`orderId` ASC));"
            );
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS `webcart`.`ordercontent` (`orderId` INT NOT NULL, `productId` INT NOT NULL, `quantity` INT NOT NULL," +
                            " INDEX `productId_idx` (`productId` ASC), INDEX `orderId_idx` (`orderId` ASC), PRIMARY KEY (`orderId`, `productId`), CONSTRAINT `productId`" +
                            " FOREIGN KEY (`productId`) REFERENCES `webcart`.`products` (`productId`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                            " CONSTRAINT `orderId` FOREIGN KEY (`orderId`) REFERENCES `webcart`.`orders` (`orderId`) ON DELETE NO ACTION" +
                            " ON UPDATE NO ACTION);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
