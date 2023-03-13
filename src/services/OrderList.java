/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dto.Customer;
import dto.Order;
import dto.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import utils.Utils;

/**
 *
 * @author Thanh Hai
 */
public class OrderList extends ArrayList<Order> {
    
    private final String FILEPATH = "src/info/orders.txt";
    
    public OrderList() {
        loadOrderFromFile();
    }
    
    public Order search(String id){
        id = id.trim();
        for (int i = 0; i < size(); i++) {
            if (this.get(i).getOrderID().equalsIgnoreCase(id)) {
                return this.get(i);
            }
        }
        return null;
    }
    
    public void listAllOrders() {
        //Load Orders.txt and add to temporary collections
        ArrayList<Order> temp = new ArrayList<>();
        try {
            File f = new File(FILEPATH);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                String id = info[0].trim();
                String cid = info[1].trim();
                String pid = info[2].trim();
                int quantity = Integer.parseInt(info[3].trim());
                String date = info[4].trim();
                boolean status = Boolean.parseBoolean(info[5].trim());
                temp.add(new Order(id, quantity, date, status, cid, pid));
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("[!] Cannot load orders.txt file");
        }
        //Access collections of Customer
        CustomerList cs = new CustomerList();
        ArrayList<Customer> customers = cs.getCustomer();
        
        //Check Customer collections is empty or not
        if(customers.isEmpty()) {
            System.out.println("There is no customers recently.");
            return;
        }
        
        //Create and initialize a collection 'sortedResults' to print it
        ArrayList<Order> sortedResults = new ArrayList<>();
        
        for (Order order : temp) {
            for (Customer c : customers) {
                if (order.getCustomerID().equalsIgnoreCase(c.getCustomerID())) {
                    sortedResults.add(order);
                }
            }
        }
        
        // sort the orders by customer name in ascending order
        Collections.sort(sortedResults, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                Customer c1 = cs.search(o1.getCustomerID());
                Customer c2 = cs.search(o2.getCustomerID());
                return c1.getCustomerName().compareToIgnoreCase(c2.getCustomerName());
            }
        });
        
        // print the sorted orders from the orders.txt
        for (Order order : sortedResults) {
            System.out.print(order);
        }
        Utils.exitMenu("Press 'Enter' key to return to main menu.");
    }

    public void printPendingOrder() {
        ArrayList<Order> pendingOrders = new ArrayList<>();
        try {
            File f = new File(FILEPATH);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            System.out.println("List of pending orders:");
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                String id = info[0].trim();
                String cid = info[1].trim();
                String pid = info[2].trim();
                int quantity = Integer.parseInt(info[3].trim());
                String date = info[4].trim();
                boolean status = Boolean.parseBoolean(info[5].trim());
                Order o = new Order(id, quantity, date, status, cid, pid);
                pendingOrders.add(o);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("[!] Error to load orders.txt.");
        }
        for (Order order : pendingOrders) {
            if (!order.isStatus()) {
                System.out.print(order);
            }
        }
        Utils.exitMenu("Press 'Enter' key to return to main menu.");
    }
    
    public void addOrder() {
        String id;
        System.out.println("[x] Input new information of Order.");
        do {
            id = Utils.getString(" Input order's ID (Dxxx): ", "[D|d]\\d{3}");
            if (search(id) != null) {
                System.err.println("[!] This ID is duplicated");
            }
        } while (search(id) != null);
        // Display the submenu for choosing a customer
        displayCustomerSubMenu();
        String customerID = selectCustomer();
        // Display the submenu for choosing a product
        displayProductSubMenu();
        String productID = selectProduct();
        int quantity = Utils.getInt(" Enter Quantity: ", 0);
        String date = Utils.inputDate(" Enter Order Date (MM/dd/yyyy): ");
        boolean status = Utils.getBoolean(" Enter status of this order (true/false): ");
        Order order = new Order(id, quantity, date, status, customerID, productID);
        this.add(order);
        
        System.out.println("[!] Order added successfully!");
        
        String confirm = Utils.getString("Do you want to create another order? (Y/N) ");
        if (confirm.equalsIgnoreCase("y")) {
            addOrder();
        }
    }
    
    public void writeFile() {
        try {
            File file = new File(FILEPATH);
            FileWriter writer = new FileWriter(file);
            for (Order order : this) {
                String line = order.toString();
                writer.write(line);
            }
            writer.close();
            System.out.println("[!] Order information has been saved to file successfully.");
        } catch (IOException e) {
            System.out.println("[!] Unable to write order information to file.");
        }
        Utils.exitMenu("Press 'Enter' key to return to main menu.");
    }

    public void loadOrderFromFile() {
        try {
            File f = new File(FILEPATH);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                String id = info[0].trim();
                String cid = info[1].trim();
                String pid = info[2].trim();
                int quantity = Integer.parseInt(info[3].trim());
                String date = info[4].trim();
                boolean status = Boolean.parseBoolean(info[5].trim());
                this.add(new Order(id, quantity, date, status, cid, pid));
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("[!] Error to load orders.txt");
        }
    }

    private void displayCustomerSubMenu() {
        ArrayList<Customer> customer = (new CustomerList()).getCustomer();
        
        int serialNumber = 0;
        for(Customer menu : customer) {
            serialNumber++;
            System.out.print(serialNumber + " - " + menu);
        }
    }

    private String selectCustomer() {
        int choice = 0;
        ArrayList<Customer> customer = (new CustomerList()).getCustomer();
        do {
            choice = Utils.getInt(" Choose index: ");
            if (choice > customer.size() || choice < 1) {
                System.out.println("[!] That customer does not exist... try again.");
            }
        } while (choice > customer.size() || choice < 1);
        int index = choice - 1;

        return customer.get(index).getCustomerID();
    }


    private void displayProductSubMenu() {
        ArrayList<Product> pro = (new ProductList()).getProduct();
        
        int serialNumber = 0;
        for(Product menu : pro) {
            serialNumber++;
            System.out.print(serialNumber + " - " + menu);
        }
    }

    private String selectProduct() {
        int choice = 0;
        ArrayList<Product> pro = (new ProductList()).getProduct();
        do {
            choice = Utils.getInt(" Choose index: ");
            if (choice < 1 || choice > pro.size()) {
                System.out.print("[!] That product does not exist... try again.");
            }
        } while (choice < 1 || choice > pro.size());
        int index = choice - 1;

        return pro.get(index).getProductID();
    }
    
    private void print() {
        for(Order o : this) {
            System.out.print(o);
        }
    }

    // Update Order's information
    public void updateOrder() {
        System.out.println("[!] Update information of an Order. ");
        print();
        String id = Utils.getString(" Enter the order ID to update (Dxxx): ", "[D|d]\\d{3}");
        //id, quantity,date, status, cusID, proID
        int quantity; String date, cusID, proID; boolean status;
        int oldQuantity; String oldDate, oldCusID, oldProID; boolean oldStatus;
        
        int index = this.indexOf(new Order(id.toUpperCase()));
        if(index == -1) {
            System.out.println(" The Order does not exist");
        } else {
            System.out.println("Note: If you do not input anything, the old data will keep.");
            oldQuantity = ((Order) this.get(index)).getOrderQuantity();
            quantity = Utils.updateInt(" Old quantity: " + oldQuantity + ", Enter new Quantity: ", oldQuantity);
            
            oldDate = ((Order) this.get(index)).getOrderDate();
            date = Utils.updateDate(" Old date: " + oldDate + ", Enter new date (MM/dd/yyyy): ", oldDate);
            
            oldCusID = ((Order) this.get(index)).getCustomerID();
            cusID = Utils.updateString(" Old Customer ID: " + oldCusID + ", Enter new Customer ID (Cxxx): ", oldDate, "(?i)C\\d{3}");
            
            oldProID = ((Order) this.get(index)).getProductID();
            proID = Utils.updateString(" Old Product ID: " + oldProID + ", Enter new Product ID (Pxxx): ", oldDate, "(?i)P\\d{3}");
            
            oldStatus = ((Order) this.get(index)).isStatus();
            status = Utils.updateBoolean(" Old Status: " + oldStatus + ", Enter new Status (true/false): ", oldStatus);
            
            ((Order) this.get(index)).setOrderQuantity(quantity);
            ((Order) this.get(index)).setOrderDate(date);
            ((Order) this.get(index)).setCustomerID(cusID);
            ((Order) this.get(index)).setProductID(proID);
            ((Order) this.get(index)).setStatus(status);
            
            System.out.println("[!] Updated Order information successfully.");
        }
        String confirm = Utils.getString("Do you want to update Order again? (Y/N) ");
        if (confirm.equalsIgnoreCase("y")) {
            updateOrder();
        }
        print();
    }

    public void deleteOrder() {
        System.out.println("[!] Delete an Order information.");
        print();
        String id = Utils.getString(" Enter Order ID to remove: ", "[D|d]\\d{3}");
        Order o = search(id);
        if(search(id) == null) {
            System.out.println("[!] Order does not exist.");
        } else {
            String confirm = Utils.getString(" This Order will be deleted, enter 'Y' to confirm deletion: ");
            if("Y".equalsIgnoreCase(confirm)) {
                this.remove(o);
                //Check this id is deleted or not
                if(search(id) == null) {
                    System.out.println("[!] This Order deleted successfully.");
                } else {
                    System.out.println("[!] Failed to delete.");
                }
            }
        }
        String confirm = Utils.getString("Do you want to remove another order? (Y/N) ");
        if (confirm.equalsIgnoreCase("y")) {
            deleteOrder();
        }
        print();
    }
}
