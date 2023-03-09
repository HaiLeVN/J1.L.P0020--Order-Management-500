/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dto.I_Menu;
import dto.I_SubMenu;
import services.CustomerList;
import services.Menu;
import services.ProductList;
import services.Update;

/**
 *
 * @author Thanh Hai
 */
public class OrderManagement {
    public static void main(String[] args) {
        // TODO code application logic here
        I_Menu menu = new Menu();
        menu.addItem("1. List all Products");
        menu.addItem("2. List all Customers");
        menu.addItem("3. Search a Customer based on his/her ID");
        menu.addItem("4. Add a Customer");
        menu.addItem("5. Update a Customer");
        menu.addItem("6. Save Customers to the file");
        menu.addItem("7. List all Orders");
        menu.addItem("8. List all pending Orders");
        menu.addItem("9. Add an Order");
        menu.addItem("10. Update an Order");
        menu.addItem("11. Save Orders to file.");
        menu.addItem("12. Quit");
        ProductList pl = new ProductList();
        CustomerList cl = new CustomerList();
        int choice;
        boolean changed = false;
        boolean check = false;
        do {
            System.out.println(">>==x=== ORDER  MANAGEMENT ===x==<<");
            menu.showMenu();
            System.out.println(">>==x=== =o======o======o= ===x==<<");
            choice = menu.getChoice();
            switch(choice) {
                case 1:
                    pl.listAllProduct();
                    changed = false;
                    break;
                case 2:
                    cl.listAllCustomer();
                    changed = false;
                    break;
                case 3:
                    cl.search();
                    changed = false;
                    break;
                case 4:
                    
                    changed = false;
                    break;
                case 5:
                    
                    changed = false;
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    changed = false;
                    break;
                case 8:
                    
                    changed = true;
                    break;
                case 9:
                    
                    break;
                case 10:
                    
                    break;
                case 11:
                    
                    break;
                case 12:
                    break;
            }
        } while (choice >= 0 && choice <= 10 && !check);
        System.out.println("Good bye!");
    }
}
