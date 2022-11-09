package com.goldbach.webflux.dto;

import org.springframework.http.HttpHeaders;

import com.goldbach.webflux.util.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Davide Listello
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ServiceResponseDto<T> {

	private T responseObject;
    private HttpHeaders responseHTTPHeaders;
    private Constants.StatusCodes status;

}
