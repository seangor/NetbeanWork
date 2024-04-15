/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.OrderBean;
import ict.bean.OrderitemBean;
import ict.db.OrderDB;
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
@WebServlet(name = "HandleOrderItem", urlPatterns = {"/HandleOrderItem"})
public class HandleOrderItem extends HttpServlet {

    private OrderDB orderdb;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        orderdb = new OrderDB(dbUrl, dbUser, dbPassword);

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        if ("list".equalsIgnoreCase(action)) {
            ArrayList<OrderitemBean> obs = orderdb.queryItemById(orderid);
            request.setAttribute("OrderItemList", obs);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/ViewOrderItem.jsp");
            rd.forward(request, response);
        } else if ("approvelist".equalsIgnoreCase(action)) {
            ArrayList<OrderitemBean> obs = orderdb.queryItemById(orderid);
            OrderBean ob = orderdb.queryOrderById(orderid);
            request.setAttribute("Order", ob);
            request.setAttribute("OrderItemList", obs);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/ViewOrderItem.jsp");
            rd.forward(request, response);
        }
    }
}
