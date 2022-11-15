package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="AdsUpdateServlet", urlPatterns = "/ads/update")
public class AdsUpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String updateTitle = req.getParameter("updateTitle");
        String updateDescription = req.getParameter("updateDescription");
        User user = (User)req.getSession().getAttribute("user");
        long userId = user.getId();
        long id = Long.parseLong(req.getParameter("updateId"));

        Ad ad  = new Ad(id, userId, updateTitle, updateDescription);

        try {
            DaoFactory.getAdsDao().updateAd(ad);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the ad", e);
        }

        res.sendRedirect("/ads");
    }

}

