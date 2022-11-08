package com.goldbach.webflux.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.goldbach.webflux.dto.HotBeverageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.goldbach.webflux.db.domain.HotBeverageOrder;
import com.goldbach.webflux.db.repository.HotBeverageOrderRepository;
import com.goldbach.webflux.dto.ServiceResponseDto;
import com.goldbach.webflux.util.Constants;

@Component
public class HotBeverageServiceImpl implements HotBeverageService {
	
	@Autowired
	private HotBeverageOrderRepository vehicleRepo;
	
	@Override
	public ServiceResponseDto<HotBeverageDto> createHotBeverage(HotBeverageDto vehicleDto) {
		ServiceResponseDto<HotBeverageDto> responseDto = new ServiceResponseDto<HotBeverageDto>();
		HotBeverageOrder hotBeverageOrderDomain = new HotBeverageOrder()
				.setModel(vehicleDto.getModel())
				.setMake(vehicleDto.getMake())
				.setColor(vehicleDto.getColor())
				.setVin(vehicleDto.getVin())
				.setYear(vehicleDto.getYear());
		hotBeverageOrderDomain = vehicleRepo.save(hotBeverageOrderDomain);
		vehicleDto.setId(hotBeverageOrderDomain.getId());
		responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		return responseDto;
	}

	@Override
	public ServiceResponseDto<HotBeverageDto> updateVehicle(HotBeverageDto vehicleDto) {
		ServiceResponseDto<HotBeverageDto> responseDto = new ServiceResponseDto<HotBeverageDto>();
		responseDto.setStatus(Constants.StatusCodes.NOT_FOUND);
		
		Optional<HotBeverageOrder> vehicleOptional = vehicleRepo.findById(vehicleDto.getId());
		vehicleOptional.ifPresent(hotBeverageOrder -> {
			
			HotBeverageOrder hotBeverageOrderDomain = new HotBeverageOrder()
					.setColor(vehicleDto.getColor())
					.setId(vehicleDto.getId())
					.setMake(vehicleDto.getMake())
					.setModel(vehicleDto.getModel())
					.setVin(vehicleDto.getVin())
					.setYear(vehicleDto.getYear());
			vehicleRepo.save(hotBeverageOrderDomain);
			
			responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		});

		return responseDto;
	}

	@Override
	public ServiceResponseDto<HotBeverageDto> getVehicle(int vehicleId) {
		Optional<HotBeverageOrder> vehicleOptional = vehicleRepo.findById(vehicleId);
		ServiceResponseDto<HotBeverageDto> responseDto = new ServiceResponseDto<HotBeverageDto>();
		HotBeverageDto dto = null;
		responseDto.setStatus(Constants.StatusCodes.NOT_FOUND);
		if (vehicleOptional.isPresent()) {
			HotBeverageOrder v = vehicleOptional.get();
			dto = new HotBeverageDto().setColor(v.getColor())
					.setId(v.getId())
					.setMake(v.getMake())
					.setModel(v.getModel())
					.setVin(v.getVin())
					.setYear(v.getYear());
			responseDto.setResponseObject(dto);
			responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		}
		return responseDto;
	}

	@Override
	public ServiceResponseDto<List<HotBeverageDto>> getHotBeveragesList() {
		ServiceResponseDto<List<HotBeverageDto>> responseDto = new ServiceResponseDto<List<HotBeverageDto>>();
		
		List<HotBeverageOrder> hotBeverageOrderList = vehicleRepo.findAll(PageRequest.of(0, 1000)).getContent();
		
		List<HotBeverageDto> vehicleDtoList = hotBeverageOrderList.parallelStream().map( (hotBeverageOrder) -> {
			return new HotBeverageDto().setColor(hotBeverageOrder.getColor())
					.setId(hotBeverageOrder.getId())
					.setMake(hotBeverageOrder.getMake())
					.setModel(hotBeverageOrder.getModel())
					.setVin(hotBeverageOrder.getVin())
					.setYear(hotBeverageOrder.getYear());
		}).collect(Collectors.toList());
		
		responseDto.setResponseObject(vehicleDtoList);
		responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		return responseDto;
	}

	@Override
	public ServiceResponseDto<HotBeverageDto> deleteBeverages(int vehicleId) {
		ServiceResponseDto<HotBeverageDto> responseDto = new ServiceResponseDto<HotBeverageDto>();
		responseDto.setStatus(Constants.StatusCodes.NOT_FOUND);
		
		Optional<HotBeverageOrder> vehicleOptional = vehicleRepo.findById(vehicleId);
		vehicleOptional.ifPresent(hotBeverageOrder -> {
			
			vehicleRepo.delete(hotBeverageOrder);
			responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		});

		return responseDto;
	}
	
}
