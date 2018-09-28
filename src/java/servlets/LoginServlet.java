/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.User;
import domain.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 766375
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String inputUserName = request.getParameter("userName");
        
        String inputPassword = request.getParameter("password");
        
        if((((inputPassword == null) || (inputPassword.equals(""))) ||((inputUserName == null) || (inputUserName.equals(""))))) {
            request.setAttribute("error", "Invalid username or password.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);  
            return;
        }
        
        UserService uService = new UserService();
        
        User u = uService.userLogin(inputUserName, inputPassword);
        
        if(u != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userName", u.getUserName());
            response.sendRedirect("home");
            return;
        } else {
            request.setAttribute("error", "Invalid username or password.");
            request.setAttribute("userName", inputUserName);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);  
            return;
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
