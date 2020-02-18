/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event_mahdi;

import com.event_mahdi.entities.event;
import com.event_mahdi.entities.fidelite;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import com.event_mahdi.services.fidelite_services;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.Pane;
import com.event_mahdi.services.event_services;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;


/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class FideliteController implements Initializable {

    @FXML
    private AnchorPane ffa500;
    @FXML
    private TextField txt_email;
    @FXML
    private Button btn_email;
    @FXML
    private Pane pnl_fidelite;
    @FXML
    private Pane pnl_event;
    @FXML
    private Button bnt_participer;
    @FXML
    private ComboBox<String> eve_nom;
    
    event_services ev =new event_services();
    List<String> arr= ev.affichagecombo();
    ObservableList<String> combo = FXCollections.observableArrayList(arr);
    @FXML
    private Button btn_quit;
    @FXML
    private Button btn_game;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        eve_nom.getItems().addAll(combo);
    }    

    private void initialize(ActionEvent event) {
        
        System.exit(0);
    }

    @FXML
    private void btn_email_clicked(ActionEvent event) {
        fidelite_services fs=new fidelite_services();
        String email=txt_email.getText();
        try {
            fs.sendMail(email);
        } catch (Exception ex) {
            Logger.getLogger(FideliteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_participer(ActionEvent event) {
        event_services sv = new event_services();
        String nom=eve_nom.getValue();
        sv.affecter(nom);
    }

    @FXML
    private void btn_quit(ActionEvent event) {
        event_services sv = new event_services();
        sv.quit();
    }

    @FXML
    private void btn_game(ActionEvent event) {
        Snake s=new Snake();
        s.show();
    }
    
}
