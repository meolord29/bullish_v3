package com.bullish.assignment1v3.model.users;

public class Client extends AbstractUser implements ProductAddable, ProductRemovable{
    

    // Allows the client the add products into the basket
    public void addProduct(){

        throw new UnsupportedOperationException("Unimplemented method 'addProduct'");
    }; 

    // Allows the client the remove products into the basket
    public void removeProduct(){

        throw new UnsupportedOperationException("Unimplemented method 'removeProduct'");
    };

}
