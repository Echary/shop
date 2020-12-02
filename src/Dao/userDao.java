package Dao;

import entity.Commodity;
import java.util.Map;


public interface userDao extends Dao{

    public boolean check(String user_name, String user_password) throws DaoException;

    //�û���Ϣ��ȡ
    public entity.User getUser() throws DaoException;

    //�û����ﳵ��ȡ
    public Map<String, Commodity> get_userCar() throws DaoException;

    //�½��û�
    public void newUser(String userId, String name, String password, int age, int sex, String car, String date) throws DaoException;


    //��ȡ��ǰ�û���Ŀ
    public int getMax() throws DaoException;

    //�û�����
    public void newTable(String car) throws DaoException;

    //�ж��û��Ƿ����
    public boolean exist(String user_name) throws DaoException;

    //�û��ǳ�
    public void logOut() throws DaoException;


}
