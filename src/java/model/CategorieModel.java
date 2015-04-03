/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Categorie;
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
public class CategorieModel {
    
    /**
     * SELECT
     * @param con
     * @param cat 
     */
     public static void getCategorieByid(Connection con, Categorie cat, String table){
         
         String sql = "SELECT * FROM "+table+" where id=?";
         
         try {
             
             PreparedStatement stmt = con.prepareStatement(sql);
             
             stmt.setInt(1, cat.getId());
             //System.out.println("categorie selectionner " + cat.getId());
             try {
                 ResultSet rs = stmt.executeQuery();
                 
                 try {
                     
                     while (rs.next()) {
                         
                         cat.setValue(rs.getString("value"));
                         
                     }
                     
                 } finally{rs.close();}
                 
             }finally{stmt.close();}
             
         } catch (SQLException e) {
             System.out.println("ex " + e);
         }
     
     }
    
   
    
    /**
     * SELECT fetch
     * @param con
     * @return 
     */
    public static List<Categorie> getCategories(Connection con){

	String sql = "SELECT * FROM categories";

	List<Categorie> categories = new ArrayList<>();

	try {

		PreparedStatement stmt = con.prepareStatement(sql);

		try{
			ResultSet rs = stmt.executeQuery();

			try{

				while (rs.next()){

					Categorie cat = new Categorie();                                        
					cat.setId(rs.getInt("id"));
					cat.setValue(rs.getString("value"));

					categories.add(cat); //ajout à l'arraylist

				}

			} finally{rs.close();}

		} finally{stmt.close();}

	} catch(SQLException ex){
            
            System.out.println("e" + ex);

	}
        
        return categories;

    }
    
}
