package com.bullish.assignment1v3.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract class AbstractItem {
    public String name;
    public Float price;
    public Float discount;
}
