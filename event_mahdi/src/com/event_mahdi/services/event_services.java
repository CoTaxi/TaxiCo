/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.event_mahdi.services;

/**
 *
 * @author mahdi
 */
import com.event_mahdi.utils.connexion;
import com.event_mahdi.entities.event;
import doryan.windowsnotificationapi.fr.Notification;
import event_mahdi.FXMLDocumentController;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
//import com.module_event.interfaces.eventServices;
/**
 *
 * @author mahdi
 */
public class event_services {
    Connection conn = connexion.getInstance().getCnx();
    Statement stmt;
    event e;

    public void create(String nom,Date date,String duree) {
        try {
            String req = "INSERT INTO evennement (nom_event,date_event,duree_event) VALUES ('"+nom+"','"+date+"','"+duree+"')";

            PreparedStatement st = conn.prepareStatement(req);
            //st.setString(1, e.getNom());
            //st.setObject(2, e.getDate());
            //st.setObject(3, e.getDuree());
            st.executeUpdate();
            try {
                Notification.sendNotification("module evennement", "evennement ajouté ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
     public void update(String nomp,String nom,Date Date_debut,String duree) {
        try {
            String req = "UPDATE evennement set nom_event='"+nom+"', date_event ='"+Date_debut+"' , duree_event ='"+duree+"'  where nom_event ='"+nomp+"'";
            PreparedStatement st = conn.prepareStatement(req);

            

            st.executeUpdate();
            System.out.println("event updated ");
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void delete(String nom) {
        try {
            String req = "DELETE FROM `evennement` WHERE nom_event = ? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1,nom);
            //  st.setString(2, e.getNomAnnonce());

            st.executeUpdate();
        } catch (SQLException ex) {

            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficherev(event e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

public void afficher_event()
    {
        try {
            PreparedStatement pt = conn.prepareStatement("select * from evennement");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("evennement [ id: " +rs.getInt(1) + "  nom : " + rs.getString(2) + "  date_debut: " + rs.getString(3)+"  duree : "+rs.getInt(4)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void afficher_event_client()
    {
        try {
            PreparedStatement pt = conn.prepareStatement("select * from evennement");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("evennement [ id : " + rs.getInt(1) +"  nom : " + rs.getString(2) + "  date_debut: " + rs.getString(3)+"  duree : "+rs.getInt(4)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public void supprimer_event (String nom)
    {
        
        try{
            PreparedStatement pt = conn.prepareStatement("delete from evennement where nom_event=?");
            pt.setString(1,nom);
            pt.executeUpdate();
            } catch (SQLException ex) {

            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
}
public void modifie_event(int duree,Timestamp date_debut,String nom)
    {
        try {
            PreparedStatement pt = conn.prepareStatement("update evennement set  duree_event= ?, date_event= ? where nom_event= ? ");
            
            pt.setInt(1,duree);
            pt.setTimestamp(2,date_debut);
            pt.setString(3,nom);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

public void tri(int min, int max){
       
       
    /*    try {
            PreparedStatement pt;
            pt = conn.prepareStatement("select * from evennement where duree_event<='"+min+"'AND duree_event>='"+max+"'");
            //  pt.setInt(1,duree);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("evennement [ nom : " + rs.getString(2) + "  date_debut: " + rs.getString(3)+"  duree : "+rs.getInt(4)+"]");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }*/
try {
            PreparedStatement pt = conn.prepareStatement("select * from evennement where duree_event>='"+min+"'AND duree_event<='"+max+"'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("evennement [ nom : " + rs.getString(2) + "  date_debut: " + rs.getString(3)+"  duree : "+rs.getInt(4)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void affecter(String nom)
{
        try {
            String req = "update client set  nom_event=? where id_client=1 ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1,nom);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public void quit()
{
        try {
            String req = "update client set  nom_event=? where id_client=1 ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1,"");
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public List<String> affichagecombo()  {
               List<String> arr=new ArrayList<>();
        try {
            
            
            
            PreparedStatement pt = conn.prepareStatement("select nom_event from evennement");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String nom=rs.getString("nom_event");
                
                arr.add(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

}
