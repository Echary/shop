package test;

import java.sql.*;

public class driverTest {
    public static void main(String[] args){
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/heima?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
            String user = "root";
            String password = "Lcw206";
            Connection connection = DriverManager.getConnection(url,user,password);
           /* String sql = "insert into heima(id,name,age,sex) values(5,'爸爸',233,1)";*/
            String sql = "select * from heima";
            Statement statement = connection.createStatement();
            /*int result = statement.executeUpdate(sql);*/
            ResultSet  resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int sex = resultSet.getInt("sex");
                System.out.println(id+"-"+name+"-"+age+"-"+sex);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
