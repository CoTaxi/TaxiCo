/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maintenance;

import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.Rdv;
import services.garage;
import services.service;

/**
 *
 * @author walid
 */
public class Menu_User {
    



    //Instance Variables
   //Bank bank = new Bank();
    boolean exit;

   /* public static void main(String) {
        Menu menu = new Menu();
        menu.runMenu();
    }*/

    public void runMenu() {
        printHeader();
        while (!exit) {
            printMenu();
          
        }
    }

    private void printHeader() {
        System.out.println("+-----------------------------------+");
        System.out.println("|         Welcome Chauffeur         |");
        System.out.println("|                                   |");
        System.out.println("+-----------------------------------+");
    }

    private void printMenu() {
        displayHeader("Please make a selection");
        service s2 =new service();
        s2.display_service();
       
        displayHeader("Please make a selection enter id_service");
        Scanner s = new Scanner(System.in);
        int sc = s.nextInt();
        garage g =new garage();
        g.display_garage(sc);
        
        displayHeader("Please make a selection enter id_garage");
        
        int sc3 = s.nextInt();
        Rdv r=new Rdv();
        r.display_rdv(sc, sc3);
        displayHeader("Please make a selection enter id_Rdv");
        int sc4 = s.nextInt();
        //r.edit_rdv(sc4);
        
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
   
    
    private void displayHeader(String message){
        System.out.println();
        int width = message.length() + 6;
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for(int i = 0; i < width; ++i){
            sb.append("-");
        }
        sb.append("+");
        System.out.println(sb.toString());
        System.out.println("|   " + message + "   |");
        System.out.println(sb.toString());
    }

   
}

