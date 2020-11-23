package test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class c3p0_set {
    public static void main(String[]agrs){
        try {
            //创建C3P0
            ComboPooledDataSource dataSource = new ComboPooledDataSource("c3p0");
           /* dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/heima?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
            dataSource.setUser("root");
            dataSource.setPassword("Lcw206");
            //设置初始化连接个数
            dataSource.setInitialPoolSize(10);
            //设置最大连接数
            dataSource.setMaxPoolSize(20);
            //设置最小连接数
            dataSource.setMinPoolSize(3);
            //再次申请个数
            dataSource.setAcquireIncrement(5);*/

            Connection connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
