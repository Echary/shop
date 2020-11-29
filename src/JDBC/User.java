package JDBC;

import entity.Commodity;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import static servlet.ShoppingServlet.consumer_map;

public class User {

    static private String name;
    static private Map<String, Commodity> map2 = new HashMap<>();

    //登录检测
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

    //用户信息获取
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

    //用户购物车获取
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

    //新建用户
    public static void newUser(String userId, String name, String password, int age, int sex, String car, String date){
        try {
            Connection connection = Pool.create();
            String sql = "INSERT INTO consumer (User_id,name,password,age,sex,car,date) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setInt(4, age);
            pstmt.setInt(5, sex);
            pstmt.setString(6,car);
            pstmt.setString(7,date);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取当前用户数目
    public static int getMax(){
        int max = 0;
        try {
            Connection connection = Pool.create();
            String sql = "SELECT COUNT(*) FROM consumer";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet  resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                max = resultSet.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }return max;
    }

    //用户建表
    public static void newTable(String car){
        try {
            Connection connection = Pool.create();
            String sql = "CREATE TABLE `shop`.`" + car + "`  (\n" +
                    "  `product_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
                    "  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,\n" +
                    "  `price` double(255, 2) NULL DEFAULT NULL,\n" +
                    "  `amount` int(0) NULL DEFAULT NULL,\n" +
                    "  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`product_id`)\n" +
                    ");";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //判断用户是否存在
    public static boolean exist(String user_name){
        boolean check = false;
        try {
            Connection connection = Pool.create();
            String sql = "select * from consumer where name = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,user_name);
            ResultSet  resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                if (resultSet != null) {
                    {
                        check = true;
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return check;
    }

    //用户登出
    public static void logOut(){
        consumer_map.clear();
    }

}




