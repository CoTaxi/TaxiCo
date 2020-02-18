/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.event_mahdi.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mahdi
 */
public class event {
    private String nom;
    private String date;
    private String duree;

    public event(String nom, String date, String duree) {
        this.nom = nom;
        this.date = date;
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }

    public String getDuree() {
        return duree;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public event(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final event other = (event) obj;

        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
}
