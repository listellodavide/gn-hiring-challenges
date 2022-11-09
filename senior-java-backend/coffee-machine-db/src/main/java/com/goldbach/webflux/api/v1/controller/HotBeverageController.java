package com.goldbach.webflux.api.v1.controller;

import java.util.List;

import com.goldbach.webflux.db.domain.HotBeverageType;
import com.goldbach.webflux.dto.HotBeverageBuilder;
import com.goldbach.webflux.dto.HotBeverageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldbach.webflux.api.v1.request.HotBeverageRequest;
import com.goldbach.webflux.dto.ServiceResponseDto;
import com.goldbach.webflux.service.HotBeverageService;

import io.swagger.annotations.Api;

/**
 * @author Davide Listello
 */
@RestController
@RequestMapping("/v1/hot-beverage")
@Api(tags="HotBeverage")
public class HotBeverageController {

	private static final Logger logger = LoggerFactory.getLogger(HotBeverageController.class);

	@Autowired
    private HotBeverageService hotBeverageService;
    
    @PostMapping
    public ResponseEntity<Object> createhotBeverage(@RequestBody HotBeverageRequest req) {
        try {
            HotBeverageDto hotBeverageDto = new HotBeverageBuilder(HotBeverageType.valueOf(req.getHotDrinkName()))
                    .withMilk(req.getWithMilk())
                    .withSugar(req.getWithSugar())
                    .build();
            ServiceResponseDto<HotBeverageDto> response = hotBeverageService.createHotBeverage(hotBeverageDto);

            switch (response.getStatus()) {
                case SUCCESS:
                    return new ResponseEntity<>(hotBeverageDto, HttpStatus.CREATED);
                case BAD_REQUEST:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                default:
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            logger.error("exception in create hotBeverage api: ", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<HotBeverageDto> gethotBeverageById (@PathVariable("id") int id) {
    	ServiceResponseDto<HotBeverageDto> response = hotBeverageService.getHotBeverage(id);
    	switch (response.getStatus()) {
	        case SUCCESS:
	            return new ResponseEntity<HotBeverageDto>(response.getResponseObject(), HttpStatus.OK);
	        case NOT_FOUND:
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        default:
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @GetMapping
    public ResponseEntity<List<HotBeverageDto>> gethotBeverageList () {
    	ServiceResponseDto<List<HotBeverageDto>> response = hotBeverageService.getHotBeveragesList();
    	switch (response.getStatus()) {
	        case SUCCESS:
	            return new ResponseEntity<List<HotBeverageDto>>(response.getResponseObject(), HttpStatus.OK);
	        case NOT_FOUND:
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        default:
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @PutMapping
    public ResponseEntity<Object> updatehotBeverage(@RequestBody HotBeverageRequest req) {
        try {
            HotBeverageDto hotBeverageDto = new HotBeverageBuilder(HotBeverageType.valueOf(req.getHotDrinkName()))
                    .withMilk(req.getWithMilk())
                    .withSugar(req.getWithSugar())
                    .build();
            hotBeverageDto.setId(req.getId());
            ServiceResponseDto<HotBeverageDto> response = hotBeverageService.updateHotBeverage(hotBeverageDto);

            switch (response.getStatus()) {
                case SUCCESS:
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);
                case BAD_REQUEST:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                default:
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            logger.error("exception in update hotBeverage api: ", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HotBeverageDto> deletehotBeverageById (@PathVariable("id") int id) {
    	ServiceResponseDto<HotBeverageDto> response = hotBeverageService.deleteBeverages(id);
    	switch (response.getStatus()) {
	        case SUCCESS:
	            return new ResponseEntity<HotBeverageDto>(HttpStatus.ACCEPTED);
	        case NOT_FOUND:
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        default:
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

}
