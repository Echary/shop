package JDBC;

import java.sql.*;

public class User {
    public static boolean check(String user_name, String user_password){
        boolean check = false;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
            String user = "root";
            String password = "Lcw206";
            Connection connection = DriverManager.getConnection(url,user,password);
            String sql = "select * from consumer where name = '" + user_name+"'";
            Statement statement = connection.createStatement();
            ResultSet  resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (resultSet != null) {
                    String myPassword = resultSet.getString("password");
                    if (myPassword.equals(user_password)) {
                        check = true;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return check;
    }
}




