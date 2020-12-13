package Dao;

import entity.Commodity;
import entity.User;

import java.util.Map;

public interface superDao extends Dao{

    //��¼�ж�
    boolean check(String user_name, String user_password) throws DaoException;

    //�û���Ϣ��ȡ
    entity.Super getSuper() throws DaoException;

    //�½��û�
    void newSuper(String superId, String name, String password, String date) throws DaoException;

    //��ȡ��ǰ��Ʒ��Ŀ
    int getMax() throws DaoException;

    //�ж��û��Ƿ����
    boolean exist(String user_name) throws DaoException;

    //�޸���Ʒ
    void commodityUpdate(String id, String name, double price, int stock, String type) throws DaoException;

    //ɾ����Ʒ
    void delete(String id) throws DaoException;

    //�����Ʒ
    void add(String id, String name, double price, int stock, String type) throws DaoException;

    //�����û�
    Map<String, User> getUser() throws DaoException;

    //�޸��û�
    void updateUser(String id, String name, String password, int age, int sex, String car) throws DaoException;

    //�ǳ�
    void logOut() throws DaoException;
}
