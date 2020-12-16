package action;

import Dao.*;
import service.userDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/superLogout")
public class SuperLogoutServlet extends HttpServlet {

    static userDao userDao = new userDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie:cookies) {

            if (cookie.getName().equals("superName")) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }

            if (cookie.getName().equals("superPassword")) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }

        req.getSession().removeAttribute("superUser");
        req.getSession().removeAttribute("superPassword");

        resp.sendRedirect("/Login/cookie_logout.jsp");
    }
}


