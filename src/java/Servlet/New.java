/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import beans.News;
import beans.Tags;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.NewsModel;
import model.TagsModel;
import utils.DataManager;

/**
 *
 * @author Arkesys
 */
public class New extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // connexion 
        DataManager dataManager = (DataManager) this.getServletContext().getAttribute("dataManager");
        Connection conn = dataManager.getConnection();

        String path = request.getServletPath();

        //controle du patern 
        // /est la racine du site (voir web.xml)
        if (path.equals("/New")) {
            System.out.println("ma new");

            News n = new News();
            n.setId(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("n", n);
            //request.setAttribute("listeCategorie", categories);

            NewsModel.getNewsByid(conn, n);

            System.out.println("->" + request.getParameter("id"));

            request.setAttribute("n", n);

            List<Tags> listeTags = new ArrayList<>();
            listeTags = TagsModel.getTags(conn);

            NewsModel.getNewsTags(conn, n);

            System.out.println("->" + n.getNewsTags().size());
            request.setAttribute("listeTags", listeTags);

            request.getRequestDispatcher("/WEB-INF/news/selected.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
