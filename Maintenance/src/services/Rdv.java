/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Rdventitie;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnexionBD;

/**
 *
 * @author walid
 */
public class Rdv {
    Connection c = ConnexionBD.getInstance().getCnx();
    public void create_rdv(Rdventitie r){
        try {
            Statement st =c.createStatement();
            String req="INSERT INTO rdv VALUES("+r.getId_rdv()+",'"+r.getId_chauffeur()+"','"+r.getDate_rdv()+"','"+r.getId_garage()+"','"+r.getId_service()+"','"+r.getStatus()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void edit_rdv(Rdventitie r,Date date_rdv)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update rdv set date_rdv=? where id_rdv=?");
            pt.setDate(1, date_rdv);
            pt.setInt(2, r.getId_rdv());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void display_rdv()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from rdv");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("Rendez vous [ id_rdv: " +rs.getInt(1) + " id_chauffeur: " + rs.getInt(2) + " Date_Rdv: " + rs.getDate(3)+" id_garage: " + rs.getInt(4)+" id_service: " + rs.getInt(5)+" status: " + rs.getString(6)+"]");
            }   } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public void remove_rdv(Rdventitie r)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from rdv where id_rdv=?");
            pt.setInt(1,r.getId_rdv());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void get_trie()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from rdv ORDER BY date_rdv ASC");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("Rendez vous [ id_rdv: " +rs.getInt(1) + " id_chauffeur: " + rs.getInt(2) + " Date_Rdv: " + rs.getDate(3)+" id_garage: " + rs.getInt(4)+" id_service: " + rs.getInt(5)+" status: " + rs.getString(6)+"]");
            }   } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public void display_rdv(int id,int id2)
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from rdv where id_service=? and id_garage=? and status=?");
            pt.setInt(1,id);
            pt.setInt(2,id2);
            pt.setString(3, "disponible");
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("Rendez vous [ id_rdv: " +rs.getInt(1) + " Date_Rdv: " + rs.getDate(3)+"]");
            }   } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }

        
}
    public void edit_rdv(Date date)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update rdv set status=? where date_rdv=?");
            pt.setString(1, "nondisponible");
            pt.setDate(2, date);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void annule_rdv(String date)
     {
        try {
            PreparedStatement pt = c.prepareStatement("update rdv set status=? where date_rdv=?");
            pt.setString(1, "disponible");
            pt.setString(2, date);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ObservableList<Rdventitie> displayAll() {
               ObservableList<Rdventitie> list=FXCollections.observableArrayList();

        
        try {
            PreparedStatement pt = c.prepareStatement("select * from rdv ");
            ResultSet rs = pt.executeQuery();
            
     while(rs.next())
     {
         list.add(new Rdventitie(rs.getInt("id_rdv"),rs.getInt("id_chauffeur"),rs.getDate("date_rdv"),rs.getInt("id_garage"),rs.getInt("id_service"),rs.getString("status")));
        
     }
     
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return list;
       
    }
    
    
    public void edit_rdvs(Rdventitie r)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update rdv set status=? where id_rdv =?");
            pt.setString(1,r.getStatus());
            pt.setInt(2, r.getId_rdv());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
