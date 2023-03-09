/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dto.Product;
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
public class ProductList extends ArrayList<Product> {
    
    private final String FILEPATH = "src/info/products.txt";
    public ProductList() {
        loadProductFomFile();
    }
    
    public Product search(String id){
        loadProductFomFile();
        id = id.trim();
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getProductID().equalsIgnoreCase(id)){
                return this.get(i);
            }
        }
        return null;
    }
    
    public void loadProductFomFile(){
        try {
            File f = new File(FILEPATH);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String info[] = line.split("[,]");
                String id = info[0].trim();
                String name= info[1].trim();
                String unit = info[2].trim();
                String origin = info[3].trim();
                double price = Double.parseDouble(info[4].trim());
                this.add(new Product(id, name, unit, origin, price));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listAllProduct() {
        ArrayList<Product> printList = new ArrayList<>();
        try {
            File f = new File(FILEPATH);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String info[] = line.split("[,]");
                String id = info[0].trim();
                String name= info[1].trim();
                String unit = info[2].trim();
                String origin = info[3].trim();
                double price = Double.parseDouble(info[4].trim());
                printList.add(new Product(id, name, unit, origin, price));
            }
            for(Product p : printList) {
                System.out.print(p.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Utils.getString("Press 'Enter' key to return to main menu.");
    }
}
