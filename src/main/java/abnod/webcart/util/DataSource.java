package abnod.webcart.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {
    private static HikariConfig config = new HikariConfig("/DataSource.properties");;
    private static HikariDataSource ds = new HikariDataSource( config );;

    static{
        try(Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE SCHEMA IF NOT EXISTS `webcart` ;");
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS `webcart`.`products` (`productId` INT NOT NULL AUTO_INCREMENT, `title` VARCHAR(45) NOT NULL, `description` VARCHAR(200) NULL," +
                            " `price` FLOAT NOT NULL, PRIMARY KEY (`productId`), UNIQUE INDEX `id_UNIQUE` (`productId` ASC), UNIQUE INDEX `title_UNIQUE` (`title` ASC));"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
