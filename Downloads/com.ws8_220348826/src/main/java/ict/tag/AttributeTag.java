/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class AttributeTag extends SimpleTagSupport{
    private String message = "Default Message";

    public void setMessage(String message) {
        this.message = message;
    }
    public void doTag() {
        try {
            PageContext pageContext =(PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            out.print("Attribute example "+ message);
        } catch (IOException e) {
            
        }
    }
}
