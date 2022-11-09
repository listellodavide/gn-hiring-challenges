package com.goldbach.webflux.service;

import java.util.List;

import com.goldbach.webflux.dto.HotBeverageDto;
import com.goldbach.webflux.dto.ServiceResponseDto;

/**
 * @author Davide Listello
 */
public interface HotBeverageService {

	ServiceResponseDto<HotBeverageDto> createHotBeverage(HotBeverageDto hotBeverageDto);
	
	ServiceResponseDto<HotBeverageDto> updateHotBeverage(HotBeverageDto hotBeverageDto);
	
	ServiceResponseDto<HotBeverageDto> getHotBeverage(int hotBeverageId);
	
	ServiceResponseDto<List<HotBeverageDto>> getHotBeveragesList();
	
	ServiceResponseDto<HotBeverageDto> deleteBeverages(int hotBeverageId);
}
