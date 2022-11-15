package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Ad> adList = DaoFactory.getAdsDao().all();
        String searchCriteria = request.getParameter("search-criteria");
        List<Ad> sortedAdList = new ArrayList<>();
        if (searchCriteria != null) {
            for (int i = 0; i < adList.size(); i++) {
                if (adList.get(i).getTitle().toLowerCase().contains(searchCriteria.toLowerCase())) {
                    sortedAdList.add(adList.get(i));
                }
            }
            request.setAttribute("ads", sortedAdList);
        } else {
            request.setAttribute("ads", adList);
        }
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

}
