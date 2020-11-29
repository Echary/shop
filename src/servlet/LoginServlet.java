package servlet;

import JDBC.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/cookieLogin")
public class LoginServlet extends HttpServlet {

    public static boolean login = false;
    private static boolean check = false;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String username = req.getParameter("username");
        String password =  req.getParameter("password");
        check = User.check(username,password);
        if (check == true){
            Cookie cookie = new Cookie("name",username);
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", username);
            resp.addCookie(cookie);
            login = true;
            resp.sendRedirect("Login/cookie_welcome.jsp");
        }else {
            resp.sendRedirect("Login/cookie_login.jsp");
        }

    }

}
