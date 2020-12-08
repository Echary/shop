package ajax;

import Dao.DaoException;
import Dao.userDao;
import Dao.userDaoImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断用户名是否重复
 */
@WebServlet("/Check_username")
public class userCheck extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static userDao userDao = new userDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //获取用户输入的数据
        String name = request.getParameter("username");
        try {
            if(!name.equals("")){
                if(userDao.exist(name)) {
                    out.print(1);
                }else {
                    out.print(2);
                }
            }else out.print(0);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

}