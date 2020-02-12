import doryan.windowsnotificationapi.fr.Notification;
import entities.Reclamation;
import java.awt.TrayIcon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.AdminCRUD;
import services.ReclamationCRUD;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.net.MalformedURLException;

public class Menu {

    //Instance Variables
        Scanner sc = new Scanner(System.in);
        //Scanner sc1 = new Scanner(System.in);
       // String msg = sc.nextLine();
        Reclamation r = new Reclamation();
        //Reclamation r2 = new Reclamation(msg);
        ReclamationCRUD RC = new ReclamationCRUD();
        AdminCRUD ACR = new AdminCRUD();
      
        int mm ;
    boolean exit;

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.runMenu();
    }

    public void runMenu() {
        System.out.println("_________________Bonjour, est-ce que vous êtes un administrateur ou un internaute ?_________________\n");
        System.out.println("      -------------------- Tapez 1 : Internaute     --------------------");
        System.out.println("      -------------------- Tapez 2 : Administrateur --------------------");
        System.out.println("      -------------------- Tapez 3 : Exit           --------------------");
        
        int choix = sc.nextInt();
             
            if (choix==1)
            {    
            while (!exit) 
            {
            printMenu();
            int choice = getMenuChoice();
            performAction(choice);
            }
            }
            else if(choix==2)
            {
                while (!exit) 
            {
                printMenuadmin();
                int choice = getMenuChoiceadmin();
                performActionadmin(choice);
            }
            }
            else if(choix==3)
            {
                System.out.println("    ______________________   Merci d'avoir utiliser notre application.   ______________________");
                System.exit(0);
            } else if(choix > 3 || choix <0)
            {
            try {
                System.out.println("Veuillez choisir 1, 2 ou 3 Merci");
                Notification.sendNotification("Notification", "Invalide choix, veuillez réessayer",TrayIcon.MessageType.WARNING);
            } catch (AWTException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
               runMenu();
            }
    }
    private void printMenu() {
         System.out.println("    ____________________________________________________________________________");
            System.out.println("                  Bienvenu dans TaxiCo, vous êtes en tant qu'un internaute.\n                         "
                    +"Comment peut-on vous aider ? ");
            
            System.out.println("         ....... Veuillez sélectionner l'action à partir du menu ci-dessous .......");
            System.out.println("    ____________________________________________________________________________\n");
            
            System.out.println("       -------------------- Tapez 1 => Afficher les réclamations --------------------");
            System.out.println("       -------------------- Tapez 2 => Ajouter une réclamation   --------------------");
            System.out.println("       -------------------- Tapez 3 => Modifier une réclamation  --------------------");
            System.out.println("       -------------------- Tapez 4 => Supprimer une réclamation -------------------- "); 
            System.out.println("       -------------------- Tapez 0 => Quitter                   -------------------- "); 
    }
    private void printMenuadmin() {
         System.out.println("    ____________________________________________________________________________");
            System.out.println("                  Bienvenu dans TaxiCo, vous êtes en tant qu'un administrateur.\n                         "
                    + "Comment peut-on vous aider ? ");
            
            System.out.println("         ....... Veuillez sélectionner l'action à partir du menu ci-dessous .......");
            System.out.println("    ____________________________________________________________________________\n");
            System.out.println("       -------------------- Tapez 1 => Afficher les réclamations                   --------------------");
            System.out.println("       -------------------- Tapez 2 => Modifier l'état d'une réclamation           --------------------");
            System.out.println("       -------------------- Tapez 3 => Supprimer une réclamation                   --------------------");
            System.out.println("       -------------------- Tapez 4 => Trier les réclamations en ordre coissant    --------------------");
            System.out.println("       -------------------- Tapez 5 => Trier les réclamations en ordre décroissant --------------------");
            System.out.println("       -------------------- Tapez 6 => Créer un fichier PDF                        --------------------");
            System.out.println("       -------------------- Tapez 7 => Chercher une réclamation                    --------------------");
            System.out.println("       -------------------- Tapez 0 => Quitter                                     --------------------");
  
    }
    
    private int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Veuillez entrer votre choix : ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                try {
                    System.out.println("Séléction invalide. Veuillez réssayer.");
                    Notification.sendNotification("Notification", "Invalid selection. Numbers only please.",TrayIcon.MessageType.WARNING);
                } catch (AWTException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (choice < 0 || choice > 4) {
                try {
                    System.out.println("Choice outside of range. Please chose again.");
                    Notification.sendNotification("Notification", "Choice outside of range. Please chose again",TrayIcon.MessageType.ERROR);
                } catch (AWTException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (choice < 0 || choice > 4);
        return choice;
    }
    private int getMenuChoiceadmin() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
            }
            if (choice < 0 || choice > 7) {
                try {
                    System.out.println("Votre choix est hors interval. Veuillez ressayer");
                    Notification.sendNotification("Notification", "Votre choix est hors interval. Veuillez ressayer",TrayIcon.MessageType.ERROR);
                } catch (AWTException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (choice < 0 || choice > 7);
        return choice;
    }

    private void performAction(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Thank you for using our application.");
                System.exit(0);
                break;
            case 1: 
                System.out.println("          _____________________  Afficher réclamations...  _____________________       \n");
                RC.afficherReclamation();
            break;
            case 2:
                System.out.println("          _____________________  Veuillez saisir votre réclamation...   _____________________       \n");
                String ch = sc.nextLine();
                String msg = sc.nextLine();
                RC.ajouterReclamation(msg,r);
                System.out.println("          _____________________  Votre Réclamation a été envoyée avec succées...   _____________________       \n");
        {
            try {
                Notification.sendNotification("module evennement", "evennement ajouté ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;
            case 3:
                System.out.println("          _____________________  Modifier réclamation...   _____________________       \n");
                System.out.println("          _____________________  Veuillez saisir l'id de la réclamation ...   _____________________    \n");
                //int choice_id0 = sc.nextInt();
                int choice_id = sc.nextInt();
                System.out.println("          _____________________  Veuillez saisir votre message ...   _____________________    \n");
                String msg0 = sc.nextLine();
                String msg1 = sc.nextLine();
                RC.modifierReclamation(choice_id, msg1, r);
                System.out.println("\n          _____________________  Votre Réclamation a été modifiée avec succées...   _____________________       \n");
        {
            try {
                Notification.sendNotification("module evennement", "evennement ajouté ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case 4:
                System.out.println("          _____________________  Supprimer Réclamation...  _____________________       \n");
                System.out.println("          _____________________  Veuillez saisir l'id de la réclamation ...   _____________________    \n");
                choice_id = sc.nextInt();
                RC.supprimerReclamation(choice_id);
                System.out.println("\n        _____________________  Votre Réclamation a été supprimée avec succées...   _____________________       \n");
        {
            try {
                Notification.sendNotification("module evennement", "evennement ajouté ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            default:
                System.out.println("Unknown error has occured.");
        }
    }
      private void performActionadmin(int choice) {
        switch (choice) {
            case 0:
                System.out.println("          _____________________  Merci d'avoir utiliser notre application   _____________________       \n");
                System.exit(0);
                break;
            case 1: 
                System.out.println("          _____________________  Afficher réclamations...   _____________________       \n");
                ACR.afficherReclamationAdmin();
        {
            try {
                Notification.sendNotification("Notification", "L'affichage est réussi",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;
            case 2:
                System.out.println("          _____________________    Modifier l'état de la réclamation...       _____________________       \n");
                System.out.println("          _____________________  Veuillez saisir l'id de la réclamation ...   _____________________    \n");
                int choice_id = sc.nextInt();
                ACR.modifierEtatReclamation(true, choice_id);
                System.out.println("\n          _____________________  L'état de la réclamation a été modifiée avec succées...   _____________________       \n");
        {
            try {
                Notification.sendNotification("Notification", "L'état a été modifié avec succées",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case 3:
                System.out.println("          _____________________  Supprimer la réclamation..._____________________       \n");
                System.out.println("          _____________________  Veuillez saisir l'id de la réclamation ...   _____________________    \n");
                int id_choice = sc.nextInt();
                ACR.supprimerReclamationAdmin(id_choice);
                System.out.println("\n        _____________________  Votre Réclamation a été supprimée avec succées...   _____________________       \n");
        {
            try {
                Notification.sendNotification("Notification", "La réclamation a été supprimée avec succées",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case 4: 
                System.out.println("          _____________________  Trier les réclamation selon la date ..._____________________       \n");
                System.out.println("          ______________________________  En ordre croissant ________________________________       \n");
                ACR.trieParDateASC();
                System.out.println("          _____________________  Trie fait avec succé..._____________________       \n");
        {
            try {
                Notification.sendNotification("Notification", "Le trie est fait en ordre croissant",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case 5: 
                System.out.println("          _____________________  Trier les réclamation selon la date ..._____________________       \n");
                System.out.println("          ______________________________  En ordre décroissant ______________________________      \n");
                ACR.trieParDateDESC();
                System.out.println("          ______________________________  Trie fait avec succé.______________________________       \n");
        {
            try {
                Notification.sendNotification("Notification", "Le trie est fait en ordre décroissant",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case 6:
                System.out.println("          ____________________________  Générer un fichier PDF ...____________________________      \n");
                ACR.convertirPDF();
                System.out.println("          ___________________ Le fichier Reclamation.PDF est généré avec succé ___________________       \n");
        {
            try {
                Notification.sendNotification("Notification", "Le fichier Reclamation.PDF est généré avec succé",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case 7:
                System.out.println("          _____________________  Veuillez saisir l'id de la réclamation ...   _____________________    \n");
                      int id_affiche = sc.nextInt();
                      ACR.chercherRec(id_affiche);
                      System.out.println("   ______________________________  Vouz troverez les résultats des recherches en dessus.______________________________       \n");
        {
            try {
                Notification.sendNotification("Notification", "Recherche est terminé.",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;                                
            default:
                System.out.println("Unknown error has occured.");
        {
            try {
                Notification.sendNotification("Notification", "Unknown error has occured.",TrayIcon.MessageType.ERROR);
            } catch (AWTException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
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