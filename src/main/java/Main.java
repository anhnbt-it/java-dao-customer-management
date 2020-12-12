/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.CartController;
import controller.ProductController;
import service.CartDao;
import service.DBConnection;
import service.ProductDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author anhnbt
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner;
        Connection conn;
        ProductController productController;
        CartController cartController;

        try {
            scanner = new Scanner(System.in);
            conn = DBConnection.getConnection();
            productController = new ProductController(conn);
            cartController = new CartController(conn);

            int choice;
            do {
                System.out.println("--- SHOPPING CART MENU ---");
                System.out.println("  1: List Products");
                System.out.println("  2: Add New Product");
                System.out.println("  3: Edit Product");
                System.out.println("  4: Remove Product");
                System.out.println("  5: Shopping cart");
                System.out.println("  6: Your list cart");
                System.out.println("  7: Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        productController.listProducts();
                        break;
                    case 2:
                        productController.addProduct(scanner);
                        break;
                    case 3:
                        productController.editProduct(scanner);
                        break;
                    case 4:
                        productController.deleteProduct(scanner);
                        break;
                    case 5:
                        cartController.addCart(scanner);
                        break;
                    case 6:
                        cartController.listCarts();
                        break;
                    case 7:
                        System.out.println("Bye...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("No choice!");
                }
            } while (true);
        } catch (InputMismatchException ex) {

            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
