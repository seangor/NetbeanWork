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
        if ("list".equalsIgnoreCase(action)) {
            ArrayList<RecordBean> rbs = Udb.queryBRec();
            request.setAttribute("BRecord", rbs);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/USERS/FUNCTION/ViewRecord.jsp");
            rd.forward(request, response);
        } 

    }

}
