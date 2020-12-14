package action;

import Dao.*;
import service.superDaoImpl;
import service.userDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import static action.LoginServlet.login;
import static action.SuperServlet.user_map;

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

                if (req.getSession().getAttribute("loginUser")!=null){

                    try {
                        userDao.logOut();
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }

                    req.getSession().removeAttribute("loginUser");

            }else if (req.getSession().getAttribute("superUser")!=null) {
                    try {
                        if (user_map != null) superDao.logOut();
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


