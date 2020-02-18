/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maintenance;

import doryan.windowsnotificationapi.fr.Notification;
import entities.Serviceentitie;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.service;

/**
 *
 * @author walid
 */
public class Menu_Service {
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
        System.out.println("2) Display");
        System.out.println("3) Remove");
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
            if (choice < 0 || choice > 3) {
                System.out.println("Choice outside of range. Please chose again.");
            }
        } while (choice < 0 || choice > 3);
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
                //System.out.println("Please enter un id_Service for Service");
                //int s_idservice = s.nextInt();
                
                System.out.println("Please enter name for Service");
               
                String s_name = s.nextLine();
               
                Serviceentitie g = new Serviceentitie (s_name);
                service srv=new service();
                srv.create_service(g);
        {
            try {
                Notification.sendNotification("Module Maintenance", "Service created ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Service.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Service.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
            break;
            case 2:
                service srv21=new service();
                srv21.display_service();

                break;
            case 3:
                System.out.println("Please enter un id_service");
                Scanner s3 = new Scanner(System.in);
                int s3_idservice = s3.nextInt();
                Serviceentitie g3 = new Serviceentitie(s3_idservice);
                service srv3= new service();
                srv3.remove_service(g3);
        {
            try {
                Notification.sendNotification("Module Maintenance", "Service has been removed",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Service.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Service.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
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
