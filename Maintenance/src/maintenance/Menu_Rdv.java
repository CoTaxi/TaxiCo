/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maintenance;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import doryan.windowsnotificationapi.fr.Notification;
import entities.Rdventitie;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import services.Rdv;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author walid
 */
public class Menu_Rdv {
    boolean exit;
    //String str="01-02-2020";
    //SimpleDateFormat f = new SimpleDateFormat("MM-dd-yyyy");
      //          Date sdate = f.parse(str);
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
        System.out.println("5) Trier");
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
     
                System.out.println("Please enter un id_chauffeur");
                int s_idchauffeur = s.nextInt();
                
                System.out.println("Please enter id_garage ");
                int s_idgarage = s.nextInt();
                
                System.out.println("Please enter id_service ");
                int s_idservice = s.nextInt();
                
                System.out.println("Please enter date_Rdv");
                //System.out.println("Saisissez une date (AAAA-MM-JJ) :");

                String strr = s.nextLine();
                                String str = s.nextLine();

                if(!(str.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"))){
   
                      System.out.println("Erreur format");
                }
                //String s_date_rdv = s.nextDate();
                
                System.out.println("Please enter status");
                String s_status = s.nextLine();
                
                Rdventitie g = new Rdventitie(s_idchauffeur, s_idgarage, s_idservice,Date.valueOf(str), s_status);
                Rdv srv=new Rdv();
                srv.create_rdv(g);
        {
            try {
                Notification.sendNotification("Module Maintenace", "Rendez vous has been created ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Rdv.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Rdv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
            break;
            case 2:
                
                System.out.println("Please enter un id_rdv");
                Scanner s2 = new Scanner(System.in);
                int s2_idRdv = s2.nextInt();
                //System.out.println("please enter date for rdv");
                //String s2_date = s2.nextLine();
                Rdventitie r2 = new Rdventitie(s2_idRdv);
                Rdv  srv2= new Rdv();
                System.out.println("Please enter new Date");
                String s2_date2 = s2.nextLine();
                String s2_date = s2.nextLine();
                srv2.edit_rdv(r2, Date.valueOf(s2_date));
        {
            try {
                Notification.sendNotification("Module Maintenance", "Rendez vous has been edited ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Rdv.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Rdv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                

                break;
            case 3:
                Rdv srv21=new Rdv();
                srv21.display_rdv();
                

                break;
            case 4:
                System.out.println("Please enter un id_Rdv");
                Scanner s3 = new Scanner(System.in);
                int s3_idRdv = s3.nextInt();
                Rdventitie g3 = new Rdventitie(s3_idRdv);
                Rdv  srv3= new Rdv();
                srv3.remove_rdv(g3);
        {
            try {
                Notification.sendNotification("Module Maintenance", "Rendez vous has been removed",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu_Rdv.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu_Rdv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case 5:
                //System.out.println("trier");
                Rdv srv4 = new Rdv();
                srv4.get_trie();
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
