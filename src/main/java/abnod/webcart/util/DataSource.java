package abnod.webcart.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {
    private static HikariConfig config = new HikariConfig("/DataSource.properties");
    ;
    private static HikariDataSource ds = new HikariDataSource(config);
    ;

    static {
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

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
