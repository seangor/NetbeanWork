/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.EquipmentBean;
import ict.bean.RecordBean;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author sean3
 */
@WebServlet(name = "handleEdit", urlPatterns = {"/handleEdit"})
public class handleEdit extends HttpServlet {

    private UserRecord db;
    private EquipmentDB Eqdb;
    private OrderDB order_db;
    private RequestDB request_db;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new UserRecord(dbUrl, dbUser, dbPassword);
        Eqdb = new EquipmentDB(dbUrl, dbUser, dbPassword);
        order_db = new OrderDB(dbUrl, dbUser, dbPassword);
        request_db = new RequestDB(dbUrl, dbUser, dbPassword);
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
            DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");

            String formattedDate = selectedDateTime.format(dateformatter);
            String formattedTime = selectedDateTime.format(timeformatter);
            HttpSession session = request.getSession();
            String type = request.getParameter("type");
            if (type.equalsIgnoreCase("1")) {
                boolean success = request_db.addRequest(1, formattedDate, formattedTime);
                int requestId = request_db.getIdByFlag();
                ArrayList<EquipmentBean> eqs = new ArrayList<EquipmentBean>();
                eqs = (ArrayList<EquipmentBean>) session.getAttribute("equipments");
                for (EquipmentBean eq : eqs) {
                    int eid = eq.getEid();
//                    eq = Eqdb.queryEqById(eid);
//                    int count = eq.getQuantity();
//                    Eqdb.borrowEquipmentQty(count, eid);
//                    Eqdb.checkStatus(count, eid);
                    request_db.addRequestItem(requestId, eid);
                    session.removeAttribute("E" + eid);
                    session.removeAttribute("equipments");
                }
                request_db.UpdateFlag();

                if (success) {
                    response.sendRedirect("" + request.getContextPath() + "/USERS/User.jsp");
                }
            } else if (type.equalsIgnoreCase("2")) {
                boolean success = order_db.addOrder(1, formattedDate, formattedTime, type);
                int orderId = order_db.getIdByFlag();
                order_db.UpdateFlag();
                ArrayList<RecordBean> rbs = (ArrayList<RecordBean>) session.getAttribute("Returnequipments");
                for (RecordBean rb : rbs) {
                    int eid = rb.getEid();
                    int bid = rb.getBid();
                    order_db.addOrderItem(orderId, eid, bid);
                    db.ReturnEquipment(bid);
                }
                if (success) {
                    response.sendRedirect("" + request.getContextPath() + "/HandleBorrowRecord?action=Recordlist");
                }
            }
        } else if ("addCart".equalsIgnoreCase(action)) {
            int eid = Integer.parseInt(request.getParameter("eid"));
            int cid = Integer.parseInt(request.getParameter("cid"));
            HttpSession session = request.getSession(); // Get the session object
            EquipmentBean eq = Eqdb.queryEqByCampusAndEquipment(cid, eid);
            ArrayList<EquipmentBean> eqs = (ArrayList<EquipmentBean>) session.getAttribute("equipments");
            session.setAttribute("E" + eid, eid);
            session.setAttribute("C" + cid, cid);

            eqs.add(eq);
            response.sendRedirect("" + request.getContextPath() + "/HandleEquipment?action=filter&campusOp=" + cid + "");

        } else if ("removeCart".equalsIgnoreCase(action)) {
            int eid = Integer.parseInt(request.getParameter("eid"));
            int cid = Integer.parseInt(request.getParameter("cid"));

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
            session.removeAttribute("C" + cid);

            response.sendRedirect("" + request.getContextPath() + "/HandleEquipment?action=filter&campusOp="+cid+"");
        } else if ("addOrder".equalsIgnoreCase(action)) {
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
        } else if ("UpApprove".equalsIgnoreCase(action)) {
            int requestid = Integer.parseInt(request.getParameter("requestid"));
            int[] bidlist;
            request_db.apporveOrder(requestid);
            ArrayList<RequestitemBean> obs = request_db.queryItemById(requestid);
            bidlist = new int[obs.size()];  // Initialize bidlist as an int array
            for (int i = 0; i < obs.size(); i++) {
                RequestitemBean oib = obs.get(i);
                int eid = oib.getEid();
                EquipmentBean eq = Eqdb.queryEqById(eid);
                int count = eq.getQuantity();
                Eqdb.borrowEquipmentQty(count, eid);
                Eqdb.checkStatus(count, eid);
                db.addBRecord(1, eid);
                int bid = db.getIdByFlag();
                bidlist[i] = bid;  // Assign the bid value to the corresponding index in bidlist
                db.UpdateFlag();
            }
            RequestBean rb = request_db.queryRequestById(requestid);
            String type = "1";

            boolean success = order_db.addOrder(rb.getUid(), rb.getDeliverdate(), rb.getDelivertime(), type);
            int orderid = order_db.getIdByFlag();
            for (int i = 0; i < obs.size(); i++) {
                RequestitemBean oib = obs.get(i);
                int eid = oib.getEid();
                int bid = bidlist[i];  // Retrieve the bid value from the bidlist array
                order_db.addOrderItem(orderid, eid, bid);
            }

            order_db.UpdateFlag();
            if (success) {
                response.sendRedirect(request.getContextPath() + "/HandleLogin?action=Technician");
            }
        } else if ("UpdateReturn".equalsIgnoreCase(action)) {
            ArrayList<RecordBean> rbs = new ArrayList<RecordBean>();
            String[] bidlength = request.getParameterValues("selectedEq");
            for (int i = 0; i < bidlength.length; i++) {
                int bid = Integer.parseInt(bidlength[i]);
                RecordBean rb = db.queryBRecById(bid);
                int eid = rb.getEid();
                db.UpdateStatus("4", bid);
                EquipmentBean eq = Eqdb.queryEqById(eid);
                int Eqquantity = eq.getQuantity();
                int EqEid = eq.getEid();

                Eqdb.returnEquipmentQty(Eqquantity, EqEid);
                Eqdb.checkStatus(Eqquantity, EqEid);
            }
            RequestDispatcher rd;
            response.sendRedirect("" + request.getContextPath() + "/HandleBorrowRecord?action=checkReturn");
        } else if ("reportDamage".equalsIgnoreCase(action)) {
            int bid = Integer.parseInt(request.getParameter("bid"));
            db.UpdateStatus("5", bid);
            response.sendRedirect("" + request.getContextPath() + "/HandleBorrowRecord?action=checkReturn");
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }

}

//INSERT INTO your_table_name (EName, EStatus, Quantity, imgsrc, description, cid)
//VALUES
//    ('Laptop', 'Available', 1, 'laptop.jpg', NULL, 0),
//    ('Projector', 'Available', 1, 'projector.jpg', NULL, 0),
//    ('Microscope', 'Available', 1, 'microscope.jpg', NULL, 0),
//    ('Camera', 'Available', 1, 'camera.jpg', NULL, 0),
//    ('Headphones', 'Available', 2, 'headphone.jpg', NULL, 0);reportDamage
