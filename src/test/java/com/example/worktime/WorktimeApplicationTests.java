package com.example.worktime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Month;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WorktimeApplicationTests {

    @Autowired
    SalaryController salaryController;
    @Autowired
    IsDayOffApi isDayOffApi;
    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void getWorkingHoursPerMonth() {
        int result = isDayOffApi.getWorkingHoursPerMonth(Year.of(2020), Month.JANUARY);
        assertEquals(136, result);
    }

    @Test
    void computeHourIncome() {
        double result = salaryController.computeHourIncome(Year.of(2020), Month.JANUARY, 99960.0).getHour_income();
        assertEquals(735.0, result);
    }

    @Test
    void testSalary() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/hour_income")
                .param("year", "2020")
                .param("month", "january")
                .param("salary", "99960");
        this.mockMvc.perform(builder).andExpect(status().isOk())
                .andExpect(content().json("{\"year\":\"2020\",\"month\":\"JANUARY\",\"salary\":99960.0,\"hour_income\":735.0}"));
    }

}
