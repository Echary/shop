package Cookie;

import JDBC.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieLogin")
public class CookieLoginServlet extends HttpServlet {

    public static boolean login = false;
    public static String user = null;
    private static boolean check = false;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password =  req.getParameter("password");
        check = User.check(username,password);
        if (check == true){
            Cookie cookie = new Cookie("name",username);
            resp.addCookie(cookie);
            login = true;
            user = username;
            resp.sendRedirect("Login/cookie_welcome.jsp");
        }else {
            resp.sendRedirect("Login/cookie_login.jsp");
        }

    }

}
