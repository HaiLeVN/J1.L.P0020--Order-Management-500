/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dto.I_Menu;
import services.CustomerList;
import services.Menu;
import services.OrderList;
import services.ProductList;
import utils.Utils;

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
        OrderList ol = new OrderList();
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
                    cl.addCustomer();
                    changed = true;
                    break;
                case 5:
                    cl.update();
                    changed = true;
                    break;
                case 6:
                    cl.writeFile();
                    changed = false;
                    break;
                case 7:
                    ol.listAllOrders();
                    changed = false;
                    break;
                case 8:
                    ol.printPendingOrder();
                    changed = false;
                    break;
                case 9:
                    ol.addOrder();
                    changed = true;
                    break;
                case 10:
                    /* 
                        Viewing SubMenu Update, instead creating a new class Update, it should be 
                        in this main menu to handle easily.
                    */
                    int subChoice;
                    do {
                        System.out.println(" ===o=== || Update Order || ===o=== ");
                        System.out.println(" |  1 - Update Order information  | ");
                        System.out.println(" |  2 - Delete an Order           | ");
                        System.out.println(" ===o=== |================| ===o=== ");
                        subChoice = Utils.getInt(" Input your choice: ", 1, 2);
                        switch (subChoice) {
                            case 1:
                                ol.updateOrder();
                                break;
                            case 2:
                                ol.deleteOrder();
                                break;
                        }
                    } while (subChoice > 0 || subChoice <= 2);
                    System.out.println("[!] Returned back to main menu.");
                    break;
                case 11:
                    ol.writeFile();
                    break;
                case 12:
                    check = menu.confirmYesNo(" Do you want to exit the program (Y/N)? ");
                    break;
            }
        } while (choice >= 0 && choice <= 12 && !check);
        System.out.println("Good bye!");
    }
}
