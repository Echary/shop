package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String userName = req.getParameter("userName");

        if (JDBC.User.exist(userName) == false) {
            String password = req.getParameter("password");
            int age = Integer.parseInt(req.getParameter("age"));
            String sexStr = req.getParameter("sex");
            int sex;
            if (sexStr.equals("ÄÐ")) {
                sex = 0;
            } else {
                sex = 1;
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = df.format(new Date());
            int max = JDBC.User.getMax();
            Format number = new DecimalFormat("0000");
            String id = number.format(max + 1);
            String car = "car" + id + userName;
            JDBC.User.newUser(id, userName, password, age, sex, car, date);
            JDBC.User.newTable(car);
            resp.sendRedirect("Login/regDo.jsp");
        }else {
            req.setAttribute("exist", 1);
            req.getRequestDispatcher("Login/register.jsp").forward(req, resp);
        }
    }
}
