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
    
    public Customer search(String id) {
        id = id.trim().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getCustomerID().equalsIgnoreCase(id)){
                return this.get(i);
            }
        }
        return null;
    }
    
    public void add(){
        String id = Utils.getString(" ");
    }
    
    public void search(){
        String id = Utils.getString(" Input Customer ID (Cxxx) to search: ");
        Customer cs = search(id);
        if(search(id) != null)  
            System.out.println(cs);
        else {
            System.out.println("“This customer does not exist");
        }
    }
    
    public void update() {

    }
    public void writeFile() {
        
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
        ArrayList<Customer> printList = new ArrayList<>();
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
                    printList.add(new Customer(id, name, address, num));
                }
            }
            for (Customer c : printList) {
                System.out.print(c.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Utils.getString("Press 'Enter' key to return to main menu.");
    }
}
