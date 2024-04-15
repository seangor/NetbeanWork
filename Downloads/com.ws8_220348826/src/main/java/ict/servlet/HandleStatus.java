/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.EquipmentBean;
import ict.bean.OrderitemBean;
import ict.db.EquipmentDB;
import ict.db.OrderDB;
import ict.db.UserRecord;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sean3
 */
@WebServlet(name = "HandleStatus", urlPatterns = {"/HandleStatus"})
public class HandleStatus extends HttpServlet {

    private UserRecord brdb;
    private OrderDB order_db;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        order_db = new OrderDB(dbUrl, dbUser, dbPassword);

        brdb = new UserRecord(dbUrl, dbUser, dbPassword);
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
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        if ("UpApprove".equalsIgnoreCase(action)) {
            order_db.apporveOrder(orderid);
            response.sendRedirect(request.getContextPath() + "/HandleOrder?action=apporvelist");
        } else if ("UpDeliver".equalsIgnoreCase(action)) {
            order_db.acceptDelivery(orderid);
            response.sendRedirect(request.getContextPath() + "/HandleOrder?action=CourierOrder");
        } else if ("UpFinish".equalsIgnoreCase(action)) {
            order_db.finishDelivery(orderid);
            ArrayList<OrderitemBean> obs = order_db.queryItemById(orderid);

            for (OrderitemBean ob : obs) {
                int eid = ob.getEid();
                brdb.addBRecord(1, eid);
            }
            response.sendRedirect(request.getContextPath() + "/HandleOrder?action=Courierdeliver");
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }

}
