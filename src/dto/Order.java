/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Thanh Hai
 */
public class Order {
    private String orderID;
    private int orderQuantity;
    private String orderDate;
    private boolean status;
    
    private String customerID;
    private String productID;

    public Order(String orderID, int orderQuantity, String orderDate, boolean status, String customerID, String productID) {
        this.orderID = orderID;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.status = status;
        this.customerID = customerID;
        this.productID = productID;
    }

    public Order(String id) {
        this.orderID = id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Order) {
            Order other = (Order) o;
            return this.orderID.equals(other.orderID);
        }
        return false;
    }

    public String getOrderID() {
        return orderID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public boolean isStatus() {
        return status;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return orderID.toUpperCase() + "," + customerID.toUpperCase() + "," + productID.toUpperCase() + "," + orderQuantity + "," + orderDate + "," + status + "\n";
    }
    
}
