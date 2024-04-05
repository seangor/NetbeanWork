/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;


import ict.bean.EquipmentBean;
import ict.db.EquipmentDB;
import ict.db.UserRecord;
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
@WebServlet(name = "handleEdit", urlPatterns = {"/USERS/FUNCTION/handleEdit"})
public class handleEdit extends HttpServlet {

    private UserRecord db;
    private EquipmentDB Eqdb;
    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new UserRecord(dbUrl, dbUser, dbPassword);
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

        if ("add".equalsIgnoreCase(action)) {
            int eid = Integer.parseInt(request.getParameter("eid"));
            EquipmentBean eq = Eqdb.queryEqById(eid);
            int count = eq.getQuantity();
            if (count > 0) {
            Eqdb.borrowEquipment(count, eid);
            Eqdb.checkStatus(count, eid);
            boolean isSuccess = db.addBRecord(1, eid);
            if (isSuccess) {
            response.sendRedirect("/com.ws8_220348826/HandleEquipment?action=list");
            }
            } else {
            PrintWriter out = response.getWriter();
            out.println("This Equipment is not available!");
            }
        }  else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }

}
