package com.goldbach.webflux.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.goldbach.webflux.db.domain.HotBeverageType;
import com.goldbach.webflux.dto.HotBeverageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.goldbach.webflux.db.domain.HotBeverageOrder;
import com.goldbach.webflux.db.repository.HotBeverageOrderRepository;
import com.goldbach.webflux.dto.ServiceResponseDto;
import com.goldbach.webflux.util.Constants;

/**
 * @author Davide Listello
 */
@Component
public class HotBeverageServiceImpl implements HotBeverageService {
	
	@Autowired
	private HotBeverageOrderRepository hotBeverageOrderRepository;
	
	@Override
	public ServiceResponseDto<HotBeverageDto> createHotBeverage(HotBeverageDto hotBeverageDto) {
		ServiceResponseDto<HotBeverageDto> responseDto = new ServiceResponseDto<HotBeverageDto>();
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
		responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		return responseDto;
	}

	@Override
	public ServiceResponseDto<HotBeverageDto> updateHotBeverage(HotBeverageDto hotBeverageDto) {
		ServiceResponseDto<HotBeverageDto> responseDto = new ServiceResponseDto<HotBeverageDto>();
		responseDto.setStatus(Constants.StatusCodes.NOT_FOUND);
		
		Optional<HotBeverageOrder> hotBeverageOptional = hotBeverageOrderRepository.findById(hotBeverageDto.getId());
		hotBeverageOptional.ifPresent(hotBeverageOrder -> {
			
			HotBeverageOrder hotBeverageOrderDomain = new HotBeverageOrder()
					.setHotDrinkName(hotBeverageDto.getHotDrinkName().toString())
					.setWithSugar(hotBeverageDto.getWithSugar())
					.setPrice(hotBeverageDto.getPrice())
					.setQtyGramsHotWater(hotBeverageDto.getQtyGramsHotWater())
					.setQtyGramsCoffeeBeans(hotBeverageDto.getQtyGramsCoffeeBeans())
					.setDurationMsInfusion(hotBeverageDto.getDurationMsInfusion())
					.setPressureBarInfusion(hotBeverageDto.getPressureBarInfusion())
					.setWithMilk(hotBeverageDto.getWithMilk());
			hotBeverageOrderRepository.save(hotBeverageOrderDomain);
			
			responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		});

		return responseDto;
	}

	@Override
	public ServiceResponseDto<HotBeverageDto> getHotBeverage(int hotBeverageId) {
		Optional<HotBeverageOrder> hotBeverageOrderOptional = hotBeverageOrderRepository.findById(hotBeverageId);
		ServiceResponseDto<HotBeverageDto> responseDto = new ServiceResponseDto<HotBeverageDto>();
		HotBeverageDto dto = null;
		responseDto.setStatus(Constants.StatusCodes.NOT_FOUND);
		if (hotBeverageOrderOptional.isPresent()) {
			HotBeverageOrder hotBeverageOrder = hotBeverageOrderOptional.get();
			dto = new HotBeverageDto()
					.setId(hotBeverageOrder.getId())
					.setHotDrinkName(HotBeverageType.valueOf(hotBeverageOrder.getHotDrinkName()))
					.setWithSugar(hotBeverageOrder.getWithSugar())
					.setPrice(hotBeverageOrder.getPrice())
					.setQtyGramsHotWater(hotBeverageOrder.getQtyGramsHotWater())
					.setQtyGramsCoffeeBeans(hotBeverageOrder.getQtyGramsCoffeeBeans())
					.setDurationMsInfusion(hotBeverageOrder.getDurationMsInfusion())
					.setPressureBarInfusion(hotBeverageOrder.getPressureBarInfusion())
					.setWithMilk(hotBeverageOrder.getWithMilk());
			responseDto.setResponseObject(dto);
			responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		}
		return responseDto;
	}

	@Override
	public ServiceResponseDto<List<HotBeverageDto>> getHotBeveragesList() {
		ServiceResponseDto<List<HotBeverageDto>> responseDto = new ServiceResponseDto<List<HotBeverageDto>>();
		
		List<HotBeverageOrder> hotBeverageOrderList = hotBeverageOrderRepository.findAll(PageRequest.of(0, 1000)).getContent();
		
		List<HotBeverageDto> hotBeverageDtoList = hotBeverageOrderList.parallelStream().map( (hotBeverageOrder) -> {
			return new HotBeverageDto()
					.setId(hotBeverageOrder.getId())
					.setHotDrinkName(HotBeverageType.valueOf(hotBeverageOrder.getHotDrinkName()))
					.setWithSugar(hotBeverageOrder.getWithSugar())
					.setPrice(hotBeverageOrder.getPrice())
					.setQtyGramsHotWater(hotBeverageOrder.getQtyGramsHotWater())
					.setQtyGramsCoffeeBeans(hotBeverageOrder.getQtyGramsCoffeeBeans())
					.setDurationMsInfusion(hotBeverageOrder.getDurationMsInfusion())
					.setPressureBarInfusion(hotBeverageOrder.getPressureBarInfusion())
					.setWithMilk(hotBeverageOrder.getWithMilk());
		}).collect(Collectors.toList());
		
		responseDto.setResponseObject(hotBeverageDtoList);
		responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		return responseDto;
	}

	@Override
	public ServiceResponseDto<HotBeverageDto> deleteBeverages(int hotBeverageId) {
		ServiceResponseDto<HotBeverageDto> responseDto = new ServiceResponseDto<HotBeverageDto>();
		responseDto.setStatus(Constants.StatusCodes.NOT_FOUND);
		
		Optional<HotBeverageOrder> hotBeverageOrderOptional = hotBeverageOrderRepository.findById(hotBeverageId);
		hotBeverageOrderOptional.ifPresent(hotBeverageOrder -> {
			hotBeverageOrderRepository.delete(hotBeverageOrder);
			responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		});

		return responseDto;
	}
	
}
