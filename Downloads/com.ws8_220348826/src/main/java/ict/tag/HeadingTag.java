package ict.tag;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;

public class HeadingTag extends SimpleTagSupport {
    private String bgColor;
    private String color = null;
    private String align = "CENTER";
    private String fontSize = "36";
    private String fontList = "Arial, Helvetica, sans-serif";
    private String border = "0";
    private String width = null;
    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontList(String fontList) {
        this.fontList = fontList;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public void setWidth(String width) {
        this.width = width;
    }



    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            out.print("<table border=" + border
                    + " bgcolor= '" + bgColor + "'"
                    + " align ='" + align + "'");
            out.print(width != null ? " width='" + width + "'" : "");
            out.print("><tr><td>");
            out.print("<span style='"
                    + "font-size: " + fontSize + "px; "
                    + "font-family: " + fontList + "; ");
            out.print(color != null ? "color: " + color + ";" : "");
            out.println("'> ");
            StringWriter sw = new StringWriter();
            this.getJspBody().invoke(sw);
            this.getJspContext().getOut().println(sw.toString());
            out.print("</span>");
            out.print("</td></tr>");
            out.print("</table>");
        } catch (Exception e) {
        }
    }
}
