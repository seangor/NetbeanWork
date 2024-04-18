/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import javax.servlet.jsp.*;
import java.io.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ExampleTag extends SimpleTagSupport{
    @Override
    public void doTag(){
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            out.print("Customer tag example "+ "(Example Tag)");
        } catch (IOException e) {}
    }
}
