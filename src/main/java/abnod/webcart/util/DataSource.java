package abnod.webcart.util;

import abnod.webcart.dao.MySqlDAO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static HikariConfig config = new HikariConfig("/DataSource.properties");
    private static HikariDataSource ds = new HikariDataSource(config);

    static {
        init();
    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * override this for change database creation dao
     */
    public static void init(){
        MySqlDAO.initDB();
    }
}
