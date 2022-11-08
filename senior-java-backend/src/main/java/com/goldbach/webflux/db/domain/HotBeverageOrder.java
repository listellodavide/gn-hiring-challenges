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

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table (name = "HotBeverageOrder")
public class HotBeverageOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="hot-drink-name")
	private String hotDrinkName;
	
	@Column(name="with-milk")
	private Boolean withMilk;
	
	@Column(name="with-sugar")
	private Boolean withSugar;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="qty-gr-water")
	private Double qtyGramsHotWater;
	
	@Column(name="qty-gr-coffee-beans")
	private Double qtyGramsCoffeeBeans;

	@Column(name="pressure-bar-infusion")
	private Double pressureBarInfusion;

	@Column(name="duration-ms-infusion")
	private Double durationMsInfusion;

}
