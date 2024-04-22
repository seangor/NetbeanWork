/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "HandleLogin", urlPatterns = {"/HandleLogin"})
public class HandleLogin extends HttpServlet {

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

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
        if ("User".equalsIgnoreCase(action)) {
            HttpSession session = request.getSession();
            session.setAttribute("Account", "User");
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/USERS/User.jsp");
            rd.forward(request, response);
        } else if ("Technician".equalsIgnoreCase(action)) {
         HttpSession session = request.getSession();
            session.setAttribute("Account", "Technician");
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/TECHNICIAN/Technician.jsp");
            rd.forward(request, response);
        }else if ("Courier".equalsIgnoreCase(action)) {
         HttpSession session = request.getSession();
            session.setAttribute("Account", "Courier");
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/COURIER/Courier.jsp");
            rd.forward(request, response);
        }
        

    }

}
