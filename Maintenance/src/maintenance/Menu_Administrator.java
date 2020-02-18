/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maintenance;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author walid
 */
public class Menu_Administrator {
    



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
            int choice = getMenuChoice();
            performAction(choice);
        }
    }

    private void printHeader() {
        System.out.println("+-----------------------------------+");
        System.out.println("|        Welcome Administrator      |");
        System.out.println("|                                   |");
        System.out.println("+-----------------------------------+");
    }

    private void printMenu() {
        displayHeader("Please make a selection");
        System.out.println("1) Garage");
        System.out.println("2) Service");
        System.out.println("3) Rendez vous");
        //System.out.println("4) List account balance");
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
                Menu_Garage menu = new Menu_Garage();
                menu.runMenu();

            break;
            case 2:
                Menu_Service m1 = new Menu_Service();
                m1.runMenu();

                break;
            case 3:
                Menu_Rdv m2 = new Menu_Rdv();
                m2.runMenu();

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

