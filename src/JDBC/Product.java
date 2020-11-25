package JDBC;

import entity.Commodity;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Product {

    static private Map<Integer, Commodity> map = new HashMap<>();

    public static Map<Integer, Commodity> get_commodity(){
        try {
            Connection connection = Pool.create();

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
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return map;
    }

    public static void getUser(){
        try {
            Connection connection = Pool.create();

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
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void addProduct(String username, String commodity_id){

    }

    public static void getProduct(){

    }

    public static void updateProduct(){

    }

    public static void deleteProduct(){

    }

}
