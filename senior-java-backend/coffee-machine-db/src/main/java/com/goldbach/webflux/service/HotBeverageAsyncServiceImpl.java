package com.goldbach.webflux.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.goldbach.webflux.db.domain.HotBeverageType;
import com.goldbach.webflux.dto.HotBeverageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.goldbach.webflux.db.domain.HotBeverageOrder;
import com.goldbach.webflux.db.repository.HotBeverageOrderRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Davide Listello
 */
@Component
public class HotBeverageAsyncServiceImpl implements HotBeverageAsyncService {
	
	@Autowired
	private HotBeverageOrderRepository hotBeverageOrderRepository;

	@Override
	public Mono<HotBeverageDto> createHotBeverageAsync(HotBeverageDto hotBeverageDto) {
		CompletableFuture<HotBeverageDto> future = CompletableFuture.supplyAsync(() -> {
			HotBeverageOrder hotBeverageOrderDomain = new HotBeverageOrder()
					.setHotDrinkName(hotBeverageDto.getHotDrinkName().toString())
					.setWithSugar(hotBeverageDto.getWithSugar())
					.setPrice(hotBeverageDto.getPrice())
					.setQtyGramsHotWater(hotBeverageDto.getQtyGramsHotWater())
					.setQtyGramsCoffeeBeans(hotBeverageDto.getQtyGramsCoffeeBeans())
					.setDurationMsInfusion(hotBeverageDto.getDurationMsInfusion())
					.setPressureBarInfusion(hotBeverageDto.getPressureBarInfusion())
					.setWithMilk(hotBeverageDto.getWithMilk());
			hotBeverageOrderDomain = hotBeverageOrderRepository.save(hotBeverageOrderDomain);
			hotBeverageDto.setId(hotBeverageOrderDomain.getId());
			return hotBeverageDto;
		});
		Mono<HotBeverageDto> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}
	
	@Override
	public Mono<HotBeverageDto> getHotBeverageAsync(int hotBeverageId) {
		CompletableFuture<HotBeverageDto> future = CompletableFuture.supplyAsync(() -> {
			
			Optional<HotBeverageOrder> hotBeverageOptional = hotBeverageOrderRepository.findById(hotBeverageId);
			HotBeverageDto dto = null;
			if (hotBeverageOptional.isPresent()) {
				HotBeverageOrder hotBeverageOrder = hotBeverageOptional.get();
				dto = new HotBeverageDto()
						.setHotDrinkName(HotBeverageType.valueOf(hotBeverageOrder.getHotDrinkName()))
						.setWithSugar(hotBeverageOrder.getWithSugar())
						.setPrice(hotBeverageOrder.getPrice())
						.setQtyGramsHotWater(hotBeverageOrder.getQtyGramsHotWater())
						.setQtyGramsCoffeeBeans(hotBeverageOrder.getQtyGramsCoffeeBeans())
						.setDurationMsInfusion(hotBeverageOrder.getDurationMsInfusion())
						.setPressureBarInfusion(hotBeverageOrder.getPressureBarInfusion())
						.setWithMilk(hotBeverageOrder.getWithMilk());
			}
			return dto;
		});
		Mono<HotBeverageDto> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}

	@Override
	public Flux<HotBeverageDto> getHotBeverageListAsync() {
		return Flux.create((emitter) -> {
			CompletableFuture<List<HotBeverageDto>> future = CompletableFuture.supplyAsync( () -> {
				List<HotBeverageOrder> hotBeverageOrderList = hotBeverageOrderRepository.findAll(PageRequest.of(0, 1000)).getContent();
				List<HotBeverageDto> hotBeverageDtoList = hotBeverageOrderList.parallelStream().map( (hotBeverageOrder) -> {
					return new HotBeverageDto().setPrice(hotBeverageOrder.getPrice())
							.setId(hotBeverageOrder.getId())
							.setHotDrinkName(HotBeverageType.valueOf(hotBeverageOrder.getHotDrinkName()))
							.setWithMilk(hotBeverageOrder.getWithMilk())
							.setWithSugar(hotBeverageOrder.getWithSugar())
							.setDurationMsInfusion(hotBeverageOrder.getDurationMsInfusion());
				}).collect(Collectors.toList());
				return hotBeverageDtoList;
			});
			future.whenComplete( (hotBeverageDtoList, exception) -> {
				if (exception == null) {
					hotBeverageDtoList.forEach(emitter::next);
					emitter.complete();
				} else {
					emitter.complete();
				}
			});
		});
	}

	@Override
	public Mono<Void> updateHotBeverageAsync(HotBeverageDto hotBeverageDto) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			HotBeverageOrder hotBeverageOrderDomain = new HotBeverageOrder()
					.setHotDrinkName(hotBeverageDto.getHotDrinkName().toString())
					.setWithSugar(hotBeverageDto.getWithSugar())
					.setPrice(hotBeverageDto.getPrice())
					.setQtyGramsHotWater(hotBeverageDto.getQtyGramsHotWater())
					.setQtyGramsCoffeeBeans(hotBeverageDto.getQtyGramsCoffeeBeans())
					.setDurationMsInfusion(hotBeverageDto.getDurationMsInfusion())
					.setPressureBarInfusion(hotBeverageDto.getPressureBarInfusion())
					.setWithMilk(hotBeverageDto.getWithMilk());
			hotBeverageOrderDomain = hotBeverageOrderRepository.save(hotBeverageOrderDomain);
		});
		Mono<Void> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}

	@Override
	public Mono<Void> deleteHotBeverageAsync(int hotBeverageId) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			
			Optional<HotBeverageOrder> hotBeverageOptional = hotBeverageOrderRepository.findById(hotBeverageId);
			if (hotBeverageOptional.isPresent()) {
				hotBeverageOrderRepository.delete(hotBeverageOptional.get());;
			}
		});
		Mono<Void> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}
}
