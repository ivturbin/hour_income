package com.example.worktime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.time.Year;

@RestController
public class SalaryController {

    @Autowired
    IsDayOffApi isDayOffApi;

    @RequestMapping("/")
    public String hello() {
        return "Hello";
    }

    @RequestMapping("/hour_income")
    public Salary computeHourIncome(@RequestParam(value = "year") Year year,
                                    @RequestParam(value = "month") Month month,
                                    @RequestParam(value = "salary") Double salary) {

        return new Salary(year, month, salary, Math.round((salary/isDayOffApi.getWorkingHoursPerMonth(year, month)) * 100)/100.0);
    }
}
