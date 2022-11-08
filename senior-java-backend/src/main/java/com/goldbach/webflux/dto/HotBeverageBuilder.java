package com.goldbach.webflux.dto;

import com.goldbach.webflux.db.domain.HotBeverageType;

public class HotBeverageBuilder
{
    private HotBeverageType hotDrinkName;
    private Boolean withMilk;
    private Boolean withSugar;
    private Double price;
    private Double qtyGramsHotWater;
    private Double qtyGramsCoffeeBeans;
    private Double qtyGramsHotMilk;
    private Double pressureBarInfusion;
    private Double durationMsInfusion;

    public HotBeverageBuilder(HotBeverageType hotDrinkName) {
        this.hotDrinkName = hotDrinkName;
        switch(hotDrinkName) {
            case HOT_WATER: {
                this.durationMsInfusion = 10d;
                this.pressureBarInfusion = 10d;
                this.price = 0.0;
                this.qtyGramsCoffeeBeans = 0d;
                this.qtyGramsHotWater = 600d;
            } break;
            case ESPRESSO: {
                this.durationMsInfusion = 200d;
                this.pressureBarInfusion = 250d;
                this.price = 3.50;
                this.qtyGramsCoffeeBeans = 300d;
                this.qtyGramsHotWater = 400d;

            } break;
            case MACCHIATO: {
                this.durationMsInfusion = 220d;
                this.pressureBarInfusion = 350d;
                this.price = 3.50;
                this.qtyGramsCoffeeBeans = 300d;
                this.qtyGramsHotWater = 800d;
                this.withMilk = true;
                this.qtyGramsHotMilk = 80d;
            } break;
            case CAPPUCCINO: {
                this.durationMsInfusion = 320d;
                this.pressureBarInfusion = 350d;
                this.price = 5.50;
                this.qtyGramsCoffeeBeans = 320d;
                this.qtyGramsHotWater = 300d;
                this.withMilk = true;
                this.qtyGramsHotMilk = 280d;
            }
        }
    }

    public HotBeverageBuilder withMilk(Boolean withMilk) {
        this.withMilk = withMilk;
        return this;
    }

    public HotBeverageBuilder withSugar(Boolean withSugar) {
        this.withSugar = withSugar;
        return this;
    }

    public HotBeverageDto build() {
        HotBeverageDto hotBeverageDto =  new HotBeverageDto(this.hotDrinkName, this.price, this.qtyGramsHotWater, this.qtyGramsHotMilk, this.qtyGramsCoffeeBeans, this.pressureBarInfusion, this.durationMsInfusion, this.withMilk, this.withSugar);
        return hotBeverageDto;
    }

}
