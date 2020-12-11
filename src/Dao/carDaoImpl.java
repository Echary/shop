package Dao;

import entity.Commodity;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class carDaoImpl implements carDao {

    static private Map<String, Commodity> map = new HashMap<>();
    static carDao carDao = new carDaoImpl();
    static userDao userDao = new userDaoImpl();
    static private entity.User user;

    //获得所有产品
    public Map<String, Commodity> get_commodity() {
        try {
            Connection connection = null;
            connection = Dao.create();
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

            pstmt.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return map;
    }

    //获得用户信息
    public void getUser() throws DaoException {
        user = userDao.getUser();
    }

    //添加产品
    public void addProduct(Commodity commodity) throws DaoException {
        try {
            Connection connection = Dao.create();
            String sql = "INSERT INTO " + user.getCar() + "(product_id,name,price,type,amount) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, commodity.getId());
            pstmt.setString(2, commodity.getName());
            pstmt.setDouble(3, commodity.getPrice());
            pstmt.setString(4, commodity.getType());
            pstmt.setInt(5, commodity.getAmount());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addProduct(String id, int amount) throws DaoException {
        updateProduct(id,amount+1);
    }

    //修改产品
    public void updateProduct(String id, int amount) throws DaoException {
        try {
            Connection connection = Dao.create();
            String sql = "UPDATE " + user.getCar() + " SET amount = ? WHERE product_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, amount);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删除产品
    public void deleteProduct(String id, int amount) throws DaoException {
        amount--;
        if (amount > 0) {
            updateProduct(id,amount);
        }else {
            try {
                Connection connection = Dao.create();
                String sql = "DELETE FROM " + user.getCar() + " WHERE product_id = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, id);
                pstmt.executeUpdate();
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //清空购物车
    public void clean() throws DaoException {
        try {
            Connection connection = Dao.create();
            String sql = "TRUNCATE TABLE " + user.getCar();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
