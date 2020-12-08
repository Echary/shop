package servlet;

import Dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@WebServlet("/cookieLogin")
public class LoginServlet extends HttpServlet {

    public static boolean login = false;
    private static boolean check = false;
    static userDao userDao = new userDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password =  req.getParameter("password");
        try {
            check = userDao.check(username,password);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        if (check == true){
            Cookie cookie = new Cookie("name",username);
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", username);
            resp.addCookie(cookie);
            login = true;
            resp.sendRedirect("Login/cookie_welcome.jsp");
        }else {
            resp.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = resp.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('用户名或密码错误!');");
            out.println("history.back();");
            out.println("</script>");
            /*resp.sendRedirect("Login/cookie_login.jsp");*/
        }
    }
}
