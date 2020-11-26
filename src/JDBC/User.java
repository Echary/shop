package JDBC;

import entity.Commodity;
import servlet.ShoppingServlet;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class User {

    static private String name;
    static private Map<String, Commodity> map2 = new HashMap<>();

    public static boolean check(String user_name, String user_password){
        boolean check = false;
        try {
            Connection connection = Pool.create();
            String sql = "select * from consumer where name = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,user_name);
            ResultSet  resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                if (resultSet != null) {
                    String myPassword = resultSet.getString("password");
                    if (myPassword.equals(user_password)) {
                        name = user_name;
                        check = true;
                        get_userCar();
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return check;
    }

    public static entity.User getUser(){

        String userID = null;
        String password = null;
        int age = 0;
        int sex = 0;
        String car = null;
        entity.User user;

        try {
            Connection connection = Pool.create();
            String sql = "select * from consumer where name = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet  resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String myPassword = resultSet.getString("password");
                userID = resultSet.getString("User_id");
                password = resultSet.getString("password");;
                age = resultSet.getInt("age");
                sex = resultSet.getInt("sex");
                car = resultSet.getString("car");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        user = new entity.User(userID,name,password,age,sex,car);

        return user;
    }

    public static Map<String, Commodity> get_userCar(){
        entity.User user = getUser();
        try {
            Connection connection = Pool.create();
            String sql = "select * from " + user.getCar();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet  resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("product_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int amount = resultSet.getInt("amount");
                String type = resultSet.getString("type");
                map2.put(id,new Commodity(id,name,price,type,amount));
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }return map2;
    }

}




