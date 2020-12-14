package action;

import Dao.*;
import service.userDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import static action.LoginServlet.login;

@WebServlet("/superLogout")
public class SuperLogoutServlet extends HttpServlet {

    static userDao userDao = new userDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("name")){
                login = false;
                cookie.setMaxAge(0);/*
                req.getSession().invalidate();*/
                req.getSession().removeAttribute("superUser");
                resp.addCookie(cookie);
                resp.sendRedirect("/Login/cookie_logout.jsp");
            }
        }
    }
}


