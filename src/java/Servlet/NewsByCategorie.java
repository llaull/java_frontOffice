/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import beans.Categorie;
import beans.News;
import beans.Tags;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategorieModel;
import model.NewsModel;
import utils.DataManager;

/**
 *
 * @author Arkesys
 */
public class NewsByCategorie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // connexion 
        DataManager dataManager = (DataManager) this.getServletContext().getAttribute("dataManager");
        Connection conn = dataManager.getConnection();

        String path = request.getServletPath();

        //controle du patern 
        if (path.equals("/viewByCat")) {

            //envoie les categorie a la jsp
            List<Categorie> categories = new ArrayList<>();
            categories = CategorieModel.getCategories(conn);
            request.setAttribute("listeCategorie", categories);

            Categorie c = new Categorie();
            c.setId(Integer.parseInt(request.getParameter("categorie")));
            System.out.println("mes news avec la categorie -> " + Integer.parseInt(request.getParameter("categorie")));

            List<News> news = new ArrayList<>();
            news = NewsModel.getNewsCategorie(conn, c);
            request.setAttribute("listeNew", news);

            request.getRequestDispatcher("/WEB-INF/news/index.jsp").forward(request, response);

        }
    }

}