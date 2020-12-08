package servlet;


import Dao.*;
import entity.Commodity;
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
    static private Map<String, Commodity> commodity_map;
    static private boolean login = false;
    static productDao productDao = new productDaoImpl();
    static userDao userDao = new userDaoImpl();

    static {
        try {
            commodity_map = productDao.get_commodity();
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

        //判断登陆状态
        if(req.getSession().getAttribute("loginUser") != null){
            login = true;
            try {
                consumer_map = userDao.get_userCar();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }else {
            login = false;
        }

        //获取操作名
        String method = req.getParameter("method");

        //初始化操作
        if (method == null){
            method = "findAll";
        }

        //初始化用户信息
        try {
            productDao.getUser();
        } catch (DaoException e) {
            e.printStackTrace();
        }

        //操作
        try {
            switch (method){

                case "reduce":
                    String id = req.getParameter("id");
                    int amount = consumer_map.get(id).getAmount();
                    if (amount > 1){
                        productDao.deleteProduct(id,amount);
                        consumer_map.get(id).setAmount(amount - 1);
                    }else{
                        productDao.deleteProduct(id,0);
                        consumer_map.remove(id);
                    }
                    req.setAttribute("map", consumer_map.values());
                    resp.sendRedirect("commodity?method=findCar");
                    break;

                case "delete":
                    id = req.getParameter("id");
                    productDao.deleteProduct(id,0);
                    consumer_map.remove(id);
                    req.setAttribute("map", consumer_map.values());
                    req.getRequestDispatcher("Show/myCar.jsp").forward(req,resp);
                    break;

                case "findAll":
                    req.setAttribute("map2", commodity_map.values());
                    req.getRequestDispatcher("Show/market.jsp").forward(req,resp);
                    break;

                case "findCar":
                    if (login == true) {
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
                    }else {
                        resp.sendRedirect("Login/cookie_login.jsp");
                    }
                    break;

                case "addAmount":
                    id = req.getParameter("id");
                    productDao.addProduct(id,consumer_map.get(id).getAmount());
                    consumer_map.get(id).setAmount(consumer_map.get(id).getAmount() + 1);
                    req.setAttribute("map", consumer_map.values());
                    resp.sendRedirect("commodity?method=findCar");
                    break;

                case "add":
                    if (login == true){
                        id = req.getParameter("id");
                        /*Set<Map.Entry<String, Commodity>> temp = commodity_map.entrySet();
                        Iterator<Map.Entry<String, Commodity>> solve = temp.iterator();
                        Set<Map.Entry<String, Commodity>> temp2 = consumer_map.entrySet();
                        Iterator<Map.Entry<String, Commodity>> solve2 = temp2.iterator();*/
                        Commodity value2;
                        if (consumer_map.get(id)==null){
                            Commodity value = commodity_map.get(id);
                            String temp_id = value.getId();
                            String name = value.getName();
                            double price = value.getPrice();
                            String type = value.getType();
                            value2 = new Commodity(temp_id,name,price,type,1);
                            consumer_map.put(id,value2);
                            productDao.addProduct(value2);
                        }else {
                            productDao.addProduct(id,consumer_map.get(id).getAmount());
                            consumer_map.get(id).setAmount(consumer_map.get(id).getAmount() + 1);
                        }
                        req.setAttribute("map", consumer_map.values());
                        resp.sendRedirect("/commodity");
                    }else {
                        resp.sendRedirect("Login/cookie_login.jsp");
                    }
                    break;

                case "clean":
                    productDao.clean();
                    consumer_map.clear();
                    resp.sendRedirect("/commodity");
                    break;

                case "find":
                    req.setAttribute("map2", commodity_map.values());
                    String keySearch = req.getParameter("keySearch");
                    req.setAttribute("keySearch",keySearch);
                    req.getRequestDispatcher("Show/finding.jsp").forward(req,resp);
                    break;
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

}
