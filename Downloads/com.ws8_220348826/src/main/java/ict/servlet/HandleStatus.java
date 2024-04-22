/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.EquipmentBean;
import ict.bean.OrderBean;
import ict.bean.OrderitemBean;
import ict.bean.RequestBean;
import ict.bean.RequestitemBean;
import ict.db.EquipmentDB;
import ict.db.OrderDB;
import ict.db.RequestDB;
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
    private RequestDB requestdb;
    private EquipmentDB Eqdb;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        order_db = new OrderDB(dbUrl, dbUser, dbPassword);
        requestdb = new RequestDB(dbUrl, dbUser, dbPassword);
        brdb = new UserRecord(dbUrl, dbUser, dbPassword);
        Eqdb = new EquipmentDB(dbUrl, dbUser, dbPassword);
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
        if ("UpDeliver".equalsIgnoreCase(action)) {
            order_db.acceptDelivery(orderid);
            response.sendRedirect(request.getContextPath() + "/HandleCourierOrder?action=CourierOrder");
        } else if ("UpFinish".equalsIgnoreCase(action)) {
            order_db.finishDelivery(orderid);
            ArrayList<OrderitemBean> obs = order_db.queryItemById(orderid);
            OrderBean ob = order_db.queryOrderById(orderid);
            if (ob.getType().equalsIgnoreCase("1")) {
                for (OrderitemBean oib : obs) {
                    int eid = oib.getEid();
                }
                response.sendRedirect(request.getContextPath() + "/HandleCourierOrder?action=Courierdeliver");
            } else if (ob.getType().equalsIgnoreCase("2")) {
                order_db.finishDelivery(orderid);
                for (OrderitemBean oib : obs) {
                    int bid = oib.getBid();
                    int eid = oib.getEid();

                    brdb.UpdateStatus("3", bid);

//                    EquipmentBean eq = Eqdb.queryEqById(eid);
//                    int Eqquantity = eq.getQuantity();
//                    int EqEid = eq.getEid();

//
//                    Eqdb.returnEquipmentQty(Eqquantity, oib.getEid());
//                    Eqdb.checkStatus(Eqquantity, EqEid);
                }
                response.sendRedirect(request.getContextPath() + "/HandleCourierOrder?action=Courierdeliver");
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }

}
//UPDATE eqorder set Status = 'Delivering' WHERE OrderId = 38
