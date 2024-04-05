/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.CustomerBean;
import ict.db.CustomerDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sean3
 */
@WebServlet(name = "HandleCustomer", urlPatterns = {"/handleCustomer"})
public class HandleCustomer extends HttpServlet {

    private CustomerDB db;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new CustomerDB(dbUrl, dbUser, dbPassword);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        processRequest(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equalsIgnoreCase(action)) {
            ArrayList<CustomerBean> customers = db.queryCust();
            request.setAttribute("customers", customers);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listCustomers.jsp");
            rd.forward(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            String id = request.getParameter("id");
            boolean isSuccess = db.delRecord(id);
            if (id != null) {
                if (isSuccess) {
                    response.sendRedirect("handleCustomer?action=list");
                }
            }
        } else if ("getEditCustomer".equalsIgnoreCase(action)) {
            String id = request.getParameter("id");
            CustomerBean customer = db.queryCustByID(id);
            request.setAttribute("c", customer);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/editCustomer.jsp");
            rd.forward(request, response);
            
        }else if ("search".equalsIgnoreCase(action)) {
            String name = request.getParameter("name");
            if (name != null) {
            ArrayList<CustomerBean> customers = db.queryCustByName(name);
            request.setAttribute("customers", customers);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listCustomers.jsp");
            rd.forward(request, response);
            }
        }else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }

}
