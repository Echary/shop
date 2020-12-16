package action;


import Dao.*;
import entity.Commodity;
import service.carDaoImpl;
import service.userDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/commodity")
public class ShoppingServlet extends HttpServlet {

    static public Map<String, Commodity> consumer_map;
    public static Map<String, Commodity> commodity_map;
    static carDao carDao = new carDaoImpl();
    static userDao userDao = new userDaoImpl();

    static {
        try {
            commodity_map = carDao.get_commodity();
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //初始化商品列表
        {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String scoreStr = req.getParameter("price");
        String stockStr = req.getParameter("stock");
        String type = req.getParameter("type");
        int stock = Integer.parseInt(stockStr);
        Double price = Double.parseDouble(scoreStr);
        Commodity commodity = new Commodity(id,name,price,stock,type);
        consumer_map.put(id, commodity);
        resp.sendRedirect("/commodity");
        }
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //获取操作名
        String method = req.getParameter("method");

        //判断登陆状态
        if (req.getSession().getAttribute("superUser") == null){
            if(req.getSession().getAttribute("loginUser") != null){
                try {
                    consumer_map = userDao.get_userCar();
                    //初始化用户信息
                    try {
                        carDao.getUser();
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }

        }else {
            method = "super";
        }

        //初始化操作
        if (method == null){
            method = "findAll";
        }

        //操作
        try {
            switch (method){

                case "reduce":
                    String id = req.getParameter("id");
                    int amount = consumer_map.get(id).getAmount();
                    if (amount > 1){
                        carDao.deleteProduct(id,amount);
                        consumer_map.get(id).setAmount(amount - 1);
                    }else{
                        carDao.deleteProduct(id,0);
                        consumer_map.remove(id);
                    }
                    req.setAttribute("map", consumer_map.values());
                    req.getRequestDispatcher("Show/myCar.jsp").forward(req,resp);
                    break;

                case "delete":
                    id = req.getParameter("id");
                    carDao.deleteProduct(id,0);
                    consumer_map.remove(id);
                    req.setAttribute("map", consumer_map.values());
                    req.getRequestDispatcher("Show/myCar.jsp").forward(req,resp);
                    break;

                case "findAll":
                    req.setAttribute("map2", commodity_map.values());
                    req.getRequestDispatcher("Show/market.jsp").forward(req,resp);
                    break;

                case "findCar":
                    if (consumer_map.isEmpty()) {
                        resp.setContentType("text/html; charset=UTF-8"); //转码
                        PrintWriter out = resp.getWriter();
                        out.flush();
                        out.println("<script>");
                        out.println("alert('购物车为空,请添加购物车!');");
                        out.println("history.back();");
                        out.println("</script>");
                    }else {
                        req.setAttribute("map", consumer_map.values());
                        req.getRequestDispatcher("Show/myCar.jsp").forward(req, resp);
                    }

                    break;

                case "addAmount":
                    id = req.getParameter("id");
                    carDao.addProduct(id,consumer_map.get(id).getAmount());
                    consumer_map.get(id).setAmount(consumer_map.get(id).getAmount() + 1);
                    /*req.setAttribute("map", consumer_map.values());*/
                    resp.sendRedirect("commodity?method=findCar");
                    break;

                case "add":
                    id = req.getParameter("id");
                    Commodity value2;
                    if (consumer_map.get(id)==null){
                        Commodity value = commodity_map.get(id);
                        String temp_id = value.getId();
                        String name = value.getName();
                        double price = value.getPrice();
                        String type = value.getType();
                        value2 = new Commodity(temp_id,name,price,type,1);
                        consumer_map.put(id,value2);
                        carDao.addProduct(value2);
                    }else {
                        carDao.addProduct(id,consumer_map.get(id).getAmount());
                        consumer_map.get(id).setAmount(consumer_map.get(id).getAmount() + 1);
                    }
                    req.setAttribute("map", consumer_map.values());
                    resp.sendRedirect("/commodity");
                    break;

                case "clean":
                    carDao.clean();
                    consumer_map.clear();
                    resp.sendRedirect("/commodity");
                    break;

                case "find":
                    req.setAttribute("map2", commodity_map.values());
                    String keySearch = req.getParameter("keySearch");
                    req.setAttribute("keySearch",keySearch);
                    req.getRequestDispatcher("Show/finding.jsp").forward(req,resp);
                    break;

                case "super":
                    resp.sendRedirect("/super");
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

}
