/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_maintenance;

import doryan.windowsnotificationapi.fr.Notification;
import entities.Garageentitie;
import entities.Rdventitie;
import entities.Serviceentitie;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import maintenance.Menu_Garage;
import services.Rdv;
import services.garage;
import services.service;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class Interface_backController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //Configure table view
    //Configure Rendez vous
    @FXML private TableView<Rdventitie> tableView;
    @FXML private TableColumn<Rdventitie, Integer>id_rdvColumn;
    @FXML private TableColumn<Rdventitie, Integer>id_chauffeurColumn;
    @FXML private TableColumn<Rdventitie, Date>date_rdvColumn;
    @FXML private TableColumn<Rdventitie, Integer>id_garageColumn;
    @FXML private TableColumn<Rdventitie, Integer>id_serviceColumn;
    @FXML private TableColumn<Rdventitie, String>statusColumn;
    
    //
    @FXML private TextField id_chauffeurTextField;
    @FXML private DatePicker date_rdvTextField;
    @FXML private TextField id_garageTextField;
    @FXML private TextField id_servicTextField;
    @FXML private TextField statusTextField;
    
    //Configure Garage
    @FXML private TableView<Garageentitie> tableViewGarage;
    @FXML private TableColumn<Garageentitie, Integer>id_garageColumnG;
    @FXML private TableColumn<Garageentitie, Integer>id_serviceColumnG;
    @FXML private TableColumn<Garageentitie, String>nameColumnG;
    @FXML private TableColumn<Garageentitie, String>addressColumn;
    


    
    //
    @FXML private TextField nameTextFieldG;
    @FXML private TextField addressTextField;
    @FXML private TextField id_serviceTextFieldG;
    
    //Configure Service
    @FXML private TableView<Serviceentitie> tableViewService;
    @FXML private TableColumn<Serviceentitie, Integer>id_serviceColumnS;
    @FXML private TableColumn<Serviceentitie, String>nameColumnS;
    
    //
    @FXML private TextField nameTextFieldS;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//Configure Column For Table Rendez vous
        
        
        id_rdvColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, Integer>("id_rdv"));
        id_chauffeurColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, Integer>("id_chauffeur"));
        date_rdvColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, Date>("date_rdv"));
        id_garageColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, Integer>("id_garage"));
        id_serviceColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, Integer>("id_service"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, String>("status"));
        Rdv r = new Rdv();
        tableView.setItems(r.displayAll());
        tableView.setEditable(true);
        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
//Configure Garage
        
        
        id_garageColumnG.setCellValueFactory(new PropertyValueFactory<Garageentitie, Integer>("id_garage"));
        id_serviceColumnG.setCellValueFactory(new PropertyValueFactory<Garageentitie, Integer>("id_service"));
        nameColumnG.setCellValueFactory(new PropertyValueFactory<Garageentitie, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Garageentitie, String>("address"));
        garage g = new garage();
        tableViewGarage.setItems(g.displayAll());
        tableViewGarage.setEditable(true);
        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewGarage.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
