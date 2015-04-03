package Servlet;

import beans.Categorie;
import beans.News;
import beans.Tags;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CategorieModel;
import model.NewsModel;

import utils.DataManager;

/**
 *
 * @author Moi
 */
public class Surf extends HttpServlet {

    private String name = "laul";
    private String pass = "1234";
    HttpSession session;

    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("**** InsideServlet::init()");

        DataManager dataManager = new DataManager();
        dataManager.setDbURL(config.getInitParameter("dbURL"));
        dataManager.setDbUserName(config.getInitParameter("DbUserName"));
        dataManager.setDbPassword(config.getInitParameter("dbPassword"));

        this.getServletContext().setAttribute("dataManager", dataManager);

        System.out.println("data : " + dataManager);

        try {
            Class.forName(config.getInitParameter("jdbcDriver"));
            System.out.println("data 11: " + dataManager);

        } catch (Exception e) {

            System.out.println(e.toString());

        }
    }

    /**
     * *
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("0");

        // connexion 
        DataManager dataManager = (DataManager) this.getServletContext().getAttribute("dataManager");
        Connection conn = dataManager.getConnection();

        String path = request.getServletPath();

        //controle du patern 
        // /est la racine du site (voir web.xml)
        if (path.equals("/Surf")) {
            System.out.println("mes news");

                                List<Categorie> categories = new ArrayList<>();
                    categories = CategorieModel.getCategories(conn);
                    System.out.println("cat = " + categories.size());
                    request.setAttribute("listeCategorie", categories);
            
            
            List<News> news = new ArrayList<>();
            news = NewsModel.getNews(conn);
            System.out.println("cat = " + news.size());
            request.setAttribute("listeNew", news);
            

                            

            request.getRequestDispatcher("/WEB-INF/news/index.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
