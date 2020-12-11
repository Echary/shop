package Dao;

import entity.Commodity;
import java.util.Map;


public interface userDao extends Dao{

    //登录判断
    boolean check(String user_name, String user_password) throws DaoException;

    //用户信息获取
    entity.User getUser() throws DaoException;

    //用户购物车获取
    Map<String, Commodity> get_userCar() throws DaoException;

    //新建用户
    void newUser(String userId, String name, String password, int age, int sex, String car, String date) throws DaoException;

    //获取当前用户数目
    int getMax() throws DaoException;

    //用户建表
    void newTable(String car) throws DaoException;

    //判断用户是否存在
    boolean exist(String user_name) throws DaoException;

    //用户登出
    void logOut() throws DaoException;

}
