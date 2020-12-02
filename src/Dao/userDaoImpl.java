package Dao;

import entity.Commodity;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import static servlet.ShoppingServlet.consumer_map;

public class userDaoImpl implements userDao {

    static private String name;
    static private Map<String, Commodity> map2 = new HashMap<>();

    //登录检测
    public boolean check(String user_name, String user_password) throws DaoException{
        boolean check = false;
        try {
            Connection connection = null;
                connection = Dao.create();
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
            connection.close();
            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return check;
    }

    //用户信息获取
    public entity.User getUser() throws DaoException{

        String userID = null;
        String password = null;
        int age = 0;
        int sex = 0;
        String car = null;
        entity.User user;

        try {
            Connection connection = Dao.create();
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
            connection.close();
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        user = new entity.User(userID,name,password,age,sex,car);

        return user;
    }

    //用户购物车获取
    public Map<String, Commodity> get_userCar() throws DaoException{
        entity.User user = getUser();
        try {
            Connection connection = Dao.create();
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
            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }return map2;
    }

    //新建用户
    public void newUser(String userId, String name, String password, int age, int sex, String car, String date) throws DaoException{
        try {
            Connection connection = Dao.create();
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
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取当前用户数目
    public int getMax() throws DaoException{
        int max = 0;
        try {
            Connection connection = Dao.create();
            String sql = "SELECT COUNT(*) FROM consumer";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet  resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                max = resultSet.getInt(1);
            }
            connection.close();
            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }return max;
    }

    //用户建表
    public void newTable(String car) throws DaoException{
        try {
            Connection connection = Dao.create();
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
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //判断用户是否存在
    public boolean exist(String user_name) throws DaoException{
        boolean check = false;
        try {
            Connection connection = Dao.create();
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
            connection.close();
            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return check;
    }

    //用户登出
    public void logOut() throws DaoException{
        consumer_map.clear();
    }

}





