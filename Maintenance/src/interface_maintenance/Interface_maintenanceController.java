/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_maintenance;

import doryan.windowsnotificationapi.fr.Notification;
import entities.Rdventitie;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import maintenance.Menu_Garage;
import services.Rdv;
import services.service;
import utils.ConnexionBD;
import entities.Rdventitie;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import interface_maintenance.Interface_main;
import java.sql.Date;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class Interface_maintenanceController implements Initializable {
    @FXML private Label choiceBoxLabel ;
    @FXML private ComboBox comboBox_listRdv;
    @FXML private ComboBox comboBox_garage ;
    @FXML private ComboBox comboBox_rdv ;
    @FXML private ComboBox comboBox_service;
    Connection c = ConnexionBD.getInstance().getCnx();
    @FXML
    private Button mm;
    
    @FXML
    public void choiceBoxButtonPushed()
    {
        choiceBoxLabel.setText(comboBox_service.getValue().toString());
         Rdv r=new Rdv();
         r.edit_rdv(Date.valueOf(comboBox_rdv.getValue().toString()));
         comboBox_listRdv.getItems().clear();
         listRdvReserved();
         comboBox_rdv.getItems().clear();
         SELECTEDcomboBoxGarage();
         
         {
            try {
                Notification.sendNotification("Module Maintenance", "Rendez vous has been aafected ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @FXML
  public void SELECTEDcomboBoxService()
    {
         try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM garage where id_service = ( select id_service from service where name =?)");
            pt.setString(1,comboBox_service.getValue().toString());
            ResultSet rs = pt.executeQuery();
            comboBox_garage.getItems().clear();
             while (rs.next()) {            
                comboBox_garage.getItems().add(rs.getString(2));
            
        } }catch (SQLException ex) {
            Logger.getLogger(Interface_maintenanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void SELECTEDcomboBoxGarage()
    {
         try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM rdv where id_service = ( select id_service from service where name =?) and id_garage= ( select id_garage from garage where name =?) and status=?");
            pt.setString(1,comboBox_service.getValue().toString());
            pt.setString(2,comboBox_garage.getValue().toString());
            pt.setString(3,"disponible");
            ResultSet rs = pt.executeQuery();
            comboBox_rdv.getItems().clear();
             while (rs.next()) {            
                comboBox_rdv.getItems().add(rs.getDate(3));
            
        } }catch (SQLException ex) {
            Logger.getLogger(Interface_maintenanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void cancelRdv()
    {
            Rdv r = new Rdv();
            r.annule_rdv(comboBox_listRdv.getValue().toString());
            comboBox_listRdv.getItems().clear();
            listRdvReserved();
            comboBox_rdv.getItems().clear();
            SELECTEDcomboBoxGarage();
            {
            try {
                Notification.sendNotification("Module Maintenance", "Rendez vous has been canceled ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void listRdvReserved(){
        try {
            PreparedStatement pt2 = c.prepareStatement("select * from rdv where status=? ");
            pt2.setString(1,"nondisponible");
            ResultSet rs = pt2.executeQuery();
            while (rs.next()) {            
                comboBox_listRdv.getItems().add(rs.getDate(3));
            } }catch (SQLException ex) {
                    Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    /**
     * Initializes the controller class.
     */
  public void initialize(URL url, ResourceBundle rb) {
      
            choiceBoxLabel.setText("");
            //choiceBox.getItems().add("salut");
            try {
                PreparedStatement pt = c.prepareStatement("select * from service");
                ResultSet rs = pt.executeQuery();
                while (rs.next()) {
                    comboBox_service.getItems().add(rs.getString(2));
                }   } catch (SQLException ex) {
                    Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
                }
            // choiceBox_service.setValue(choiceBox_service.getValue());
            
            //comboBox_listRdv();
            listRdvReserved();
            
       
    }

              
        
       /* try {
            PreparedStatement pt = c.prepareStatement("select * from service");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                choiceBox_rdv.getItems().add(rs.getString(2));
            }   } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        // TODO

    @FXML
    private void mm(ActionEvent event) {
           try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Interface_back.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            //Interface_main.;
    } catch(Exception e) {
       e.printStackTrace();
      }
 }
       
    }
    

    

