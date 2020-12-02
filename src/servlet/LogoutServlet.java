package servlet;

import Dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import static servlet.LoginServlet.login;

@WebServlet("/cookieLogout")
public class LogoutServlet extends HttpServlet {

    static userDao userDao = new userDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("name")){
                login = false;
                try {
                    userDao.logOut();
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                cookie.setMaxAge(0);
                req.getSession().removeAttribute("loginUser");
                resp.addCookie(cookie);
                resp.sendRedirect("/Login/cookie_logout.jsp");
            }
        }
    }
}


