/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import dao.AddToCart;
import dao.DBConnection;
import dao.ProductDao;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
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
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        Connection conn = DBConnection.getConnection();
        ProductDao productDao = new ProductDao(conn);
        AddToCart addToCart = new AddToCart(conn);
        int choice;
        do {            
            System.out.println("---- MENU ---- ");
            System.out.println("1. List Products");
            System.out.println("2. Add New Product");
            System.out.println("3. Edit Product");
            System.out.println("4. Remove Product");
            System.out.println("5. Shopping cart");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    listProducts(productDao);
                    break;
                case 2:
                    addNewProduct(scanner, productDao);
                    break;
                case 5:
                    shoppingCart(scanner, productDao, addToCart);
                case 6:
                    System.out.println("Bye...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("No choice!");
            }
        } while (true);
    }

    private static void listProducts(ProductDao productDao) {
        List<Product> products = productDao.getRecords(0, 0);
        System.out.println("-- List Products --");
        if (products == null) {
            System.out.println("No data...");
        } else {
            products.forEach(product -> {
                System.out.println(product.toString());
            });
        }
    }

    private static void addNewProduct(Scanner scanner, ProductDao productDao) {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.println("Enter Product line id: ");
            int productlineId = scanner.nextInt();
            System.out.println("Enter quantity: ");
            int qty = scanner.nextInt();
            System.out.println("Enter price: ");
            double price = scanner.nextDouble();

            scanner.nextLine();

            System.out.println("Enter description: ");
            String desc = scanner.nextLine();
            System.out.println("Enter status [0, 1]: ");
            short status = scanner.nextShort();
            scanner.nextLine();
            Product product = new Product(productlineId, name, qty, price, desc, status);
            if (productDao.add(product)) {
                System.out.println("Created record successfully.");
            } else {
                System.out.println("Insert fails.");
            }
        } catch (InputMismatchException ex) {
            ex.printStackTrace();
        }
    }

    private static void shoppingCart(Scanner scanner, ProductDao productDao, AddToCart addToCart) {
        listProducts(productDao);
        System.out.println("Enter the product ID you want to add to the cart: ");
        int id = scanner.nextInt();
        Product product = productDao.findById(id);
        if (product == null) {
            System.out.println("Product not found.");
        } else {
            System.out.println("Enter quantity: ");
            short quantity = scanner.nextShort();
            product.setQty(quantity);
            addToCart.addProduct(product);
            System.out.println("Press any key to quit...");
            short choose = scanner.nextShort();
            if (choose == -1) {
                addToCart.checkOut(product.getId(), product.getQty());
            }
        }
    }
    
}
