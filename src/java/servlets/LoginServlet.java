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
import javax.servlet.http.Cookie;
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
        HttpSession session = request.getSession();
       
        if(request.getParameter("logout") != null){
            (session).invalidate();
            request.setAttribute("error", "The user has been successfully logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
        if(session.getAttribute("userName") != null) {
            response.sendRedirect("home");
            return;
        }
        
        Cookie[] cookies = request.getCookies();
        String cookieName = "userName";
        boolean isLoggedIn = false;
        for (Cookie c: cookies) {
            if(cookieName.equals(c.getName())) {
                request.setAttribute("userName", c.getValue());
                request.setAttribute("checked", "checked");
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
            if((request.getParameter("persist") != null) &&(request.getParameter("persist")).equals("true")) {
                Cookie c = new Cookie("userName", u.getUserName());
                c.setMaxAge(60*60*24*365); // Let cookie persist for 1 year max
                c.setPath("/");
                response.addCookie(c);
            }
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
