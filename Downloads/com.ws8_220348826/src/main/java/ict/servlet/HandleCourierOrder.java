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
@WebServlet(name = "HandleCourierOrder", urlPatterns = {"/HandleCourierOrder"})
public class HandleCourierOrder extends HttpServlet {

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
        if ("CourierOrder".equalsIgnoreCase(action)) {
            ArrayList<OrderBean> obs = orderdb.queryOrderByStatus("approved");
            request.setAttribute("OrderList", obs);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/COURIER/FUNCTION/ViewOrder.jsp");
            rd.forward(request, response);
        } else if ("Courierdeliver".equalsIgnoreCase(action)) {
            ArrayList<OrderBean> obs = orderdb.queryOrderByStatus("delivering");
            ArrayList<OrderBean> ob2 = orderdb.queryOrderByStatus("Received");
            ArrayList<OrderBean> combinedList = new ArrayList<>();
            combinedList.addAll(obs);
            combinedList.addAll(ob2);
            request.setAttribute("OrderList", combinedList);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/COURIER/FUNCTION/ViewDelivery.jsp");
            rd.forward(request, response);
        }
    }
}
