package com.wheather;

import com.wheather.domain.ActualData;
import com.wheather.domain.ResponseData;
import com.wheather.services.CommonServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = Application.class)
public class WeatherTest {

    @Autowired
    private CommonServices commonServices;

    private List<String> cites;

    @Before
    public void setUp() {
        cites = new ArrayList<>();
        cites.add("Tel Aviv");
        cites.add("Singapore");
        cites.add("Auckland");
        cites.add("Ushuaia");
        cites.add("Miami");
        cites.add("London");
        cites.add("Berlin");
        cites.add("Reykjavik");
        cites.add("Cape Town");
        cites.add("Kathmandu");
    }

    @Test
    public void getTemp() {
        List<ActualData> actualDataList = new ArrayList<>();
        for (String city : cites) {
            ResponseEntity<ResponseData> restTemplate = commonServices.getResponse(city);
            assert restTemplate.getStatusCode().is2xxSuccessful();
            ResponseData getBody = restTemplate.getBody();
            assert getBody != null;
            long getDaylightTime = commonServices.getDailyLight(getBody.getSunriseSunsetData().getSunrise(),
                    getBody.getSunriseSunsetData().getSunset());
            actualDataList.add(new ActualData(city, getBody.getTempData().getTemp(), getDaylightTime));
        }

        ActualData actualData = Collections.max(actualDataList, Comparator.comparing(ActualData::getDayLightTime));
        System.out.println("The longest daylight temperature is: " + actualData.getTemperature() + " in the city: " + actualData.getCity());

        actualData = Collections.min(actualDataList, Comparator.comparing(ActualData::getDayLightTime));
        System.out.println("The shortest daylight temperature is: " + actualData.getTemperature() + " in the city: " + actualData.getCity());
    }

}
