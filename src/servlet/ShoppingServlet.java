package servlet;

import Cookie.CookieLoginServlet;
import JDBC.Product;
import entity.Commodity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static JDBC.Product.addProduct;
import static JDBC.Product.deleteProduct;

@WebServlet("/commodity")
public class ShoppingServlet extends HttpServlet {

    static public Map<String, Commodity> consumer_map;
    static private Map<String, Commodity> commodity_map;
    static private boolean login = false;

    static {
        commodity_map = Product.get_commodity();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(CookieLoginServlet.login == true){
            login = true;
            consumer_map = JDBC.User.get_userCar();
        }else {
            login = false;
        }

        String method = req.getParameter("method");

        if (method == null){
            method = "findAll";
        }

        switch (method){
            case "reduce":
                String id = req.getParameter("id");
                int amount = consumer_map.get(id).getAmount();
                if (amount > 1){
                    deleteProduct(id,amount);
                    consumer_map.get(id).setAmount(amount - 1);
                }else{
                    deleteProduct(id,0);
                    consumer_map.remove(id);
                }
                req.setAttribute("map", consumer_map.values());
                req.getRequestDispatcher("Show/myCar.jsp").forward(req,resp);
                break;

            case "delete":
                id = req.getParameter("id");
                deleteProduct(id,0);
                consumer_map.remove(id);
                req.setAttribute("map", consumer_map.values());
                req.getRequestDispatcher("Show/myCar.jsp").forward(req,resp);
                break;

            case "findAll":
                if(login == true) {
                    req.setAttribute("login","login");

                }else {
                    req.setAttribute("login","no");
                }
                req.setAttribute("map2", commodity_map.values());
                req.getRequestDispatcher("Show/market.jsp").forward(req,resp);
                break;

            case "findCar":
                if (login == true) {
                    req.setAttribute("map", consumer_map.values());
                    req.getRequestDispatcher("Show/myCar.jsp").forward(req, resp);
                }else {
                    resp.sendRedirect("Login/cookie_login.jsp");
                }
                break;

            case "addAmount":
                id = req.getParameter("id");
                addProduct(id,consumer_map.get(id).getAmount());
                consumer_map.get(id).setAmount(consumer_map.get(id).getAmount() + 1);
                req.setAttribute("map", consumer_map.values());
                req.getRequestDispatcher("Show/myCar.jsp").forward(req,resp);
                break;

            case "add":
                if (login == true){
                    id = req.getParameter("id");
                    Set<Map.Entry<String, Commodity>> temp = commodity_map.entrySet();
                    Iterator<Map.Entry<String, Commodity>> solve = temp.iterator();
                    Set<Map.Entry<String, Commodity>> temp2 = consumer_map.entrySet();
                    Iterator<Map.Entry<String, Commodity>> solve2 = temp2.iterator();
                    Commodity value2;
                    if (consumer_map.get(id)==null){
                        Commodity value = commodity_map.get(id);
                        String temp_id = value.getId();
                        String name = value.getName();
                        double price = value.getPrice();
                        String type = value.getType();
                        value2 = new Commodity(temp_id,name,price,type,1);
                        consumer_map.put(id,value2);
                        addProduct(value2);
                    }else {
                        addProduct(id,consumer_map.get(id).getAmount());
                        consumer_map.get(id).setAmount(consumer_map.get(id).getAmount() + 1);
                    }
                    req.setAttribute("map", consumer_map.values());
                    resp.sendRedirect("/commodity");
                }else {
                    resp.sendRedirect("Login/cookie_login.jsp");
                }
                break;

            case "clean":
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
    }

    public static void logOut(){
        consumer_map.clear();
    }
}
