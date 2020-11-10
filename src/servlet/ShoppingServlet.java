package servlet;

import entity.Commodity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/commodity")
public class ShoppingServlet extends HttpServlet {

    static private Map<Integer, Commodity> map = new HashMap<>();
    static private Map<Integer, Commodity> map2 = new HashMap<>();

    static {
        map2.put(1,new Commodity(1,"ºìÅôÔ¶",10.0,1,"A"));
        map2.put(2,new Commodity(2,"³ÈÅôÔ¶",5.0,2,"B"));
        map2.put(3,new Commodity(3,"»ÆÅôÔ¶",3.0,3,"C"));
        map2.put(4,new Commodity(4,"ÂÌÅôÔ¶",1.0,4,"D"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String scoreStr = req.getParameter("price");
        String stockStr = req.getParameter("stock");
        String type = req.getParameter("type");
        int stock = Integer.parseInt(stockStr);
        Integer id = Integer.parseInt(idStr);
        Double price = Double.parseDouble(scoreStr);
        Commodity commodity = new Commodity(id,name,price,stock,type);
        map.put(id, commodity);
        resp.sendRedirect("/commodity");



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String method = req.getParameter("method");

        if (method == null){
            method = "findAll";
        }



        switch (method){
            case "reduce":
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                int amount = map.get(id).getAmount();
                if (amount > 1){
                    map.get(id).setAmount(amount - 1);
                }else{
                    map.remove(id);
                }
                req.setAttribute("map",map.values());
                req.getRequestDispatcher("Show/myCar.jsp").forward(req,resp);
                break;

            case "delete":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                map.remove(id);
                req.setAttribute("map",map.values());
                req.getRequestDispatcher("Show/myCar.jsp").forward(req,resp);
                break;

            case "findAll":
                req.setAttribute("map2",map2.values());
                req.getRequestDispatcher("Show/market.jsp").forward(req,resp);
                break;

            case "findCar":
                req.setAttribute("map",map.values());
                req.getRequestDispatcher("Show/myCar.jsp").forward(req,resp);
                break;

            case "addAmount":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                map.get(id).setAmount(map.get(id).getAmount() + 1);
                req.setAttribute("map",map.values());
                req.getRequestDispatcher("Show/myCar.jsp").forward(req,resp);
                break;

            case "add":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                Set<Map.Entry<Integer, Commodity>> temp = map2.entrySet();
                Iterator<Map.Entry<Integer, Commodity>> solve = temp.iterator();
                Set<Map.Entry<Integer, Commodity>> temp2 = map.entrySet();
                Iterator<Map.Entry<Integer, Commodity>> solve2 = temp2.iterator();
                Commodity value2;
                if (map.get(id)==null){
                    Commodity value = map2.get(id);
                    Integer temp_id = value.getId();
                    String name = value.getName();
                    double price = value.getPrice();
                    String type = value.getType();
                    value2 = new Commodity(temp_id,name,price,type,1);
                    map.put(id,value2);
                }else {
                    map.get(id).setAmount(map.get(id).getAmount() + 1);
                }
                req.setAttribute("map",map.values());
                resp.sendRedirect("/commodity");
                break;

            case "clean":
                map.clear();
                resp.sendRedirect("/commodity");
                break;

            case  "find":
        }
    }

    public void compare(String word){
        Set<Map.Entry<Integer, Commodity>> temp = map2.entrySet();
        Iterator<Map.Entry<Integer, Commodity>> solve = temp.iterator();


    }

    public boolean compileKeyWord(String word, String keyWord) {
        Pattern pn = Pattern.compile(keyWord+"\\w|\\w"+keyWord+"\\w|\\w"+keyWord);
        Matcher mr = null;
        mr = pn.matcher(word);
        if (mr.find())  {
            return true;
        } else {
            return false;
        }
    }

}
