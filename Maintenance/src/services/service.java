/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Garageentitie;
import entities.Serviceentitie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnexionBD;

/**
 *
 * @author walid
 */
public class service {
    Connection c = ConnexionBD.getInstance().getCnx();
    public void create_service(Serviceentitie s)
    {
        try {
            Statement st =c.createStatement();
            String req="INSERT INTO service VALUES("+s.getId_service()+",'"+s.getName()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void display_service ()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from service");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("Service [ id_service: " +rs.getInt(1) + " name : " + rs.getString(2) + "]");
            }   } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public void remove_service(Serviceentitie s)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from service where id_service=?");
            pt.setInt(1,s.getId_service());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void display_service (int id)
    {
        try {
            PreparedStatement pt = c.prepareStatement("select from service where id=?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("Service [ id_service: " +rs.getInt(1) + " name : " + rs.getString(2) + "]");
            }   } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
}
        public void display_service_name ()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println(" name : " + rs.getString(2) + "]");
            }   } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
}
       public ObservableList<Serviceentitie> displayAll() {
               ObservableList<Serviceentitie> list=FXCollections.observableArrayList();

        
        try {
            PreparedStatement pt = c.prepareStatement("select * from service ");
            ResultSet rs = pt.executeQuery();
            
     while(rs.next())
     {
         list.add(new Serviceentitie(rs.getInt(1),rs.getString(2)));
        
     }
     
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return list;
       
    }
       public void edit_rdvs(Serviceentitie s)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update service set name=? where id_service =?");
            pt.setString(1,s.getName());
            pt.setInt(2, s.getId_service());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
