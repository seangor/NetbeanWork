/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.EquipmentBean;
import ict.bean.RequestBean;
import ict.bean.RequestitemBean;
import ict.db.EquipmentDB;
import ict.db.RequestDB;
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
@WebServlet(name = "HandleRequestItem", urlPatterns = {"/HandleRequestItem"})
public class HandleRequestItem extends HttpServlet {

    private RequestDB requestdb;
        private EquipmentDB Edb;


    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        requestdb = new RequestDB(dbUrl, dbUser, dbPassword);
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
        int requestid = Integer.parseInt(request.getParameter("requestid"));
        if ("list".equalsIgnoreCase(action)) {
            ArrayList<RequestitemBean> obs = requestdb.queryItemById(requestid);
            request.setAttribute("RequestItemList", obs);
            RequestBean ob = requestdb.queryRequestById(requestid);
            request.setAttribute("BorrowRequest", ob);
            ArrayList<EquipmentBean> Equipments = new ArrayList<EquipmentBean>();
            for (RequestitemBean rib : obs) {
              EquipmentBean eb = Edb.queryEqById(rib.getEid());
              Equipments.add(eb);
            }
            request.setAttribute("Equipments", Equipments);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/ViewRequestItem.jsp");
            rd.forward(request, response);
        }
    }
}
