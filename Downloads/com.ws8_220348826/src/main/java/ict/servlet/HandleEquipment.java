/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.EquipmentBean;
import ict.db.EquipmentDB;
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
@WebServlet(name = "HandleEquipment", urlPatterns = {"/HandleEquipment"})
public class HandleEquipment extends HttpServlet {

    private EquipmentDB Edb;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

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
        String action = request.getParameter("action");

        if ("list".equalsIgnoreCase(action)) {
            ArrayList<EquipmentBean> Equipments = Edb.queryEq();
            request.setAttribute("Equipments", Equipments);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/USERS/FUNCTION/ViewEquipment.jsp");
            rd.forward(request, response);
        } else if ("cart".equalsIgnoreCase(action)) {
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/USERS/FUNCTION/ViewCart.jsp");
            rd.forward(request, response);
        } else if ("listStatus".equalsIgnoreCase(action)) {
            ArrayList<EquipmentBean> Equipments = Edb.queryEq();
            request.setAttribute("Equipments", Equipments);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/TECHNICIAN/FUNCTION/CheckEqStatus.jsp");
            rd.forward(request, response);
        } else if ("filter".equalsIgnoreCase(action)) {
            String campus = request.getParameter("campusOp");
            if (campus == null) {
                campus = "0";
            }
            ArrayList<EquipmentBean> eqs = new ArrayList<EquipmentBean>();
            if (campus.equalsIgnoreCase("0")) {
                eqs = Edb.queryEq();
            } else {
                int cid = Integer.parseInt(campus);
                eqs = Edb.filterEqByCampus(cid);
            }
            request.setAttribute("Equipments", eqs);
            request.setAttribute("campusOp", campus);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/USERS/FUNCTION/ViewEquipment.jsp");
            rd.forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }

    }

}

//filterEqByCampus
