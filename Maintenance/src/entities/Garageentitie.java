/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author walid
 */
public class Garageentitie {
    private int id_garage,id_service ;
    private String name,address;

    public Garageentitie(int id_garage, String name, String address, int id_service) {
        this.id_garage = id_garage;
        this.id_service = id_service;
        this.name = name;
        this.address = address;
    }

    public Garageentitie(String name, String address,int id_service) {
        this.id_service = id_service;
        this.name = name;
        this.address = address;
    }

    public Garageentitie(int id_garage) {
        this.id_garage = id_garage;
    }

    public int getId_garage() {
        return id_garage;
    }

    public int getId_service() {
        return id_service;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setId_garage(int id_garage) {
        this.id_garage = id_garage;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
