package action;

import Dao.DaoException;
import service.superDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/superLogin")
public class SuperLoginServlet extends HttpServlet {

    private static boolean check = false;
    static Dao.superDao superDao = new superDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String superName = req.getParameter("superName");
        String superPassword =  req.getParameter("superPassword");
        try {
            check = superDao.check(superName,superPassword);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        if (check == true){

            req.setAttribute("superName", superName);
            req.setAttribute("superPassword", superPassword);
            req.setAttribute("check",check);
            req.getRequestDispatcher("Login/superWelcome.jsp").forward(req, resp);

        }else {
            resp.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = resp.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('用户名或密码错误!');");
            out.println("history.back();");
            out.println("</script>");
        }
    }

}
