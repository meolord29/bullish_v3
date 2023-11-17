package com.bullish.assignment1v3.model;

public class Admin extends AbstractUser implements ProductAddable, ProductRemovable{
    
    // Allows the admin the add new products into the system for Clients to interact with
    public void addProduct(){}; 

    // Allows the admin to delete products from the system for Clients to interact with
    public void removeProduct(){};

}
