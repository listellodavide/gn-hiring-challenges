package com.goldbach.webflux.api.v1.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class HotBeverageRequest {

	private String hotDrinkName;
	private Boolean withMilk;
	private Boolean withSugar;
	private int id;
}
