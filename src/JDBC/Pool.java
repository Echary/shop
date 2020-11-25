package JDBC;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.sql.*;

public class Pool {
    private static DataSource dataSource;
    static Connection connection;

    static{
        dataSource = new ComboPooledDataSource("c3p0");
        connection = null;
    }

    public static Connection create() throws SQLException {
        return Pool.dataSource.getConnection();
    }

}
