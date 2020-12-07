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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

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
            session.setMaxInactiveInterval(60*60*24*7);
            resp.addCookie(cookie);
            login = true;
            resp.sendRedirect("Login/cookie_welcome.jsp");
        }else {
            resp.sendRedirect("Login/cookie_login.jsp");
        }
    }
}
