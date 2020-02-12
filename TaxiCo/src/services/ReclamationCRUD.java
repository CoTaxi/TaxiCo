/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.ConnexionBD;

/**
 *
 * @author Oussama_RMILI
 */
public class ReclamationCRUD {
    Connection c= ConnexionBD.getInstance().getCnx();
    
    //Methode pour ajouter une reclamation : 
    public void ajouterReclamation(String msg, Reclamation r){
        try {
           // dt = new java.util.Date();
            //sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String Req_Add="INSERT INTO `reclamation`(`id_reclamation`, `message`, `etat`,`date_rec`) VALUES (?,?,?,?)";
            PreparedStatement pt = c.prepareStatement(Req_Add);  
            pt.setInt(1, r.getId_reclamation());
            pt.setString(2, msg);
            pt.setBoolean(3, false);
            pt.setString(4, r.getDate_rec());
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    // Methode pour afficher les reclamations : 
    public void afficherReclamation(){
        try {
            PreparedStatement pt1 = c.prepareStatement("select * from reclamation");
            ResultSet rs = pt1.executeQuery();
            while (rs.next()) {
                System.out.println("Reclamations [ id Reclamation: " +rs.getInt(1) + " Votre message : " + rs.getString(2) + " Etat: " + rs.getString(3)+ " Date est le: " +rs.getString(4)+ "\t \n]");             
            }
            } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    // Methode pour supprimer Reclamation :
    public void supprimerReclamation(int id){
        try {
            PreparedStatement pt2 = c.prepareStatement("delete from reclamation where id_reclamation=?");
            pt2.setInt(1,id);
            pt2.executeUpdate();
            } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierReclamation(int id, String msg1, Reclamation r ){
        try {
            PreparedStatement pt3 = c.prepareStatement("update reclamation set message=?, date_rec=? where id_reclamation=?");
            pt3.setString(1, msg1);
            pt3.setString(2, r.getDate_rec());
            pt3.setInt(3, id);
            pt3.executeUpdate();
          } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
