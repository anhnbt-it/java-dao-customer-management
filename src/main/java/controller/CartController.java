/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.util.InputMismatchException;
import service.CartDao;
import service.ProductDao;
import java.util.Scanner;
import model.Product;

/**
 *
 * @author anhnbt
 */
public class CartController {
    
    
    private final CartDao cartDao;
    private final ProductDao productDao;
     
    public CartController(Connection conn) {
        this.cartDao = new CartDao(conn);
        this.productDao = new ProductDao(conn);
    }

    public void listCarts() {
        cartDao.getListProducts();
    }

    public void addCart(Scanner scanner) throws InputMismatchException {
        System.out.print("Enter the product ID you want to add to the cart: ");
        int id = scanner.nextInt();
        Product product = productDao.findById(id);
        if (product == null) {
            System.out.println("Product not found.");
        } else {
            System.out.println(product.toString());
            System.out.print("Enter quantity: ");
            short quantity = scanner.nextShort();
            product.setQty(quantity);
            
            cartDao.addProduct(product);
            
            scanner.nextLine();
            
            System.out.print("Checkout? [Y/n]");
            String continueShoping = scanner.nextLine();
            
            if (continueShoping.toLowerCase().equals("y")) {
                cartDao.checkOut(product.getId(), product.getQty());
            } else {
                System.out.println("Continue shopping...");
            }
            // Press any key to quit
        }
    }
}
