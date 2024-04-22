/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.EquipmentBean;
import ict.bean.RecordBean;
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
@WebServlet(name = "HandleBorrowRecord", urlPatterns = {"/HandleBorrowRecord"})
public class HandleBorrowRecord extends HttpServlet {

    private UserRecord Udb;
    private EquipmentDB Edb;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        Udb = new UserRecord(dbUrl, dbUser, dbPassword);
        Edb = new EquipmentDB(dbUrl, dbUser, dbPassword);
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
        if ("Recordlist".equalsIgnoreCase(action)) {
            ArrayList<RecordBean> rbs = Udb.queryBRec();
            request.setAttribute("BRecord", rbs);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/USERS/FUNCTION/ViewRecord.jsp");
            rd.forward(request, response);
        } else if ("checkout".equalsIgnoreCase(action)) {
            ArrayList<RecordBean> rbs = new ArrayList<RecordBean>();
            String[] bidlength = request.getParameterValues("selectedEq");
            for (int i = 0; i < bidlength.length; i++) {
                int bid = Integer.parseInt(bidlength[i]);
                RecordBean rb = Udb.queryBRecById(bid);
                rbs.add(rb);
            }
            request.setAttribute("CheckOutList", rbs);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/USERS/FUNCTION/ViewReturnCheckout.jsp");
            rd.forward(request, response);
        } else if ("checkReturn".equalsIgnoreCase(action)) {

            String status3 = "3";
            String status4 = "4";
            String status5 = "5";

            ArrayList<RecordBean> rbs3 = (ArrayList<RecordBean>) Udb.queryBRecByStatus(status3);
            ArrayList<RecordBean> rbs4 = (ArrayList<RecordBean>) Udb.queryBRecByStatus(status4);
            ArrayList<RecordBean> rbs5 = (ArrayList<RecordBean>) Udb.queryBRecByStatus(status5);

            ArrayList<RecordBean> combinedList = new ArrayList<>();
            combinedList.addAll(rbs3);
            combinedList.addAll(rbs4);
            combinedList.addAll(rbs5);

            request.setAttribute("BRecord", combinedList);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/USERS/FUNCTION/ViewRecord.jsp");
            rd.forward(request, response);
        }

    }

}
