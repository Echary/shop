package JDBC;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.sql.*;


public class check {

    private static DataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource("c3p0");
    }

    public static Connection getConnection() throws SQLException {
        return check.dataSource.getConnection();
    }

    public static void release(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        connection = null;
    }

    public static void release(Statement statement) {
        try {
            if(statement != null) {
                statement.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        statement = null;
    }

    public static void release(ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        resultSet = null;
    }
}
