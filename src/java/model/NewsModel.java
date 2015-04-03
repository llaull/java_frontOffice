package model;

import beans.News;
import beans.Tags;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arkesys
 */
public class NewsModel {

    /**
     * affiche les news d'un tags commun
     *
     * @param con
     * @param n
     */
    public static void getNewsTags(Connection con, News n) {

        String sql = "SELECT T.id, T.value FROM tags AS T INNER JOIN news_tag AS nT ON T.id = nT.tag_ID WHERE nT.news_ID=?";

        try {

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, n.getId());
            System.out.println("News selectionner " + n.getId());
            try {
                ResultSet rs = stmt.executeQuery();

                try {

                    while (rs.next()) {

                        Tags tag = new Tags();

                        tag.setId(rs.getInt("id"));
                        tag.setValue(rs.getString("value"));

                        n.getNewsTags().add(tag);

                    }

                } finally {
                    rs.close();
                }

            } finally {
                stmt.close();
            }

        } catch (SQLException e) {
            System.out.println("ex " + e);
        }

    }

    /**
     * SELECT UNE new avec son id
     *
     * @param con
     * @param cat
     */
    public static void getNewsByid(Connection con, News n) {

        String sql = "SELECT n.id, n.titre, n.txt, c.value AS nameCat, c.id AS idCat, (select GROUP_CONCAT(value) FROM news_tag INNER JOIN tags as t on t.id = news_tag.tag_ID where news_tag.news_ID = n.id) FROM news AS n INNER JOIN categories AS c ON n.categorie_ID = c.id WHERE n.id=?";

        try {

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, n.getId());
            try {
                ResultSet rs = stmt.executeQuery();

                try {

                    while (rs.next()) {

                        n.setTitre(rs.getString("titre"));
                        n.setTxt(rs.getString("txt"));
                        n.getCategorie().setId(rs.getInt("idCat"));
                        n.getCategorie().setValue(rs.getString("nameCat"));

                    }

                } finally {
                    rs.close();
                }

            } finally {
                stmt.close();
            }

        } catch (SQLException e) {
            System.out.println("ex " + e);
        }

    }

    /**
     * affiche toute les news
     *
     * @param con
     * @return
     */
    public static List<News> getNews(Connection con) {

        String sql = "SELECT n.id, n.titre, n.txt, c.value AS nameCat, c.id AS idCat, (select GROUP_CONCAT(value) FROM news_tag INNER JOIN tags as t on t.id = news_tag.tag_ID where news_tag.news_ID = n.id) AS tags FROM news AS n INNER JOIN categories AS c ON n.categorie_ID = c.id";

        List<News> News = new ArrayList<>();

        try {

            PreparedStatement stmt = con.prepareStatement(sql);

            try {
                ResultSet rs = stmt.executeQuery();

                try {

                    while (rs.next()) {

                        News n = new News();
                        n.setId(rs.getInt("id"));
                        n.setTitre(rs.getString("titre"));
                        n.getCategorie().setValue(rs.getString("nameCat"));
                        //n.setNewsTagsString(rs.getString("tags"));

                        News.add(n); //ajout à l'arraylist

                    }

                } finally {
                    rs.close();
                }

            } finally {
                stmt.close();
            }

        } catch (SQLException ex) {

            System.out.println("e" + ex);

        }

        return News;

    }

    /**
     * affiche les news avec un id de tag commun
     *
     * @param con
     * @param t
     * @return
     */
    public static List<News> getNewsTag(Connection con, Tags t) {

        String sql = "SELECT * FROM news AS n INNER JOIN news_tag as nT on n.id = nT.news_ID WHERE nT.tag_ID=?";

        List<News> News = new ArrayList<>();

        try {

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, t.getId());

            try {
                ResultSet rs = stmt.executeQuery();

                try {

                    while (rs.next()) {

                        News n = new News();
                        n.setId(rs.getInt("id"));
                        n.setTitre(rs.getString("titre"));
                        //n.getCategorie().setValue(rs.getString("nameCat"));

                        News.add(n);  //ajout à l'arraylist

                    }

                } finally {
                    rs.close();
                }

            } finally {
                stmt.close();
            }

        } catch (SQLException ex) {

            System.out.println("e" + ex);

        }

        return News;

    }

}
