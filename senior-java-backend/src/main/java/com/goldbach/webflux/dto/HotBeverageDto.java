package com.goldbach.webflux.dto;

import com.goldbach.webflux.db.domain.HotBeverageType;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class HotBeverageDto {

	private HotBeverageType hotDrinkName;
	private Boolean withMilk;
	private Boolean withSugar;
	private Double price;
	private Double qtyGramsHotWater;
	private Double qtyGramsHotMilk;
	private Double qtyGramsCoffeeBeans;
	private Double pressureBarInfusion;
	private Double durationMsInfusion;
	private int id;

	public HotBeverageDto(HotBeverageType hotDrinkName, Double price, Double qtyGramsHotWater, Double qtyGramsHotMilk, Double qtyGramsCoffeeBeans, Double pressureBarInfusion, Double durationMsInfusion, Boolean withMilk, Boolean withSugar) {
		this.hotDrinkName = hotDrinkName;
		this.price = price;
		this.qtyGramsHotWater = qtyGramsHotWater;
		this.qtyGramsHotMilk = qtyGramsHotMilk;
		this.qtyGramsCoffeeBeans = qtyGramsCoffeeBeans;
		this.pressureBarInfusion = pressureBarInfusion;
		this.durationMsInfusion = durationMsInfusion;
		this.withMilk = withMilk;
		this.withSugar = withSugar;
	}
}
