/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event_mahdi;

import com.event_mahdi.services.event_services;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import com.event_mahdi.entities.event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.control.cell.PropertyValueFactory;
import com.event_mahdi.utils.connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.event_mahdi.entities.fidelite;
import javafx.scene.control.ComboBox;
import com.event_mahdi.entities.event;
import java.util.ArrayList;
import java.util.List;
import com.event_mahdi.services.event_services;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import javafx.scene.control.Label;
import com.event_mahdi.services.fidelite_services;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Pane pnl_stat;
    @FXML
    private Pane pnl_fidelite;
    @FXML
    private Pane pnl_event;
    @FXML
    private TextField txt_nom;
    @FXML
    private DatePicker txt_date;
    @FXML
    private TextField txt_duree;
    @FXML
    private Button btn_add;
    @FXML
    private TableView<event> table;
    @FXML
    private TableColumn<event, String> col_nom;
    @FXML
    private TableColumn<event, String> col_date;
    @FXML
    private TableColumn<event, String> col_duree;
    @FXML
    private JFXButton btn_event;
    @FXML
    private JFXButton btn_fidelite;
    @FXML
    private JFXButton btn_stat;
    event_services ev =new event_services();
    List<String> arr= ev.affichagecombo();
    
    /**
     * Initializes the controller class.
     */
    
    ObservableList<event> eve =FXCollections.observableArrayList();
    ObservableList<fidelite> fid =FXCollections.observableArrayList();
    ObservableList<String> combo = FXCollections.observableArrayList(arr);
    
    @FXML
    private TableView<fidelite> tab_fidelite;
    @FXML
    private TableColumn<fidelite,String> code_promo;
    @FXML
    private TableColumn<fidelite,String> pt_fidelite;
    @FXML
    private ComboBox<String> combo_modif;
    @FXML
    private Button event_delete;
    @FXML
    private Button event_modifier;
    @FXML
    private JFXButton btn_modif;
    @FXML
    private Label nbr_inv;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fidelite_services sr=new fidelite_services();
        String nbr;
        
        
            nbr = String.valueOf(sr.afficher_nbr_email());
            nbr_inv.setText(nbr);
       
       
       update_event();
       update_fidelite();
       combo_modif();
    }
   
   
    @FXML
    private void handleButtonAction(ActionEvent event) {
         if(event.getSource()==btn_event)
       {pnl_event.toFront();}
       else if(event.getSource()==btn_fidelite)
       {pnl_fidelite.toFront();}
       else if(event.getSource()==btn_stat)
       {pnl_stat.toFront();}
       
       else if(event.getSource()==btn_add){
       String nom=txt_nom.getText();
       LocalDate date=txt_date.getValue();
       String duree=txt_duree.getText();
       java.util.Date date1 = java.sql.Date.valueOf(date);
       event_services srv=new event_services();
       srv.create(nom, date1, duree);
       update_event();
       }

    }
    public void update_event()
    {
     try {
            
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
            Connection conn = connexion.getInstance().getCnx();
            PreparedStatement pt = conn.prepareStatement("select * from evennement");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                eve.add(new event(
                        rs.getString("nom_event"),
                        rs.getString("date_event"),
                        rs.getString("duree_event")
                ));
            }
            table.setItems(eve);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update_fidelite()
    {
        fidelite fidelite=new fidelite();
     try {
            
            code_promo.setCellValueFactory(new PropertyValueFactory<>("code"));
            pt_fidelite.setCellValueFactory(new PropertyValueFactory<>("points"));
            Connection conn = connexion.getInstance().getCnx();
            PreparedStatement pt = conn.prepareStatement("select * from fidelite");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                fid.add(new fidelite(
                        rs.getString("code_promo"),
                        rs.getString("pt_fidelite")
                ));
            }
            
            tab_fidelite.setItems(fid);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void combo_modif() {
        combo_modif.getItems().clear();
        combo_modif.getItems().addAll(combo);
    }

    @FXML
    private void event_delete(ActionEvent event) {
        event_services sr=new event_services();
        String nom=combo_modif.getValue();
        sr.supprimer_event(nom);
        combo_modif();
        update_event();
    }

    @FXML
    private void event_modifier(ActionEvent event) {
        try {
            String nomp=combo_modif.getValue().toString();
            
            Connection conn = connexion.getInstance().getCnx();
            PreparedStatement pt = conn.prepareStatement("select * from evennement where nom_event=?");
            pt.setString(1,nomp);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) { 
                String nom=rs.getString("nom_event");
                Date date=rs.getDate("date_event");
                String duree=rs.getString("duree_event");
                String pattern = "yyyy-MM-dd";
                DateFormat df = new SimpleDateFormat(pattern);
                String dateAsString = df.format(date);
                System.out.println("date: "+dateAsString);
                txt_nom.setText(nom);
                txt_duree.setText(duree);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateAsString, formatter);
                txt_date.setValue(localDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   /* public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}*/

    @FXML
    private void modif_event(ActionEvent event){
        String nomp=combo_modif.getValue().toString();
        String nom=txt_nom.getText();
       LocalDate date=txt_date.getValue();
       String duree=txt_duree.getText();
       java.util.Date date1 = java.sql.Date.valueOf(date);
       event_services s=new event_services();
       s.update(nomp,nom,date1,duree);
    }
}
