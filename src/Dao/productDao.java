package Dao;

import entity.Commodity;
import java.util.Map;

public interface productDao extends Dao{

    //获得所有产品
    Map<String, Commodity> get_commodity() throws DaoException;

    //获得用户信息
    void getUser() throws DaoException;

    //添加产品
    void addProduct(Commodity commodity) throws DaoException;
    void addProduct(String id,int amount) throws DaoException;

    //修改产品
    void updateProduct(String id, int amount) throws DaoException;

    //删除产品
    void deleteProduct(String id, int amount) throws DaoException;

    //清空购物车
    void clean() throws DaoException;

}
