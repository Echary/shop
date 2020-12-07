package Tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;
import java.io.IOException;


public class MaxTag implements SimpleTag {
    private String num1;
    private String num2;

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    @Override
    public void doTag() throws JspException, IOException {
        int a=Integer.parseInt(num1);
        int b=Integer.parseInt(num2);
        JspWriter out=pageContext.getOut();
        try{
            out.print(a>b?a:b);
        }catch (Exception e){
            out.print("输入的格式不正确");
        }

    }

    @Override
    public void setParent(JspTag jspTag) {

    }

    @Override
    public JspTag getParent() {
        return null;
    }

    PageContext pageContext;
    @Override
    public void setJspContext(JspContext jspContext) {
        this.pageContext=(PageContext) jspContext;
    }

    @Override
    public void setJspBody(JspFragment jspFragment) {

    }
}