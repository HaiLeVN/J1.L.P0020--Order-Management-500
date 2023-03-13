/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dto.Customer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import utils.Utils;

/**
 *
 * @author Thanh Hai
 */
public class CustomerList extends ArrayList<Customer> {
    
    private final String FILEPATH = "src/info/customers.txt";
    
    public CustomerList() {
        loadCustomerFromFile();
    }
    
    public ArrayList<Customer> getCustomer() {
        return this;
    }

    public Customer search(String id) {
        id = id.trim().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getCustomerID().equalsIgnoreCase(id)){
                return this.get(i);
            }
        }
        return null;
    }
    
    public void addCustomer(){
        String id, name, address, phone;
        System.out.println("[x] Input new information of Customer.");
        do {
            id = Utils.getString(" Input customer's ID (Cxxx): ", "[C|c]\\d{3}");
            if (search(id) != null) {
                System.err.println("[!] This ID is duplicated");
            }
        } while (search(id) != null);
        name = Utils.getString(" Input customer's name: ");
        address = Utils.getString(" Input customer's address: ");
        phone = Utils.getStringNumber(" Input phone number (10-12 nums): ", 10, 12);
        Customer cs = new Customer(id, name, address, phone);
        this.add(cs);
        
        String confirm = Utils.getString("[!] Do you want to add Customer again (Y/N)? ");
        if("Y".equalsIgnoreCase(confirm)) {
            addCustomer();
        } else {
            System.out.println("[!] Back to main menu");
        }
    }
    
    public void search(){
        String id = Utils.getString(" Input Customer ID (Cxxx) to search: ");
        Customer cs = search(id);
        if(search(id) != null)  
            System.out.println(cs);
        else {
            System.out.println(" This customer does not exist");
        }
        Utils.exitMenu("Press 'Enter' key to return to main menu.");
    }
    private void print() {
        for(Customer c : this) {
            System.out.print(c.toString());
        }
    }
    public void update() {
        System.out.println("[!] Update information of Customer");
        String id, name, address, phone;
        String oldName, oldAddress, oldPhone;

        // Print the customer list to user choose
        print();

        id = Utils.getString(" Input customer's ID (Cxxx) to update: ", "(?i)C\\d{3}");
        int index = this.indexOf(new Customer(id.toUpperCase()));
        if (index == -1) {
            System.err.println("Customer ID does not exist");
        } else {
            oldName = ((Customer) this.get(index)).getCustomerName();
            name = Utils.updateString(" Input new customer's name: ", oldName);

            oldAddress = ((Customer) this.get(index)).getCustomerAddress();
            address = Utils.updateString(" Input new customer's address: ", oldAddress);

            oldPhone = ((Customer) this.get(index)).getCustomerPhone();
            do {
                phone = Utils.updateString(" Input new customer's phone number: ", oldPhone);
                if (!phone.matches("[^\\d]|\\d{10,12}")) {
                    System.out.println("Phone number must be between 10 and 12 digits");
                }
            } while (!phone.matches("[^\\d]|\\d{10,12}"));

            ((Customer) this.get(index)).setCustomerName(name);
            ((Customer) this.get(index)).setCustomerAddress(address);
            ((Customer) this.get(index)).setCustomerPhone(phone);

            System.out.println("[!] Updated successfully!");
        }
        String confirm = Utils.getString("[!] Do you want to update Customer again (Y/N)? ");
        if("Y".equalsIgnoreCase(confirm)) {
            update();
        } else {
            System.out.println("[!] Back to main menu");
        }
    }

    public void writeFile() {
        try {
            File file = new File(FILEPATH);
            FileWriter writer = new FileWriter(file);
            for (Customer customer : this) {
                String line = customer.toString();
                writer.write(line);
            }
            writer.close();
            System.out.println("[!] Customer information has been saved to file successfully.");
        } catch (IOException e) {
            System.out.println("[!] Unable to write customer information to file.");
        }
    }

    public void loadCustomerFromFile(){
        try {
            File f = new File(FILEPATH);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String info[] = line.split(",");
                if (info.length >= 4) {
                    String id = info[0].trim();
                    String name = info[1].trim();
                    String address = info[2].trim();
                    String num = info[3].trim();
                    this.add(new Customer(id, name, address, num));
                }
            }
        } catch (IOException e) {
            System.out.println("[!] Cannot load the customers.txt file.");
        }
    }

    public void listAllCustomer() {
        try {
            File f = new File(FILEPATH);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String info[] = line.split(",");
                if (info.length >= 4) {
                    String id = info[0].trim();
                    String name = info[1].trim();
                    String address = info[2].trim();
                    String num = info[3].trim();
                    Customer cs = new Customer(id, name, address, num);
                    System.out.print(cs.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("[!] Error to read customers.txt file");
        }
        Utils.exitMenu("Press 'Enter' key to return to main menu.");
    }
}
