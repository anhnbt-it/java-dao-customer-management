/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.Product;
import service.IDao;
import service.ProductDao;

/**
 *
 * @author anhnbt
 */
public class ProductController {

    private final IDao<Product> productDao;

    public ProductController(Connection conn) {
        this.productDao = new ProductDao(conn);
    }

    public void listProducts() {
        System.out.println("********** LIST PRODUCTS **********");
        List<Product> products = productDao.findAll();
        if (products == null) {
            System.out.println("No items...");
        } else {
            products.forEach(product -> {
                System.out.println(product.toString());
            });
            System.out.println("Total " + products.size() + " results!");
        }
    }

    public void addProduct(Scanner scanner) throws InputMismatchException {
        System.out.println("********** ADD NEW PRODUCT **********");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Line ID: ");
        int productlineId = scanner.nextInt();
        System.out.print("Enter Quantity: ");
        int qty = scanner.nextInt();
        System.out.print("Enter Price [$]: ");
        double price = scanner.nextDouble();

        scanner.nextLine();

        System.out.print("Enter Description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter Status [0=Disable, 1=Enable]: ");
        short status = scanner.nextShort();

        scanner.nextLine();

        Product product = new Product(productlineId, name, qty, price, desc, status);
        productDao.save(product); // Created record successfully
    }

    public void editProduct(Scanner scanner) throws InputMismatchException {
        System.out.println("********** EDIT PRODUCT **********");
        System.out.print("Enter Product ID want to Edit: ");
        int id = scanner.nextInt();
        Product product = productDao.findById(id);
        if (product != null) {
            System.out.println(product.toString());
            System.out.println("  1. Edit Name\t\t\t4. Edit Price");
            System.out.println("  2. Edit Product line\t\t5. Edit Description");
            System.out.println("  3. Edit Quantity\t\t6. Edit Status");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    showEditName(scanner, product);
                    break;
                case 2:
                    showEditProductLine(scanner, product);
                    break;
                case 3:
                    showEditQty(scanner, product);
                    break;
                case 4:
                    showEditPrice(scanner, product);
                    break;
                case 5:
                    showEditDescription(scanner, product);
                    break;
                case 6:
                    showEditStatus(scanner, product);
                    break;
            }
        } else {
            error404();
        }
    }

    public void deleteProduct(Scanner scanner) throws InputMismatchException {
        System.out.print("Enter Product ID you want to delete: ");
        int id = scanner.nextInt();
        Product product = productDao.findById(id);
        if (product.getName() != null) {
            System.out.print("Are you sure want to delete: " + product.getName() + "? [Y/n]: ");
            scanner.nextLine();
            String confirm = scanner.nextLine();
            if (confirm.toLowerCase().equals("y")) {
                productDao.deleteById(id);
            }
        } else {
            error404();
        }
    }

    private void showEditName(Scanner scanner, Product product) throws InputMismatchException {
        System.out.println("********** EDIT PRODUCT NAME **********");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        product.setName(name);
        productDao.update(product);
    }

    private void showEditProductLine(Scanner scanner, Product product) throws InputMismatchException {
        System.out.println("********** EDIT PRODUCT LINE **********");
        System.out.print("Enter Product Line ID: ");
        int productLine = scanner.nextInt();
        product.setProductlineId(productLine);
        productDao.update(product);
    }

    private void showEditQty(Scanner scanner, Product product) throws InputMismatchException {
        System.out.println("********** EDIT PRODUCT QUANTITY **********");
        System.out.print("Enter Quantity: ");
        short qty = scanner.nextShort();
        product.setQty(qty);
        productDao.update(product);
    }

    private void showEditPrice(Scanner scanner, Product product) throws InputMismatchException {
        System.out.println("********** EDIT PRODUCT PRICE **********");
        System.out.print("Enter Price [?]: ");
        double price = scanner.nextDouble();
        product.setPrice(price);
        productDao.update(product);
    }

    private void showEditDescription(Scanner scanner, Product product) throws InputMismatchException {
        System.out.println("********** EDIT PRODUCT DESCRIPTION **********");
        System.out.print("Enter Description: ");
        String desc = scanner.nextLine();
        product.setDesc(desc);
        productDao.update(product);
    }

    private void showEditStatus(Scanner scanner, Product product) throws InputMismatchException {
        System.out.println("********** EDIT PRODUCT STATUS **********");
        System.out.print("Enter status [0=DISABLE, 1=ENABLE]: ");
        short status = scanner.nextShort();
        product.setStatus(status);
        productDao.update(product);
    }
    
    private void error404() {
        System.out.println("404: Product not found.");
    }

}
