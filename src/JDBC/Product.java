package JDBC;

import entity.Commodity;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static JDBC.User.getUser;


public class Product {

    static private Map<String, Commodity> map = new HashMap<>();
    static private Map<String, Commodity> temp = new HashMap<>();
    static private entity.User user;

    public static Map<String, Commodity> get_commodity(){
        try {
            Connection connection = Pool.create();
            String sql = "select * from commodity";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet  resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("commodity_id");
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

    public static void User(){
        user = getUser();
    }

    public static void addProduct(Commodity commodity){
        if(user == null) User();
        try {
            Connection connection = Pool.create();
            String sql = "INSERT INTO " + user.getCar() + "(product_id,name,price,type,amount) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,commodity.getId());
            pstmt.setString(2,commodity.getName());
            pstmt.setDouble(3,commodity.getPrice());
            pstmt.setString(4,commodity.getType());
            pstmt.setInt(5,commodity.getAmount());
            int resultSet = pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void getProduct(){

    }

    public static void updateProduct(){

    }

    public static void deleteProduct(){

    }

    public static Map<String, Commodity> searchProduct(String ID){
        try {
            Connection connection = Pool.create();

            String sql = "select * from commodity where commodity_id =?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,ID);
            ResultSet  resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("commodity_id");
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

}
