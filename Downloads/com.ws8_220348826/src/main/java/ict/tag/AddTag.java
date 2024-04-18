
package ict.tag;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.*;

public class AddTag extends SimpleTagSupport{
    private int num1, num2;

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }
    @Override
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            int result = num1 + num2;   
            out.println("The sum of "+num1 + " and "+num2+" is "+result);
        } catch (IOException e) {
             System.out.println("error");
        }
    }
}
