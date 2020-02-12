/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Oussama_RMILI
 */
public class Reclamation {
    private int id_reclamation ; 
    private String message ; 
    private Boolean etat  ; 
    //private String date_rec ;
   java.util.Date dt = new java.util.Date();
   java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   private String date_rec = sdf.format(dt);

    public Reclamation() {
    }


    public Reclamation(String message) {
        
        this.message = message;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getEtat() {
        return etat;
    }
    
    public String getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(String date_rec) {
        this.date_rec = date_rec;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", message=" + message + ", etat=" + etat + '}';
    }
    
    
    
}
