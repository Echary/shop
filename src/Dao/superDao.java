package Dao;

import entity.Commodity;
import entity.User;

import java.util.Map;

public interface superDao extends Dao{

    //登录判断
    boolean check(String user_name, String user_password) throws DaoException;

    //用户信息获取
    entity.Super getSuper() throws DaoException;

    //新建用户
    void newSuper(String superId, String name, String password, String date) throws DaoException;

    //获取当前商品数目
    int getMax() throws DaoException;

    //判断用户是否存在
    boolean exist(String user_name) throws DaoException;

    //修改商品
    void commodityUpdate(String id, String name, double price, int stock, String type) throws DaoException;

    //删除商品
    void delete(String id) throws DaoException;

    //添加商品
    void add(String id, String name, double price, int stock, String type) throws DaoException;

    //加载用户
    Map<String, User> getUser() throws DaoException;

    //修改用户
    void updateUser(String id, String name, String password, int age, int sex, String car) throws DaoException;

    //登出
    void logOut() throws DaoException;
}
