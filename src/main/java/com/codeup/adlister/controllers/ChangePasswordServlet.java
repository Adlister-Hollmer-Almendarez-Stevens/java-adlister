package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = "/change-password")
public class ChangePasswordServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/profile");
        }
        request.getRequestDispatcher("/WEB-INF/change-password.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");
        String confirmNewPassword = request.getParameter("confirm-new-password");
        System.out.println("username: " + user.getUsername());
        System.out.println("old password: " + user.getPassword());

        boolean validOldPassword = BCrypt.checkpw(oldPassword, user.getPassword());
        if (validOldPassword && newPassword.equals(confirmNewPassword)) {
            String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            user.setPassword(hashedNewPassword);
            System.out.println("new password: " + user.getPassword());
            long userId = user.getId();
            DaoFactory.getUsersDao().updateUser(user, userId);
            response.sendRedirect("/");
        }
    }
}
