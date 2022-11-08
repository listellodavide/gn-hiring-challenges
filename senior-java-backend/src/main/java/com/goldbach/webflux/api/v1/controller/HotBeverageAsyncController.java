package com.goldbach.webflux.api.v1.controller;

import com.goldbach.webflux.db.domain.HotBeverageType;
import com.goldbach.webflux.dto.HotBeverageBuilder;
import com.goldbach.webflux.dto.HotBeverageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.goldbach.webflux.api.v1.request.HotBeverageRequest;
import com.goldbach.webflux.service.HotBeverageAsyncService;

import io.swagger.annotations.Api;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/hot-beverage-async")
@Api(tags="HotBeverage-Async")
public class HotBeverageAsyncController {

	@Autowired
    private HotBeverageAsyncService hotBeverageAsyncService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<HotBeverageDto> createHotBeverageAsync(@RequestBody HotBeverageRequest req) {
        HotBeverageDto hotBeverageDto = new HotBeverageBuilder(HotBeverageType.valueOf(req.getHotDrinkName()))
                .withMilk(req.getWithMilk())
                .withSugar(req.getWithSugar())
                .build();

        return hotBeverageAsyncService.createVehicleAsync(hotBeverageDto);
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<HotBeverageDto> getHotBeverageByIdAsync(@PathVariable("id") int id) {
    	return hotBeverageAsyncService.getVehicleAsync(id);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<HotBeverageDto> getHotBeverageListAsync() {
    	return hotBeverageAsyncService.getVehicleListAsync();
    }
    
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> updateHotBeverage(@RequestBody HotBeverageRequest req) {
        HotBeverageDto hotBeverageDto = new HotBeverageBuilder(HotBeverageType.valueOf(req.getHotDrinkName()))
                .withMilk(req.getWithMilk())
                .withSugar(req.getWithSugar())
                .build();

        hotBeverageDto.setId(req.getId());
    	return hotBeverageAsyncService.updateVehicleAsync(hotBeverageDto);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> deleteHotBeverageById (@PathVariable("id") int id) {
    	return hotBeverageAsyncService.deleteVehicleAsync(id);
    }
    
}
