/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.event_mahdi.utils;

/**
 *
 * @author mahdi
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mahdi
 */
public class connexion {
   private static String url="jdbc:mysql://localhost:3306/unplagged" ; //jdbc est un framework // 3306:port d'ecoute
    private static String usr="root";
    private static String pwd="";
    private static Connection cnx ;
    private static connexion mycon;
    
    public static Connection getCnx(){
        return cnx;
    }
    
    private connexion(){
        try{
        cnx=DriverManager.getConnection(url,usr,pwd); // creer une instance
          
    }
    catch (SQLException ex){
        Logger.getLogger(connexion.class.getName()).log(Level.SEVERE,null,ex);
    }
    }
        public static connexion getInstance(){
            if(mycon==null)
                mycon=new connexion() ;
            return mycon ;
        }
}