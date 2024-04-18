/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author sean3
 */
public class IconTag extends SimpleTagSupport{
    private String message;
    private String color;


    private String bgColor;
    private String icon;
    private Random generator = new Random((new Date()).getTime());
    private String getRandomColor() {
        String[] num = {"1","2","3","4","5","6","7","8","9","0","A","B","C","D","E","F"};
        String rcolor= "";
        for (int i=0; i<6; i++) {
            rcolor+=num[generator.nextInt(16)];
        }
        return rcolor;
    }
    
    public IconTag(){
        icon="ski";
        bgColor="FFFFFF";
        message="default";
        this.color = getRandomColor();
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public void doTag(){
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
             String url = "https://chart.googleapis.com/chart?chst=d_bubble_icon_text_small&chld="+icon+"|bb|" + message + "|" + bgColor + "|" + color;
            out.print("<img src=\"" + url + "\" /><br/>");
        } catch (IOException e) {
                        e.printStackTrace();
        }
    }
}
