package com.example.worktime;

import java.time.Month;
import java.time.Year;

public class Salary {
    private final Year year;
    private final Month month;
    private final double salary;
    private final double hour_income;

    public Salary(Year year, Month month, double salary, double hourIncome) {
        this.year = year;
        this.month = month;
        this.salary = salary;
        this.hour_income = hourIncome;
    }


    public Year getYear() {
        return year;
    }

    public Month getMonth() {
        return month;
    }

    public double getSalary() {
        return salary;
    }

    public double getHour_income() {
        return hour_income;
    }
}
