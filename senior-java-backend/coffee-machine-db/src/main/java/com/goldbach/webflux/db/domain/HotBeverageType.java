package com.goldbach.webflux.db.domain;

/**
 * @author Davide Listello
 */
public enum HotBeverageType {

    HOT_WATER("HOT_WATER"),
    ESPRESSO("ESPRESSO"),
    MACCHIATO("MACCHIATO"),
    CAPPUCCINO("CAPPUCCINO"),
    MILKCOFFEE("MILKCOFFEE");

    private String hotDrinkName;

    private HotBeverageType(String hotDrinkName) {
        this.hotDrinkName = hotDrinkName;
    }

    @Override
    public String toString(){
        return hotDrinkName;
    }
}
