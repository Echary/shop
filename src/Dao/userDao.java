package Dao;

import entity.Commodity;
import java.util.Map;


public interface userDao extends Dao{

    //��¼�ж�
    boolean check(String user_name, String user_password) throws DaoException;

    //�û���Ϣ��ȡ
    entity.User getUser() throws DaoException;

    //�û����ﳵ��ȡ
    Map<String, Commodity> get_userCar() throws DaoException;

    //�½��û�
    void newUser(String userId, String name, String password, int age, int sex, String car, String date) throws DaoException;

    //��ȡ��ǰ�û���Ŀ
    int getMax() throws DaoException;

    //�û�����
    void newTable(String car) throws DaoException;

    //�ж��û��Ƿ����
    boolean exist(String user_name) throws DaoException;

    //�û��ǳ�
    void logOut() throws DaoException;

}
