/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.OrderBean;
import ict.bean.RequestBean;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author sean3
 */
@WebServlet(name = "HandleRequest", urlPatterns = {"/HandleRequest"})
public class HandleRequest extends HttpServlet {

    private RequestDB requestdb;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        requestdb = new RequestDB(dbUrl, dbUser, dbPassword);
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
        if ("list".equalsIgnoreCase(action)) {
            HttpSession session = request.getSession();
            ArrayList<RequestBean> obs = new ArrayList<RequestBean>();
            if (((String) session.getAttribute("Account")).equals("User")) {
                obs = requestdb.queryRequestByUid(1);
            } else if (((String) session.getAttribute("Account")).equals("Technician")) {
                obs = requestdb.queryRequest();
            }
            request.setAttribute("RequestList", obs);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/ViewRequest.jsp");

            rd.forward(request, response);
        }
    }

    
}
