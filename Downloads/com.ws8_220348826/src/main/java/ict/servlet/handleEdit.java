/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.EquipmentBean;
import ict.db.EquipmentDB;
import ict.db.OrderDB;
import ict.db.UserRecord;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "handleEdit", urlPatterns = {"/handleEdit"})
public class handleEdit extends HttpServlet {

    private UserRecord db;
    private EquipmentDB Eqdb;
    private OrderDB order_db;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new UserRecord(dbUrl, dbUser, dbPassword);
        Eqdb = new EquipmentDB(dbUrl, dbUser, dbPassword);
        order_db = new OrderDB(dbUrl, dbUser, dbPassword);

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

        if ("add".equalsIgnoreCase(action)) {
            int year = 2024;
            int month = Integer.parseInt(request.getParameter("month"));
            int day = Integer.parseInt(request.getParameter("day"));
            int hour = Integer.parseInt(request.getParameter("hour"));
            int minute = Integer.parseInt(request.getParameter("minute"));

            LocalDateTime selectedDateTime = LocalDateTime.of(year, month, day, hour, minute);
            HttpSession session = request.getSession();
            ArrayList<EquipmentBean> eqs = (ArrayList<EquipmentBean>) session.getAttribute("equipments");
            order_db.addOrder(1, selectedDateTime);
            for (EquipmentBean eq : eqs) {
                int eid = eq.getEid();
                eq = Eqdb.queryEqById(eid);
                int count = eq.getQuantity();
                Eqdb.borrowEquipment(count, eid);
                Eqdb.checkStatus(count, eid);
                int orderId = order_db.getIdByFlag();
                order_db.addOrderItem(orderId, eid);

            }

        } else if ("return".equalsIgnoreCase(action)) {
            int bid = Integer.parseInt(request.getParameter("bid"));
            db.UpdateReturnStatus(bid);
            response.sendRedirect("/com.ws8_220348826/USERS/User.jsp");

        } else if ("takeorder".equalsIgnoreCase(action)) {
            int bid = Integer.parseInt(request.getParameter("bid"));
            db.UpdateTakeOrderStatus(bid);
            response.sendRedirect("" + request.getContextPath() + "/HandleBorrowRecord?action=cList");

        } else if ("finishOrder".equalsIgnoreCase(action)) {
            int bid = Integer.parseInt(request.getParameter("bid"));
            db.UpdateFinishStatus(bid);
            response.sendRedirect("" + request.getContextPath() + "/HandleBorrowRecord?action=OrderList");

        } else if ("addCart".equalsIgnoreCase(action)) {
            int eid = Integer.parseInt(request.getParameter("eid"));
            HttpSession session = request.getSession(); // Get the session object
            EquipmentBean eq = Eqdb.queryEqById(eid);
            ArrayList<EquipmentBean> eqs = (ArrayList<EquipmentBean>) session.getAttribute("equipments");
            session.setAttribute("E" + eid, eid);
            eqs.add(eq);
            response.sendRedirect("" + request.getContextPath() + "/HandleEquipment?action=list");

        } else if ("removeCart".equalsIgnoreCase(action)) {
            int eid = Integer.parseInt(request.getParameter("eid"));
            HttpSession session = request.getSession();
            ArrayList<EquipmentBean> eqs = (ArrayList<EquipmentBean>) session.getAttribute("equipments");
            EquipmentBean removedEquipment = null;
            for (EquipmentBean eq : eqs) {
                if (eq.getEid() == eid) {
                    removedEquipment = eq;
                    break;
                }
            }
            if (removedEquipment != null) {
                eqs.remove(removedEquipment);
            }
            session.removeAttribute("E" + eid);
            response.sendRedirect(request.getContextPath() + "/HandleEquipment?action=list");
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }

}
