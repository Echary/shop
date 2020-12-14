package action;

import Dao.*;
import service.carDaoImpl;
import service.userDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    static carDao carDao = new carDaoImpl();
    static userDao userDao = new userDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String userName = req.getParameter("userName");

            if (userDao.exist(userName) == false) {
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
                int max = userDao.getMax();
                Format number = new DecimalFormat("0000");
                String id = number.format(max + 1);
                String car = "car" + id + userName;
                userDao.newUser(id, userName, password, age, sex, car, date);
                userDao.newTable(car);
                resp.sendRedirect("Login/regDo.jsp");
            }else {
                req.setAttribute("exist", 1);
                req.getRequestDispatcher("Login/register.jsp").forward(req, resp);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
