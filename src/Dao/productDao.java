package Dao;

import entity.Commodity;
import java.util.Map;

public interface productDao extends Dao{

    //������в�Ʒ
    Map<String, Commodity> get_commodity() throws DaoException;

    //����û���Ϣ
    void getUser() throws DaoException;

    //��Ӳ�Ʒ
    void addProduct(Commodity commodity) throws DaoException;
    void addProduct(String id,int amount) throws DaoException;

    //�޸Ĳ�Ʒ
    void updateProduct(String id, int amount) throws DaoException;

    //ɾ����Ʒ
    void deleteProduct(String id, int amount) throws DaoException;

    //��չ��ﳵ
    void clean() throws DaoException;

}
