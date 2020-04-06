package com.wheather.services.impl;

import com.wheather.domain.ResponseData;
import com.wheather.services.CommonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class CommonServicesImpl implements CommonServices {

    @Autowired
    private TestRestTemplate testRestTemplate;

    public ResponseEntity<ResponseData> getResponse(String cityName) {
        String url = UriComponentsBuilder.newInstance().scheme("https").host("api.openweathermap.org")
                .path("/data/2.5/weather").queryParam("q", cityName)
                .queryParam("appid", "4648eb57cd7bb8ae7a317c8946e9d801").build().toUriString();
        return testRestTemplate.getForEntity(url, ResponseData.class);
    }

    public long getDailyLight(long sunrise, long sunset) {
        Duration duration = Duration.between(convertTimestampToLocalDateTime(sunrise), convertTimestampToLocalDateTime(sunset));
        return duration.getSeconds();
    }

    private LocalDateTime convertTimestampToLocalDateTime(long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp, 1000, ZoneOffset.UTC);
    }
}
