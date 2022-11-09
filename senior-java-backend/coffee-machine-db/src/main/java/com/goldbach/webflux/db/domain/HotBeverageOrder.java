package com.goldbach.webflux.db.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Davide Listello
 */
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table (name = "hot_beverage_orders")
public class HotBeverageOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="hot_drink_name")
	private String hotDrinkName;
	
	@Column(name="with_milk")
	private Boolean withMilk;
	
	@Column(name="with_sugar")
	private Boolean withSugar;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="qty_gr_water")
	private Double qtyGramsHotWater;
	
	@Column(name="qty_gr_coffee_beans")
	private Double qtyGramsCoffeeBeans;

	@Column(name="qty_gr_milk")
	private Double qtyGramsHotMilk;

	@Column(name="pressure_bar_infusion")
	private Double pressureBarInfusion;

	@Column(name="duration_ms_infusion")
	private Double durationMsInfusion;

}
