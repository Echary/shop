package Dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface Dao {

    DataSource dataSource = new ComboPooledDataSource("c3p0");
    Connection connection = null;

    static Connection create() throws SQLException {
        return Dao.dataSource.getConnection();
    }

}
