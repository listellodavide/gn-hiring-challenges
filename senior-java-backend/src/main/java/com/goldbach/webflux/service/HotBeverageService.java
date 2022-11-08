package com.goldbach.webflux.service;

import java.util.List;

import com.goldbach.webflux.dto.HotBeverageDto;
import com.goldbach.webflux.dto.ServiceResponseDto;

public interface HotBeverageService {

	ServiceResponseDto<HotBeverageDto> createHotBeverage(HotBeverageDto vehicleDto);
	
	ServiceResponseDto<HotBeverageDto> updateHotBeverage(HotBeverageDto vehicleDto);
	
	ServiceResponseDto<HotBeverageDto> getHotBeverage(int vehicleId);
	
	ServiceResponseDto<List<HotBeverageDto>> getHotBeveragesList();
	
	ServiceResponseDto<HotBeverageDto> deleteBeverages(int vehicleId);
}
