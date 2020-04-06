package com.wheather.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualData {

    private String city;
    private Double temperature;
    private long dayLightTime;
}
