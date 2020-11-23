package JDBC;

import entity.Commodity;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Product {

    static private Map<Integer, Commodity> map = new HashMap<>();




    public static Map<Integer, Commodity> get_commodity(){
        try {
            /*Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
            String user = "root";
            String password = "Lcw206";
            Connection connection = DriverManager.getConnection(url,user,password);*/


            Connection connection = create_c3p0.create();

            String sql = "select * from commodity";
            Statement statement = connection.createStatement();
            ResultSet  resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = Integer.valueOf(resultSet.getString("commodity_id"));
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                String type = resultSet.getString("type");
                map.put(id,new Commodity(id,name,price,stock,type));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return map;
    }


}
