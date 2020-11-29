package JDBC;

import entity.Commodity;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class Product {

    static private Map<String, Commodity> map = new HashMap<>();
    static private entity.User user;

    //������в�Ʒ
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

    //����û���Ϣ
    public static void getUser(){
        user = User.getUser();
    }

    //��Ӳ�Ʒ
    public static void addProduct(Commodity commodity) {
        if (user == null) getUser();
        try {
            Connection connection = Pool.create();
            String sql = "INSERT INTO " + user.getCar() + "(product_id,name,price,type,amount) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, commodity.getId());
            pstmt.setString(2, commodity.getName());
            pstmt.setDouble(3, commodity.getPrice());
            pstmt.setString(4, commodity.getType());
            pstmt.setInt(5, commodity.getAmount());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addProduct(String id,int amount){
        if (user == null) getUser();
        updateProduct(id,amount+1);
    }


    //�޸Ĳ�Ʒ
    public static void updateProduct(String id, int amount){
        if (user == null) getUser();
        try {
            Connection connection = Pool.create();
            String sql = "UPDATE " + user.getCar() + " SET amount = ? WHERE product_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, amount);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ɾ����Ʒ
    public static void deleteProduct(String id, int amount){
        amount--;
        if (amount > 0) {
            updateProduct(id,amount);
        }else {
            try {
                Connection connection = Pool.create();
                String sql = "DELETE FROM " + user.getCar() + " WHERE product_id = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, id);
                pstmt.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*public static Map<String, Commodity> searchProduct(String ID){
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
    }*/

    //��չ��ﳵ
    public static void clean(){
        if (user == null) getUser();
        try {
            Connection connection = Pool.create();
            String sql = "TRUNCATE TABLE " + user.getCar();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
