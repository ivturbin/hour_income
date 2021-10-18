package com.example.worktime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Month;
import java.time.Year;

@Slf4j
@Service
public class IsDayOffApi {
    @Cacheable("workingHoursCache")
    public int getWorkingHoursPerMonth(Year year, Month month) {
        log.info("getting hours on {}, {}", year, month);
        final String uri = String.format("https://isdayoff.ru/api/getdata?year=%4s&month=%02d", year, month.getValue());
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        if (result != null) {
            return result.replaceAll("1", "").length() * 8; //Количество рабочих дней умножить на 8 часов
        }
        return -1;
    }
}
