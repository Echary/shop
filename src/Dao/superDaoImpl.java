package Dao;

import entity.Super;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static servlet.ShoppingServlet.consumer_map;
import static servlet.SuperServlet.user_map;

public class superDaoImpl implements superDao{

    static private String superName;
    static private Map<String, User> userMap = new HashMap<>();

    //登陆检测
    public boolean check(String name, String password) throws DaoException {
        boolean check = false;
        try {
            Connection connection = null;
            connection = Dao.create();
            String sql = "select * from super where name = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                if (resultSet != null) {
                    String myPassword = resultSet.getString("password");
                    if (myPassword.equals(password)) {
                        superName = name;
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

    //获取管理员信息
    public Super getSuper() throws DaoException {
        String superID = null;
        String password = null;
        entity.Super superUser;

        try {
            Connection connection = Dao.create();
            String sql = "select * from super where name = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,superName);
            ResultSet  resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String myPassword = resultSet.getString("password");
                superID = resultSet.getString("Super_id");
                password = resultSet.getString("password");;
            }
            connection.close();
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        superUser = new entity.Super(superID,superName,password);

        return superUser;
    }

    //新建管理员
    public void newSuper(String superId, String name, String password, String date) throws DaoException {
        try {
            Connection connection = Dao.create();
            String sql = "INSERT INTO super (Super_id,name,password,date) VALUES(?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, superId);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setString(4, date);
            pstmt.executeUpdate();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取商品数量
    public int getMax() throws DaoException {
        String number = null;
        int max = 0;
        try {
            Connection connection = Dao.create();
            String sql = "select * from commodity where commodity_id=(select max(commodity_id) from commodity)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet  resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                number = resultSet.getString("commodity_id");
            }
            connection.close();
            pstmt.close();
            max = Integer.parseInt(number);
        } catch (SQLException e){
            e.printStackTrace();
        }return max;
    }

    //判断管理员是否存在
    public boolean exist(String user_name) throws DaoException {
        boolean check = false;
        try {
            Connection connection = Dao.create();
            String sql = "select * from super where name = ?";
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

    //修改商品
    @Override
    public void commodityUpdate(String id, String name, double price, int stock, String type) throws DaoException {
        try {
            Connection connection = Dao.create();
            String sql = "UPDATE commodity SET name = ? ,price = ? ,stock = ? ,type = ? WHERE commodity_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3,stock);
            pstmt.setString(4,type);
            pstmt.setString(5,id);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删除商品
    @Override
    public void delete(String id) throws DaoException {
        try {
            Connection connection = Dao.create();
            String sql = "DELETE FROM commodity WHERE commodity_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //添加商品
    @Override
    public void add(String id, String name, double price, int stock, String type) throws DaoException {
        try {
            Connection connection = Dao.create();
            String sql = "INSERT INTO commodity (commodity_id,name,price,stock,type) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, stock);
            pstmt.setString(5, type);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //加载用户
    @Override
    public Map<String, User> getUser() throws DaoException {
        try {
            Connection connection = null;
            connection = Dao.create();
            String sql = "select * from consumer";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet  resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("User_id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                int sex = resultSet.getInt("sex");
                String car = resultSet.getString("car");
                String date = resultSet.getString("date");
                userMap.put(id,new User(id,name,password,age,sex,car,date));
            }

            pstmt.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userMap;
    }

    //修改用户
    @Override
    public void updateUser(String id, String name, String password, int age, int sex, String car) throws DaoException {
        try {
            Connection connection = Dao.create();
            String sql = "UPDATE consumer SET User_id = ? ,name = ? ,password = ? ,age = ? ,sex = ? ,car = ? WHERE User_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setInt(4, age);
            pstmt.setInt(5, sex);
            pstmt.setString(6, car);
            pstmt.setString(7, id);
            pstmt.executeUpdate();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logOut() throws DaoException {
        user_map.clear();
    }


}
