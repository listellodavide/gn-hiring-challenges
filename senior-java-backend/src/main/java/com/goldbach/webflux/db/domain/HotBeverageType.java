package com.goldbach.webflux.db.domain;

public enum HotBeverageType {

    HOT_WATER("hot-water"),
    ESPRESSO("espresso"),
    MACCHIATO("macchiato"),
    CAPPUCCINO("cappuccino"),
    MILKCOFFEE("milkcoffee");

    private String hotDrinkName;

    private HotBeverageType(String hotDrinkName) {
        this.hotDrinkName = hotDrinkName;
    }

    @Override
    public String toString(){
        return hotDrinkName;
    }
}
