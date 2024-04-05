/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.EquipmentBean;
import ict.bean.RecordBean;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class EquipmentTag extends SimpleTagSupport {

    private ArrayList list;
    private String type;

    public void setList(ArrayList list) {
        this.list = list;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void doTag() {
        if (type == "equipment") {
            try {
                PageContext pageContext = (PageContext) getJspContext();
                JspWriter out = pageContext.getOut();
                out.println("<h1>View Equipment</h1>");
                out.println("<table border='1' >");
                out.println("<tr>");
                out.println("<th>CustId</th><th>name</th><th>tel</th><th>age</th>");
                out.println("</tr>");
                ArrayList<EquipmentBean> eqs = list;
                for (int i = 0; i < eqs.size(); i++) {
                    EquipmentBean c = eqs.get(i);
                    out.println("<tr>");
                    out.println("<td>" + c.getEid() + "</td>");
                    out.println("<td>" + c.getEName() + "</td>");
                    out.println("<td>" + c.getEstatus() + "</td>");
                    out.println("<td>" + c.getQuantity() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } catch (IOException ioe) {
                System.out.println("Error generating prime: " + ioe);
            }
        } else if (type == "BRecord") {
            try {
                PageContext pageContext = (PageContext) getJspContext();
                JspWriter out = pageContext.getOut();
                out.println("<h1>View Equipment</h1>");
                out.println("<table border='1' >");
                out.println("<tr>");
                out.println("<th>CustId</th><th>name</th><th>tel</th><th>age</th>");
                out.println("</tr>");
                ArrayList<RecordBean> rbs = list;
                for (int i = 0; i < rbs.size(); i++) {
                    RecordBean c = rbs.get(i);
                    out.println("<tr>");
                    out.println("<td>" + c.getBid() + "</td>");
                    out.println("<td>" + c.getEid() + "</td>");
                    out.println("<td>" + c.getEname() + "</td>");
                    out.println("<td>" + c.getBorrowDate() + "</td>");
                    out.println("<td>" + c.getDeadline() + "</td>");

                    out.println("</tr>");
                }
                out.println("</table>");
            } catch (IOException ioe) {
                System.out.println("Error generating prime: " + ioe);
            }
        } else {
            try {
                PageContext pageContext = (PageContext) getJspContext();
                JspWriter out = pageContext.getOut();
                out.println("<h1>Wrong table</h1>");
            } catch (IOException ioe) {
                System.out.println("Error generating prime: " + ioe);
            }
        }
    }
}
