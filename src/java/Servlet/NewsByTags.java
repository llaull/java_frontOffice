package Servlet;

import beans.Categorie;
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
import model.CategorieModel;
import model.NewsModel;
import model.TagsModel;
import utils.DataManager;

/**
 *
 * @author Moi
 */
public class NewsByTags extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // connexion 
        DataManager dataManager = (DataManager) this.getServletContext().getAttribute("dataManager");
        Connection conn = dataManager.getConnection();

        String path = request.getServletPath();

        //controle du patern 
        // /est la racine du site (voir web.xml)
        if (path.equals("/viewByTag")) {

            //envoie les categorie a la jsp
            List<Categorie> categories = new ArrayList<>();
            categories = CategorieModel.getCategories(conn);
            System.out.println("cat = " + categories.size());
            request.setAttribute("listeCategorie", categories);

            Tags t = new Tags();
            t.setId(Integer.parseInt(request.getParameter("id")));
            System.out.println("mes news avec le tag -> " + Integer.parseInt(request.getParameter("id")));

            List<News> news = new ArrayList<>();
            news = NewsModel.getNewsTag(conn, t);
            System.out.println("news -> " + news.size());
            request.setAttribute("listeNew", news);

            request.getRequestDispatcher("/WEB-INF/news/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
