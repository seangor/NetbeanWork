/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.EquipmentBean;
import ict.db.WishlistDB;
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
@WebServlet(name = "HandleWishlist", urlPatterns = {"/HandleWishlist"})
public class HandleWishlist extends HttpServlet {

    private WishlistDB WishDB;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        WishDB = new WishlistDB(dbUrl, dbUser, dbPassword);

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
        String action = request.getParameter("action");
        if ("add".equalsIgnoreCase(action)) {
            int eid = Integer.parseInt(request.getParameter("eid"));
            int wid = Integer.parseInt(request.getParameter("wid"));

            if (wid == 0) {
                WishDB.addWishlist(1, eid);
            } else {
                WishDB.delWishlist(wid);
            }
            response.sendRedirect("/com.ws8_220348826/HandleEquipment?action=list");
        } else if ("notice".equalsIgnoreCase(action)) {
            ArrayList<EquipmentBean> eq = WishDB.queryNotice();
            request.setAttribute("notification",eq);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/USERS/FUNCTION/ViewNotification.jsp");
            rd.forward(request, response);
        }
            
    }

}
