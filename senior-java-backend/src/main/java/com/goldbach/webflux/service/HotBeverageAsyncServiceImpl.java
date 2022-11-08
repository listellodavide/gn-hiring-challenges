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

@Component
public class HotBeverageAsyncServiceImpl implements HotBeverageAsyncService {
	
	@Autowired
	private HotBeverageOrderRepository vehicleRepo;

	@Override
	public Mono<HotBeverageDto> createVehicleAsync(HotBeverageDto vehicleDto) {
		CompletableFuture<HotBeverageDto> future = CompletableFuture.supplyAsync(() -> {
			HotBeverageOrder hotBeverageOrderDomain = new HotBeverageOrder()
								.setModel(vehicleDto.getModel())
								.setMake(vehicleDto.getMake())
								.setColor(vehicleDto.getColor())
								.setVin(vehicleDto.getVin())
								.setYear(vehicleDto.getYear());
			hotBeverageOrderDomain = vehicleRepo.save(hotBeverageOrderDomain);
			vehicleDto.setId(hotBeverageOrderDomain.getId());
			return vehicleDto;
		});
		Mono<HotBeverageDto> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}
	
	@Override
	public Mono<HotBeverageDto> getVehicleAsync(int vehicleId) {
		CompletableFuture<HotBeverageDto> future = CompletableFuture.supplyAsync(() -> {
			
			Optional<HotBeverageOrder> vehicleOptional = vehicleRepo.findById(vehicleId);
			HotBeverageDto dto = null;
			if (vehicleOptional.isPresent()) {
				HotBeverageOrder v = vehicleOptional.get();
				dto = new HotBeverageDto().setColor(v.getColor())
						.setId(v.getId())
						.setMake(v.getMake())
						.setModel(v.getModel())
						.setVin(v.getVin())
						.setYear(v.getYear());
			}
			return dto;
		});
		Mono<HotBeverageDto> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}

	@Override
	public Flux<HotBeverageDto> getVehicleListAsync() {
		return Flux.create((emitter) -> {
			CompletableFuture<List<HotBeverageDto>> future = CompletableFuture.supplyAsync( () -> {
				List<HotBeverageOrder> hotBeverageOrderList = vehicleRepo.findAll(PageRequest.of(0, 1000)).getContent();
				List<HotBeverageDto> vehicleDtoList = hotBeverageOrderList.parallelStream().map( (hotBeverageOrder) -> {
					return new HotBeverageDto().setPrice(hotBeverageOrder.getPrice())
							.setId(hotBeverageOrder.getId())
							.setHotDrinkName(HotBeverageType.valueOf(hotBeverageOrder.getHotDrinkName()))
							.setWithMilk(hotBeverageOrder.getWithMilk())
							.setWithSugar(hotBeverageOrder.getWithSugar())
							.setDurationMsInfusion(hotBeverageOrder.getDurationMsInfusion());
				}).collect(Collectors.toList());
				return vehicleDtoList;
			});
			future.whenComplete( (vehicleDtoList, exception) -> {
				if (exception == null) {
					vehicleDtoList.forEach(emitter::next);
					emitter.complete();
				} else {
					emitter.complete();
				}
			});
		});
	}

	@Override
	public Mono<Void> updateVehicleAsync(HotBeverageDto hotBeverageDto) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			HotBeverageOrder hotBeverageOrderDomain = new HotBeverageOrder()
					.setHotDrinkName(HotBeverageType.valueOf(hotBeverageDto.getHotDrinkName()))
					.setWithMilk(hotBeverageDto.getWithMilk());
			hotBeverageOrderDomain = vehicleRepo.save(hotBeverageOrderDomain);
		});
		Mono<Void> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}

	@Override
	public Mono<Void> deleteVehicleAsync(int vehicleId) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			
			Optional<HotBeverageOrder> vehicleOptional = vehicleRepo.findById(vehicleId);
			if (vehicleOptional.isPresent()) {
				vehicleRepo.delete(vehicleOptional.get());;
			}
		});
		Mono<Void> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}
}
