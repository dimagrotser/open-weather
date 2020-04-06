package com.wheather.services;

import com.wheather.domain.ResponseData;
import org.springframework.http.ResponseEntity;

public interface CommonServices {

    ResponseEntity<ResponseData> getResponse(String cityName);

    long getDailyLight(long sunrise, long sunset);
}
