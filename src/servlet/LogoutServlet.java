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
    static superDao superDao = new superDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie:cookies){

            if (cookie.getName().equals("name")){
                login = false;

                if (req.getSession().getAttribute("loginUer")!=null){

                    try {
                        userDao.logOut();
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }

                    req.getSession().removeAttribute("loginUser");

            }else if (req.getSession().getAttribute("superUser")!=null) {
                    try {
                        superDao.logOut();
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }

                    req.getSession().removeAttribute("superUser");
            }

                cookie.setMaxAge(0);/*
                req.getSession().invalidate();*/


                resp.addCookie(cookie);
                resp.sendRedirect("/Login/cookie_logout.jsp");
            }
        }
    }
}


