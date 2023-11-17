package com.bullish.assignment1v3.model.users;

public class Admin extends AbstractUser implements ProductAddable, ProductRemovable{
    
    // Allows the admin the add new products into the system for Clients to interact with
    @Override
    public void addProduct(){

        throw new UnsupportedOperationException("Unimplemented method 'addProduct'");
    }; 

    // Allows the admin to delete products from the system for Clients to interact with
    @Override
    public void removeProduct(){

        throw new UnsupportedOperationException("Unimplemented method 'removeProduct'");
    };

}
