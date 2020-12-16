package action;

import Dao.DaoException;
import Dao.userDao;
import service.userDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/userCheck")
public class checkServlet extends HttpServlet {

    static userDao userDao = new userDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            HttpSession session = req.getSession();

            String cookie_username = null;
            String cookie_password = null;

            Cookie[] cookies = req.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("username".equals(cookie.getName())) {
                        cookie_username = cookie.getValue();
                        cookie_username = URLDecoder.decode(cookie_username, "UTF-8");
                    }
                    if ("userPassword".equals(cookie.getName())) {
                        cookie_password = cookie.getValue();
                    }
                }

                try {
                    if (cookie_username != null && cookie_password != null){
                        if (userDao.check(cookie_username,cookie_password)) {
                            session.setAttribute("loginUser", cookie_username);
                            session.setAttribute("userPassword",cookie_password);
                            resp.sendRedirect("/Login/cookie_login.jsp");
                        }
                    }else {

                    }
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }
        }
    }
