/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author anhnbt
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {            
            System.out.println("---- MENU ---- ");
            System.out.println("1. Insert Customer");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            System.out.println(choice);
            switch (choice) {
                case 1:
                    showInsertCustomer(scanner);
                    break;
                case 5:
                    System.out.println("Bye...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("No choice!");
            }
        } while (true);
    }

    private static void showInsertCustomer(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.println(name);
    }
    
}
