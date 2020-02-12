/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Reclamation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.ConnexionBD;

/**
 *
 * @author Oussama_RMILI
 */
public class AdminCRUD {
    Connection c= ConnexionBD.getInstance().getCnx();
    
   // Methode pour lister tous les reclamations : 
    public void afficherReclamationAdmin(){
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
    public void supprimerReclamationAdmin(int id){
        try {
            PreparedStatement pt2 = c.prepareStatement("delete from reclamation where id_reclamation=?");
            pt2.setInt(1,id);
            pt2.executeUpdate();
               } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifierEtatReclamation(Boolean etatR, int id){
        try {
            PreparedStatement pt3 = c.prepareStatement("update reclamation set etat=? where id_reclamation=?");
            pt3.setBoolean(1, etatR);
            pt3.setInt(2, id);
            pt3.executeUpdate();
             } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void trieParDateASC(){
        try {
            PreparedStatement pt4 = c.prepareStatement("select * from reclamation order by date_rec asc");
            ResultSet rs = pt4.executeQuery();
            while (rs.next()) {
                System.out.println("Reclamations [ id Reclamation: " +rs.getInt(1) + " Votre message : " + rs.getString(2) + " Etat: " + rs.getString(3)+ " Date est le: " +rs.getString(4)+ "\t \n]");             
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void trieParDateDESC(){
        try {
            PreparedStatement pt4 = c.prepareStatement("select * from reclamation order by date_rec desc");
            ResultSet rs = pt4.executeQuery();
            while (rs.next()) {             
                System.out.println("Reclamations [ id Reclamation: " +rs.getInt(1) + " Votre message : " + rs.getString(2) + " Etat: " + rs.getString(3)+ " Date est le: " +rs.getString(4)+ "\t \n]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void convertirPDF(){
         
        try {
            String file_name ="C:\\Users\\dell\\Desktop\\Esprit 1er semestre 3A3\\PIDEV 3A3\\Reclamation.pdf";
            Document document = new Document();
            try {
                //file_name.setReadable(true,false);
                PdfWriter.getInstance(document, new FileOutputStream(file_name));
            } catch (DocumentException ex) {
                Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            document.open();
            PreparedStatement pt5;
            
                pt5 = c.prepareStatement("select * from reclamation");
                ResultSet rs;
                rs = pt5.executeQuery();
                while (rs.next()) { 
                Paragraph para=new Paragraph("Réclamation [ id_réclamation: " +rs.getInt(1) + " Message : " + rs.getString(2) + " Etat: " + rs.getBoolean(3)+" Date de réclamation: " + rs.getDate(4)+"]");
                //System.out.println("garage [ id_garage: " +rs.getInt(1) + " name : " + rs.getString(2) + " Address: " + rs.getString(3)+" id_service: " + rs.getInt(4)+"]");
                document.add(para);
                document.add(new Paragraph(" "));
            }
            document.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
            Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
             }
            
         
        
 
        }
    public void chercherRec(int id_affiche){
        try {
            PreparedStatement pt6 = c.prepareStatement("select *from reclamation where id_reclamation=?");
            pt6.setInt(1, id_affiche);
            ResultSet rs = pt6.executeQuery();
            while (rs.next()){
                System.out.println("Reclamations [ id Reclamation: " +rs.getInt(1) + " Votre message : " + rs.getString(2) + " Etat: " + rs.getString(3)+ " Date est le: " +rs.getString(4)+ "\t \n]");
                
            }   } catch (SQLException ex) {
            Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
