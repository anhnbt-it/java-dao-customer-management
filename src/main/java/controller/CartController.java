/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.util.Enumeration;
import java.util.Hashtable;
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

    public void shoppingCart() {
        System.out.println("***************************************************");
        System.out.println("\t\t Shopping Cart ");
        System.out.println("***************************************************");
        Hashtable<Integer, Product> hashtable = cartDao.listProducts();
        if (hashtable.isEmpty()) {
            System.out.println("Your Cart is empty.");
        } else {
            Enumeration<Integer> enu = hashtable.keys();
            double total = 0;
            while (enu.hasMoreElements()) {
                int key = enu.nextElement();
                Product product = hashtable.get(key);
                total += product.getPrice() * product.getQty();
                System.out.println(product.toString());
            }
            System.out.println("Cart subtotal (" + hashtable.size() + " items): $" + total);
        }
    }

    public void shopNow(Scanner scanner) throws InputMismatchException {
        System.out.println("Adding Items to Your Shopping Cart");
        System.out.print("Enter the product ID you want to add to the cart: ");
        int id = scanner.nextInt();
        Product product = productDao.findById(id);
        if (product == null) {
            System.out.println("404: Product not found.");
        } else {
            System.out.println(product.toString());
            if (product.getQty() <= 0) {
                System.out.println("Temporarily out of stock.");
            } else {
                System.out.print("Enter Quantity: ");
                short quantity = scanner.nextShort();
                if (quantity > product.getQty()) {
                    System.out.println("Quantity exceeds quantity available in stock.");
                } else {
                    int oldQty = product.getQty();
                    product.setQty(quantity);
                    cartDao.addItem(product);
                    scanner.nextLine();
                    System.out.print("Proceed to checkout? [Y/n]: ");
                    String continueShoping = scanner.nextLine();
                    if (continueShoping.toLowerCase().equals("y")) {
                        cartDao.checkOut(product, oldQty);
                    }
                    // Press any key to quit
                }
            }
        }
    }
}
