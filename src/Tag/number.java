package Tag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import static Listener.sessionListener.userNumber;


public class number extends TagSupport {

    public int doStartTag() throws JspException {

        PageContext pg = super.pageContext;
        JspWriter out=pg.getOut();
        try{
            out.print("<body>\n" +
                    "    在线人数: " +  userNumber + "<br/>\n" +
                    "</body>");
        }catch(IOException e){
        }
        return TagSupport.EVAL_BODY_INCLUDE;
    }
}





