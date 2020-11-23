package test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.sql.*;

public class c3p0_test {
    private static DataSource dataSource;

    static{
        dataSource = new ComboPooledDataSource("c3p0");
    }

    public static void main(String[] args){
        try {
            Connection connection = null;
            connection = dataSource.getConnection();
            String sql = "select * from heima";
            Statement statement = connection.createStatement();
            ResultSet  resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int sex = resultSet.getInt("sex");
                System.out.println(id+"-"+name+"-"+age+"-"+sex);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
