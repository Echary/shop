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

   /* private String myUsername = "admin";
    private String myPassword = "123123";*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* String username = req.getParameter("username");
        String password =  req.getParameter("password");
        if (username.equals(myUsername) && password.equals(myPassword)){
            Cookie cookie = new Cookie("name",username);
            resp.addCookie(cookie);
            resp.sendRedirect("Login/cookie_welcome.jsp");
        }else {
            resp.sendRedirect("Login/cookie_login.jsp");
        }*/

        String username = req.getParameter("username");
        String password =  req.getParameter("password");
        boolean check = User.check(username,password);
        System.out.println(check);
        if (check == true){
            Cookie cookie = new Cookie("name",username);
            resp.addCookie(cookie);
            resp.sendRedirect("Login/cookie_welcome.jsp");
        }else {
            resp.sendRedirect("Login/cookie_login.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