//Configure Service
        
        
        id_serviceColumnS.setCellValueFactory(new PropertyValueFactory<Serviceentitie, Integer>("id_service"));
        nameColumnS.setCellValueFactory(new PropertyValueFactory<Serviceentitie, String>("name"));
        service s = new service();
        tableViewService.setItems(s.displayAll());
        tableViewService.setEditable(true);
        nameColumnS.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewService.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }  
    
    //Update status For Table Rendez vous
    
    
    public void changeStatusCellEvent (CellEditEvent edditedCell)
    {
        Rdventitie statusSelected = tableView.getSelectionModel().getSelectedItem();
        statusSelected.setStatus(edditedCell.getNewValue().toString());
        Rdv r = new Rdv();
        r.edit_rdvs(statusSelected);
        {
            try {
                Notification.sendNotification("Module Maintenance", "Status has been updated ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
//Update Name And Address For Table Garage
    
    
    public void changeNameCellEventG (CellEditEvent edditedCell)
    {
        Garageentitie nameSelected =  tableViewGarage.getSelectionModel().getSelectedItem();
        nameSelected.setName(edditedCell.getNewValue().toString());
        garage g = new garage();
        g.updateName(nameSelected);
        {
            try {
                Notification.sendNotification("Module Maintenance", "Name has been updated ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void changeAddressCellEvent (CellEditEvent edditedCell)
    {
        Garageentitie nameSelected =  tableViewGarage.getSelectionModel().getSelectedItem();
        nameSelected.setName(edditedCell.getNewValue().toString());
        garage g = new garage();
        g.UpdateAddress(nameSelected);
        {
            try {
                Notification.sendNotification("Module Maintenance", "Address has been updated ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
//Update Name For Table Service
    
    
    public void changeNameCellEventS (CellEditEvent edditedCell)
    {
        Serviceentitie nameSelected = tableViewService.getSelectionModel().getSelectedItem();
        nameSelected.setName(edditedCell.getNewValue().toString());
        service s = new service();
        s.edit_rdvs(nameSelected);
        {
            try {
                Notification.sendNotification("Module Maintenance", "Name has been updated ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
//Configure Button For Table Rendez vous
    
    
    public void addButton()
    {
        Rdventitie newRdv = new Rdventitie( Integer.parseInt(id_chauffeurTextField.getText()),
                                           Date.valueOf(date_rdvTextField.getValue()),
                                           Integer.parseInt(id_garageTextField.getText()),
                                           Integer.parseInt(id_servicTextField.getText()),
                                           statusTextField.getText());
        
        Rdv r = new Rdv();
        r.create_rdv(newRdv);
        //tableView.getItems().add(newRdv);
        tableView.getItems().clear();
        tableView.setItems(r.displayAll());
        
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
    
    public void RemoveButton()
    {
      
        ObservableList <Rdventitie> selectedRows,allPeople;
        allPeople = tableView.getItems();
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        for (Rdventitie rdv : selectedRows)
        {
            
            Rdventitie selected = tableView.getSelectionModel().getSelectedItem();
            int id = selected.getId_rdv();
            Rdventitie re = new Rdventitie(id);
            Rdv r = new Rdv();
            r.remove_rdv(re);
            allPeople.remove(rdv);
        }
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
    
//Configure Button For Table Garage
    
    
    public void addButtonGarage()
    {
        Garageentitie newgarage = new Garageentitie( nameTextFieldG.getText(),
                                           addressTextField.getText(),
                                           Integer.parseInt(id_serviceTextFieldG.getText()));
        
        garage g = new garage();
        g.create_garage(newgarage);
        //tableViewGarage.getItems().add(newgarage);
        tableViewGarage.getItems().clear();
        tableViewGarage.setItems(g.displayAll());
        {
            try {
                Notification.sendNotification("Module Maintenance", "Garage has been aafected ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void RemoveButtonGarage()
    {
      
        ObservableList <Garageentitie> selectedRows,allPeople;
        allPeople = tableViewGarage.getItems();
        selectedRows = tableViewGarage.getSelectionModel().getSelectedItems();
        for (Garageentitie garage : selectedRows)
        {
            Garageentitie selected = tableViewGarage.getSelectionModel().getSelectedItem();
            int id = selected.getId_garage();
            Garageentitie gr = new Garageentitie(id);
            garage g = new garage();
            g.remove_garage(gr);
            allPeople.remove(garage);
        }
        {
            try {
                Notification.sendNotification("Module Maintenance", "Garage has been Removed ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
//Configure Button For Service
    
    
    public void addButtonService()
    {
        Serviceentitie newservice = new Serviceentitie(nameTextFieldS.getText());
                                           
                                           
        
        service s = new service();
        s.create_service(newservice);
        //tableViewService.getItems().add(newservice);
        tableViewService.getItems().clear();
        tableViewService.setItems(s.displayAll());
        {
            try {
                Notification.sendNotification("Module Maintenance", "Service has been aafected ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void RemoveButtonService()
    {
      
        ObservableList <Serviceentitie> selectedRows,allPeople;
        allPeople = tableViewService.getItems();
        selectedRows = tableViewService.getSelectionModel().getSelectedItems();
        for (Serviceentitie service : selectedRows)
        {
            Serviceentitie selected = tableViewService.getSelectionModel().getSelectedItem();
            int id = selected.getId_service();
            Serviceentitie se = new Serviceentitie(id);
            service s = new service();
            s.remove_service(se);
            allPeople.remove(service);
        }
        {
            try {
                Notification.sendNotification("Module Maintenance", "Service has been Removed",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
