package com.goldbach.webflux.service;

import com.goldbach.webflux.dto.HotBeverageDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HotBeverageAsyncService {

	Mono<HotBeverageDto> createVehicleAsync(HotBeverageDto vehicleDto);

	Mono<HotBeverageDto> getVehicleAsync(int vehicleId);
	
	Flux<HotBeverageDto> getVehicleListAsync();
	
	Mono<Void> updateVehicleAsync(HotBeverageDto vehicleDto);
	
	Mono<Void> deleteVehicleAsync(int vehicleId);
}
