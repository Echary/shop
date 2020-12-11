package servlet;

import Dao.*;
import entity.Commodity;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Map;


@WebServlet("/super")
public class SuperServlet extends HttpServlet {

    public static Map<String, Commodity> commodity_map;
    public static Map<String, User> user_map;
    static private boolean login = false;
    static superDao superDao = new superDaoImpl();
    static carDao carDao = new carDaoImpl();

    static {
        try {
            commodity_map = carDao.get_commodity();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    String id;
    String name;
    double price;
    int stock;
    String type;
    String password;
    int age;
    int sex;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取操作名
        String operate = req.getParameter("operate");

        //判断登陆状态
        if (req.getSession().getAttribute("superUser") == null){
            login = true;
        }else {
            login = false;
        }

        //初始化操作
        if (operate == null){
            operate = "findAll";
        }


        //操作
        switch (operate){

            case "findAll":
                req.setAttribute("map2", commodity_map.values());
                req.getRequestDispatcher("Show/super.jsp").forward(req,resp);
                break;

            case "find":
                req.setAttribute("map2", commodity_map.values());
                String keySearch = req.getParameter("keySearch");
                req.setAttribute("keySearch",keySearch);
                req.getRequestDispatcher("Show/superFinding.jsp").forward(req,resp);
                break;

            case "more":
                id = req.getParameter("id");
                Commodity value = commodity_map.get(id);
                req.setAttribute("message",value);
                req.getRequestDispatcher("Show/commodityUpdate.jsp").forward(req,resp);
                break;

            case "delete":
                id = req.getParameter("id");
                try {
                    superDao.delete(id);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                commodity_map.remove(id);
                resp.sendRedirect("commodity?operate=findAll");
                break;

            case "update":
                id = req.getParameter("id");
                name =  req.getParameter("name");
                price = Double.parseDouble(req.getParameter("price"));
                stock = Integer.parseInt(req.getParameter("stock"));
                type = req.getParameter("type");
                try {
                    superDao.commodityUpdate(id,name,price,stock,type);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                Commodity change = commodity_map.get(id);
                change.setName(name);
                change.setPrice(price);
                change.setStock(stock);
                change.setType(type);
                resp.sendRedirect("commodity?operate=findAll");
                break;

            case "add":
                try {
                    int max = superDao.getMax();
                    Format number = new DecimalFormat("000");
                    id = number.format(max + 1);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                name =  req.getParameter("name");
                price = Double.parseDouble(req.getParameter("price"));
                stock = Integer.parseInt(req.getParameter("stock"));
                type = req.getParameter("type");
                try {
                    superDao.add(id,name,price,stock,type);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                commodity_map.put(id, new Commodity(id,name,price,stock,type));
                resp.sendRedirect("commodity?operate=findAll");
                break;

            case "manage":
                try {
                    if (user_map == null) user_map = superDao.getUser();
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                req.setAttribute("User",user_map.values());
                req.getRequestDispatcher("Show/superManage.jsp").forward(req,resp);
                break;

            case "userMore":
                id = req.getParameter("id");
                User userMessage = user_map.get(id);
                req.setAttribute("userMessage",userMessage);
                req.getRequestDispatcher("Show/userUpdate.jsp").forward(req,resp);
                break;

            case "userUpdate":
                id = req.getParameter("id");
                name =  req.getParameter("name");
                password =  req.getParameter("password");
                age = Integer.parseInt(req.getParameter("age"));

                String sexStr = req.getParameter("sex");

                if (sexStr.equals("男")){
                    sex = 0;
                }else {
                    sex = 1;
                }
                String car = "car" + id + name;
                try {
                    superDao.updateUser(id,name,password,age,sex,car);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                resp.sendRedirect("commodity?operate=manage");
                break;

            case "userFind":
                try {
                    if (user_map == null) user_map = superDao.getUser();
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                req.setAttribute("User",user_map.values());
                keySearch = req.getParameter("keySearch");
                req.setAttribute("keySearch",keySearch);
                req.getRequestDispatcher("Show/superMF.jsp").forward(req,resp);
                break;
        }

    }
}
