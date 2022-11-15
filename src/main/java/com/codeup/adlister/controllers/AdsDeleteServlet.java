package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.MySQLAdsDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdsDeleteServlet", urlPatterns = "/ads/delete")
public class AdsDeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
       long id = Long.parseLong(req.getParameter("ad_id"));
//        long id = (long) req.getParameter("ad_id");
//        Long.parseLong()
//        System.out.println(req.getParameter("ad_id"));

        try {
            DaoFactory.getAdsDao().deleteAd(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete ad", e);
        }

        res.sendRedirect("/ads");
    }

}
