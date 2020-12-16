package action;

import Dao.*;
import service.userDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cookieLogin")
public class LoginServlet extends HttpServlet {

    private static boolean check = false;
    static userDao userDao = new userDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password =  req.getParameter("password");
        try {
            check = userDao.check(username,password);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        if (check == true){

            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("check",check);
            req.getRequestDispatcher("Login/cookie_welcome.jsp").forward(req, resp);

        }else {
            resp.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = resp.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('用户名或密码错误!');");
            out.println("history.back();");
            out.println("</script>");
        }
    }
}
