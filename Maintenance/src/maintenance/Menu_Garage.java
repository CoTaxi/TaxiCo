/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maintenance;

import doryan.windowsnotificationapi.fr.Notification;
import entities.Garageentitie;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.garage;

/**
 *
 * @author walid
 */
public class Menu_Garage {
    boolean exit;
    public void runMenu() {
        printHeader();
        while (!exit) {
            printMenu();
            int choice = getMenuChoice();
            performAction(choice);
        }
    }

    private void printHeader() {
        System.out.println("+-----------------------------------+");
        System.out.println("|        Welcome                    |");
        System.out.println("|                                   |");
        System.out.println("+-----------------------------------+");
    }

    private void printMenu() {
        displayHeader("Please make a selection");
        System.out.println("1) Create");
        System.out.println("2) Edit");
        System.out.println("3) Display");
        System.out.println("4) Remove");
        System.out.println("5) Generate PDF");
        System.out.println("0) Exit");
    }

    private int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
            }
            if (choice < 0 || choice > 5) {
                System.out.println("Choice outside of range. Please chose again.");
            }
        } while (choice < 0 || choice > 5);
        return choice;
    }

    private void performAction(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Thank you for using our application.");
                System.exit(0);
                break;
            case 1:
                Scanner s = new Scanner(System.in);

                System.out.println("Please enter un id_service for garage");
                int s_idservice = s.nextInt();
                
                System.out.println("Please enter name for garage");
                String s_name2 = s.nextLine();
                String s_name = s.nextLine();
                
                System.out.println("Please enter Address for garage");
                String s_address = s.nextLine();
                Garageentitie g = new Garageentitie (s_idservice,s_name,s_address);
                garage srv=new garage();
                srv.create_garage(g);
        {
            try {
                Notification.sendNotification("module Maintenance", "Garage ajouté ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
            break;
            case 2:
                
                System.out.println("Please enter un id_garage");
                Scanner s2 = new Scanner(System.in);
                int s2_idgarage = s2.nextInt();
                Garageentitie g2 = new Garageentitie(s2_idgarage);
                garage  srv2= new garage();
                System.out.println("Please enter new name for garage");
                String s2_name2 = s2.nextLine();
                String s2_name = s2.nextLine();
                srv2.edit_garage(g2, s2_name);
        {
            try {
                Notification.sendNotification("Module Maintenace", "Garage has been edited ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                

                break;
            case 3:
                garage srv21=new garage();
                srv21.display_garage();

                break;
            case 4:
                System.out.println("Please enter un id_garage");
                Scanner s3 = new Scanner(System.in);
                int s3_idgarage = s3.nextInt();
                Garageentitie g3 = new Garageentitie(s3_idgarage);
                garage  srv3= new garage();
                srv3.remove_garage(g3);
        {
            try {
                Notification.sendNotification("Module Maintenance", "Garage has been removed ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case 5:
                garage srv4=new garage();
                srv4.pdf();
            default:
                System.out.println("Unknown error has occured.");
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
