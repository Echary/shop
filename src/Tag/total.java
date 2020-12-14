package Tag;
import entity.Commodity;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import static action.ShoppingServlet.consumer_map;

public class total extends TagSupport {

    private static double total;

    public int doStartTag() throws JspException {

        for (Commodity value : consumer_map.values()) {
            double temp = 0;
            temp = value.getAmount()*value.getPrice();
            total = temp + total;
        }

        PageContext pg = super.pageContext;
        JspWriter out=pg.getOut();
        try{
            out.print("<th class=\"nobg\" colspan=\"7\">×Ü¼Û:" + total + "</th>");
            total = 0;
        }catch(IOException e){
        }
        return TagSupport.EVAL_BODY_INCLUDE;
    }
}