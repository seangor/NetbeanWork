/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author sean3
 */
public class StatusTag extends SimpleTagSupport {

    private String status;
    private String item;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            String exp = "";
            if (item.equalsIgnoreCase("request")) {
                switch (status) {
                    case "1":
                        exp = "In Progress";
                        break;
                    case "2":
                        exp = "Accepted";
                        break;
                    case "3":
                        exp = "Not Accepted";
                        break;
                }
            } else if (item.equalsIgnoreCase("order")) {
                switch (status) {
                    case "1":
                        exp = "Pending";
                        break;
                    case "2":
                        exp = "Delivering";
                        break;
                    case "3":
                        exp = "Received";
                        break;
                }
            } else if (item.equalsIgnoreCase("borrowRecord")) {
                switch (status) {
                    case "1":
                        exp = "Possessing";
                        break;
                    case "2":
                        exp = "Returning";
                        break;
                    case "3":
                        exp = "Checking Status";
                        break;
                    case "4":
                        exp = "Returned";
                        break;
                    case "5":
                        exp = "Damaged";
                        break;                }
            }
            out.println(exp);
        } catch (IOException e) {
            System.out.println("error");
        }
    }

}
