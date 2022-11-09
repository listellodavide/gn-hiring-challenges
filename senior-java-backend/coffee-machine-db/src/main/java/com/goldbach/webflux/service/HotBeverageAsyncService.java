package com.goldbach.webflux.service;

import com.goldbach.webflux.dto.HotBeverageDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Davide Listello
 */
public interface HotBeverageAsyncService {

	Mono<HotBeverageDto> createHotBeverageAsync(HotBeverageDto hotBeverageDto);

	Mono<HotBeverageDto> getHotBeverageAsync(int hotBeverageId);
	
	Flux<HotBeverageDto> getHotBeverageListAsync();
	
	Mono<Void> updateHotBeverageAsync(HotBeverageDto hotBeverageDto);
	
	Mono<Void> deleteHotBeverageAsync(int hotBeverageId);
}
