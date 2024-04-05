/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sean3
 */
@WebServlet(name = "test", urlPatterns = {"/test"})
public class test extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
        String id =request.getParameter("id");
        String name =request.getParameter("name");
        String tel =request.getParameter("tel");
        int age = Integer.parseInt(request.getParameter("age"));
        boolean isSuccess = db.addRecord(id, name, tel, age);
        if (id != null) {
                    if (isSuccess) {
            response.sendRedirect("handleCustomer?actiom=list");
        }
        }

        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }

}
