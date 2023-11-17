package com.bullish.assignment1v3.model.store;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Basket extends AbstractBasket implements Discountable, BasketUpdatable{

    

    @Override
    public Float buyOneGetFiftyPercentOffNextOne() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buyOneGetFiftyPercentOffNextOne'");
    }

    @Override
    public void basketUpdate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'basketUpdate'");
    }
    
    

}
